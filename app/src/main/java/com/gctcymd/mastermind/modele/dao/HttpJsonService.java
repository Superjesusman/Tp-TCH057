package com.gctcymd.mastermind.modele.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gctcymd.mastermind.modele.MyDBContract;
import com.gctcymd.mastermind.modele.MyDBHelper;
import com.gctcymd.mastermind.modele.entite.CodeSecret;
import com.gctcymd.mastermind.modele.entite.Configuration;
import com.gctcymd.mastermind.modele.entite.Couleur;
import com.gctcymd.mastermind.modele.entite.HSession;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpJsonService {
    private static final String URL_POINT_ENTREE = "http://10.0.2.2:3000";
    public CodeSecret getRandomCodeSecret(Configuration config) throws IOException, JSONException {
        OkHttpClient okHttpClient = new OkHttpClient();

        String path = "/codesSecrets?";

        int longueurCode = config.getLongueurCode();
        int nbCouleur = config.getNbreCouleurs();
        if(nbCouleur != 0) path+="nbCouleurs="+nbCouleur;
        Request request = new Request.Builder().url(URL_POINT_ENTREE + path).build();
        Log.d("Path:", URL_POINT_ENTREE + path);
        String jsonData = "";
        try {
            Response response = okHttpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();
            jsonData = responseBody.string();
        } catch (Exception e){
            System.out.println(e);
        }

        Log.d("HttpJsonService:getCodeSecrets", jsonData);

        List<CodeSecret> codeSecrets = new ArrayList<>();

        if(!jsonData.isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                codeSecrets = Arrays.asList(objectMapper.readValue(jsonData,CodeSecret[].class));
                codeSecrets.removeIf(c -> c.getLongueur() != longueurCode);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        int randomNum = ThreadLocalRandom.current().nextInt(0, codeSecrets.size());
        return codeSecrets.get(randomNum);
    }

    public Couleur[] getCouleursDisponibles() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();

        String path = "/couleursDisponibles";
        Request request = new Request.Builder().url(URL_POINT_ENTREE + path).build();

        Response response = okHttpClient.newCall(request).execute();
        ResponseBody responseBody = response.body();
        String jsonData = responseBody.string();

        Log.d("HttpJsonService:getCouleursDisponibles", jsonData);

        Couleur[] couleursDisponibles;
        String[] str;

        if(!jsonData.isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                str = objectMapper.readValue(jsonData,String[].class);
                couleursDisponibles = new Couleur[str.length];
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            int i = 0;
            for (String s:str){
                couleursDisponibles[i] = new Couleur(s);
                i++;
            }
            return couleursDisponibles;
        }
        return null;
    }

    public void pushNewStats(int id, String courriel, String resultat, int nbCouleurs, int nbTentatives, int[] cSString, Context context) throws IOException, JSONException {
        OkHttpClient okHttpClient = new OkHttpClient();

        HSession hsesh = new HSession(id, courriel, resultat, nbCouleurs, nbTentatives, cSString);

        MediaType JSON = MediaType.parse("application/json; chartset=utf-8");
        RequestBody corps = RequestBody.create(String.valueOf(hsesh), JSON);
        Request request = new Request.Builder().url(URL_POINT_ENTREE + "/stats")
                .post(corps)
                .build();

        MyDBHelper dbHelper = new MyDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(MyDBContract.MyTable.COLUMN_NAME1, id);
        values.put(MyDBContract.MyTable.COLUMN_NAME2, courriel);
        JSONArray jsonArray = new JSONArray();
        int i = 0;
        for(int color: cSString){
            switch (color) {
                case 0xffff0000:
                    jsonArray.put(i, "ROUGE");
                    break;
                case 0xff00ff00:
                    jsonArray.put(i, "VERT");
                    break;
                case 0xff0000ff:
                    jsonArray.put(i, "BLEU");
                    break;
                case 0xffffff00:
                    jsonArray.put(i, "JAUNE");
                    break;
                case 0xffff00ff:
                    jsonArray.put(i, "MAGENTA");
                    break;
                case 0xffffa500:
                    jsonArray.put(i, "ORANGE");
                    break;
                case 0xff000000:
                    jsonArray.put(i, "NOIR");
                    break;
                case 0xffffffff:
                    jsonArray.put(i, "BLANC");
                    break;
            }
            i++;
        }
        String jsonString = jsonArray.toString();
        values.put(MyDBContract.MyTable.COLUMN_NAME3, jsonString);
        values.put(MyDBContract.MyTable.COLUMN_NAME4, nbCouleurs);
        values.put(MyDBContract.MyTable.COLUMN_NAME5, resultat);
        values.put(MyDBContract.MyTable.COLUMN_NAME6, nbTentatives);

        long newRowId = db.insert(MyDBContract.MyTable.TABLE_NAME, null, values);

        if(newRowId != -1){
            Toast.makeText(context, "DB REUSSIE", Toast.LENGTH_LONG).show();
        }
    }
}
