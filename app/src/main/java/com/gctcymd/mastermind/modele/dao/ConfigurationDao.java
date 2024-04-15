package com.gctcymd.mastermind.modele.dao;

import com.gctcymd.mastermind.modele.entite.CodeSecret;
import com.gctcymd.mastermind.modele.entite.Configuration;
import com.gctcymd.mastermind.modele.entite.Couleur;

import org.json.JSONException;

import java.io.IOException;

public class ConfigurationDao {
    public static CodeSecret getRandomCodeSecret(Configuration configuration) throws IOException, JSONException {
        return new HttpJsonService().getRandomCodeSecret(configuration.getLongueurCode(), configuration.getNbreCouleurs());
    }

    public static Couleur[] getCouleursDispos() throws IOException, JSONException {
        return new HttpJsonService().getCouleursDisponibles();
    }
}