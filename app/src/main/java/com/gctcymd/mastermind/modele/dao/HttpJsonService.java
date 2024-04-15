package com.gctcymd.mastermind.modele.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gctcymd.mastermind.modele.CodeSecret;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpJsonService {
    private static String URL_POINT_ENTREE = "http://10.0.2.2:3000";
    OkHttpClient okHttpClient = new OkHttpClient();
    public List<CodeSecret> getCodeSecrets(int longueurCode, int nbCouleur) throws IOException, JSONException {
        String path = "/codesSecrets?";
        if(nbCouleur != 0) path+="nbCouleurs="+nbCouleur;
        Request request = new Request.Builder().url(URL_POINT_ENTREE + path).build();
        Response response = okHttpClient.newCall(request).execute();
        ResponseBody responseBody = response.body();
        String jsonData = responseBody.string();

        ObjectMapper objectMapper = new ObjectMapper();
        List<CodeSecret> codeSecrets = new ArrayList<>();

        try {
            codeSecrets = objectMapper.readValue(jsonData, new TypeReference<List<CodeSecret>>(){});
            codeSecrets.removeIf(c -> c.getLongueur() != longueurCode);
            return codeSecrets;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return codeSecrets;
    }
}
