package com.gctcymd.mastermind.vue.activites;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.gctcymd.mastermind.R;
import com.gctcymd.mastermind.modele.entite.Couleur;
import com.gctcymd.mastermind.modele.entite.HSession;
import com.gctcymd.mastermind.vue.adaptateur.HistoriqueAdapter;

import java.util.ArrayList;
import java.util.List;

public class HistoriqueActivity extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_historique);
        lv = findViewById(R.id.lvHistorique);
        List<HSession> listeDeSessions = new ArrayList<HSession>();

        Couleur[] test = {new Couleur(Color.RED), new Couleur(Color.BLUE), new Couleur(Color.GREEN), new Couleur(Color.LTGRAY)};
        int[] cS = {Color.RED, Color.BLUE};

        listeDeSessions.add(new HSession("test@test.ca", "succes", 4, 4, cS));
        listeDeSessions.add(new HSession("test@test.ca", "succes", 4, 7, cS));
        listeDeSessions.add(new HSession("test@test.ca", "succes", 4, 2, cS));

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