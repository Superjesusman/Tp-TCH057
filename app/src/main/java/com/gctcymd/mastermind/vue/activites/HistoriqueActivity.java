package com.gctcymd.mastermind.vue.activites;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.gctcymd.mastermind.R;
import com.gctcymd.mastermind.modele.MyDBContract;
import com.gctcymd.mastermind.modele.MyDBHelper;
import com.gctcymd.mastermind.modele.entite.Code;
import com.gctcymd.mastermind.modele.entite.CodeSecret;
import com.gctcymd.mastermind.modele.entite.Couleur;
import com.gctcymd.mastermind.modele.entite.HSession;
import com.gctcymd.mastermind.vue.adaptateur.HistoriqueAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistoriqueActivity extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_historique2);

        List<HSession> listeDeSessions = new ArrayList<HSession>();

        //Bloc pour consulter les donnees
        MyDBHelper dbHelper = new MyDBHelper(this);

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                MyDBContract.MyTable.COLUMN_NAME1,
                MyDBContract.MyTable.COLUMN_NAME2,
                MyDBContract.MyTable.COLUMN_NAME3,
                MyDBContract.MyTable.COLUMN_NAME4,
                MyDBContract.MyTable.COLUMN_NAME5,
                MyDBContract.MyTable.COLUMN_NAME6,
        };

        String selection = MyDBContract.MyTable.COLUMN_NAME2 + " = ?";
        String[] selectionArgs = { "test@test.ca" };

        String sortOrder = "";

        Cursor cursor = db.query(
                MyDBContract.MyTable.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        while (cursor.moveToNext()) {
            int idCode = cursor.getInt(cursor.getColumnIndexOrThrow(MyDBContract.MyTable.COLUMN_NAME1));
            String courriel = cursor.getString(cursor.getColumnIndexOrThrow(MyDBContract.MyTable.COLUMN_NAME2));
            String codeSec = cursor.getString(cursor.getColumnIndexOrThrow(MyDBContract.MyTable.COLUMN_NAME3));
            int nbCouleurs = cursor.getInt(cursor.getColumnIndexOrThrow(MyDBContract.MyTable.COLUMN_NAME4));
            Toast.makeText(this, "DB" + codeSec, Toast.LENGTH_LONG).show();
            int[] cS = new int[nbCouleurs];
            int couleur;
            try {
                JSONArray jsonArray = new JSONArray(codeSec);
                for(int i=0; i<jsonArray.length(); i++){
                    Toast.makeText(this, "DB" + jsonArray.getString(i), Toast.LENGTH_SHORT).show();
                    switch (jsonArray.getString(i)){
                        case "ROUGE":
                            cS[i] = 0xffff0000;
                            break;
                        case "VERT":
                            cS[i] = 0xff00ff00;
                            break;
                        case "BLEU":
                            cS[i] = 0xff0000ff;
                            break;
                        case "JAUNE":
                            cS[i] = 0xffffff00;
                            break;
                        case "MAGENTA":
                            cS[i] = 0xffff00ff;
                            break;
                        case "ORANGE":
                            cS[i] = 0xffffa500;
                            break;
                        case "NOIR":
                            cS[i] = 0xff000000;
                            break;
                        case "BLANC":
                            cS[i] = 0xffffffff;
                            break;
                    }

                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            String resultat = cursor.getString(cursor.getColumnIndexOrThrow(MyDBContract.MyTable.COLUMN_NAME5));
            int nbTentatives = cursor.getInt(cursor.getColumnIndexOrThrow(MyDBContract.MyTable.COLUMN_NAME6));
            Toast.makeText(this, "DB" + Arrays.toString(cS), Toast.LENGTH_LONG).show();
            listeDeSessions.add(new HSession(courriel, resultat, nbCouleurs, nbTentatives, cS));
        }
        cursor.close();
        //Un bloc pour inserer les donnees pour les tests;
/*
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(MyDBContract.MyTable.COLUMN_NAME1, 1);
        values.put(MyDBContract.MyTable.COLUMN_NAME2, "test@test.ca");
        ArrayList<String> cSString = new ArrayList<>();
        cSString.add("ROUGE");cSString.add("VERT");cSString.add("BLEU");cSString.add("JAUNE");
        JSONArray jsonArray = new JSONArray();
        for(String colorString: cSString){
            jsonArray.put(colorString);
        }
        String jsonString = jsonArray.toString();
        values.put(MyDBContract.MyTable.COLUMN_NAME3, jsonString);
        values.put(MyDBContract.MyTable.COLUMN_NAME4, 4);
        values.put(MyDBContract.MyTable.COLUMN_NAME5, "succes");
        values.put(MyDBContract.MyTable.COLUMN_NAME6, 4);

        long newRowId = db.insert(MyDBContract.MyTable.TABLE_NAME, null, values);

        if(newRowId != -1){
            Toast.makeText(this, "DB REUSSIE", Toast.LENGTH_LONG).show();
        }
*/

        lv = findViewById(R.id.lvHistorique);

        HistoriqueAdapter adapter = new HistoriqueAdapter(this, R.layout.session_jeu, listeDeSessions);

        lv.setAdapter(adapter);

    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                return true;
            case R.id.game:
                startActivity(new Intent(getApplicationContext(),JeuActivity.class));
                return true;
            case R.id.history:
                return true;
            case R.id.settings:
                startActivity(new Intent(getApplicationContext(),ConfigurationActivity.class));
                return true;
        }
        return false;
    }
}