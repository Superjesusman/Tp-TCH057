package com.gctcymd.mastermind.modele.dao;

import static java.util.Arrays.copyOfRange;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gctcymd.mastermind.modele.entite.CodeSecret;
import com.gctcymd.mastermind.modele.entite.Couleur;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpJsonService {
    private static String URL_POINT_ENTREE = "http://10.0.2.2:3000";
    public CodeSecret getRandomCodeSecret(int longueurCode, int nbCouleur) throws IOException, JSONException {
        OkHttpClient okHttpClient = new OkHttpClient();

        String path = "/codesSecrets?";
        if(nbCouleur != 0) path+="nbCouleurs="+nbCouleur;
        Request request = new Request.Builder().url(URL_POINT_ENTREE + path).build();

        Response response = okHttpClient.newCall(request).execute();
        ResponseBody responseBody = response.body();
        String jsonData = responseBody.string();

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

        Couleur[] couleursDipos;

        if(jsonData.length() > 0) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                couleursDipos = objectMapper.readValue(jsonData,Couleur[].class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return couleursDipos;
        }
        return null;
    }
}
