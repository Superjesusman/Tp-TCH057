package com.gctcymd.mastermind.modele.dao;

import com.gctcymd.mastermind.modele.entite.CodeSecret;

import org.json.JSONException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LesCodesSecretsDAO {
    public static CodeSecret getRandomCodeSecret(int longueurCode, int nbCouleur) throws IOException, JSONException {
        return new HttpJsonService().getRandomCodeSecret(longueurCode, nbCouleur);
    }
}
