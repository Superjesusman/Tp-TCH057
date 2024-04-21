package com.gctcymd.mastermind.modele.dao;

import com.gctcymd.mastermind.modele.CodeSecret;
import com.gctcymd.mastermind.modele.Tentative;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LesCodesSecretsDAO {
    private Connection conn;

    public LesCodesSecretsDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:mastermind.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<CodeSecret> getLesCodeSecret() {
        ArrayList<Tentative> lesTentatives = new ArrayList<>();
        String sql = "SELECT * FROM Tentatives";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CodeSecret tentative = new Tentative();
                // Remplissez l'objet Tentative avec les données de la base de données
                // tentative.set...
                lesTentatives.add(tentative);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lesTentatives;
    }
}
