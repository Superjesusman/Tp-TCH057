package com.gctcymd.mastermind.presentateur;

import static java.util.Arrays.copyOfRange;

import android.app.Activity;

import com.gctcymd.mastermind.modele.Mastermind;
import com.gctcymd.mastermind.modele.entite.Code;
import com.gctcymd.mastermind.modele.entite.CodeSecret;
import com.gctcymd.mastermind.modele.ModelManager;
import com.gctcymd.mastermind.modele.dao.ConfigurationDao;
import com.gctcymd.mastermind.modele.entite.Configuration;
import com.gctcymd.mastermind.modele.entite.Couleur;
import com.gctcymd.mastermind.vue.activites.JeuActivity;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class PresentateurMastermind {
    private Activity activite;
    private Mastermind game;
    public PresentateurMastermind(Activity activite) {
        this.activite = activite;
        this.game = ModelManager.getGame();
    }

    public void lancerJeu(Configuration configuration, String user) {
        new Thread() {
            @Override
            public void run() {
                try {
                    game.setConfiguration(configuration);
                    game.setUser(user);

                    CodeSecret codeSecret = ConfigurationDao.getRandomCodeSecret(configuration);
                    game.setCodeSecret(codeSecret);
                    Couleur[] couleursDispos = copyOfRange(ConfigurationDao.getCouleursDispos(), 0, configuration.getNbreCouleurs());
                    ((JeuActivity) activite).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((JeuActivity) activite).afficheJeu();
                        }
                    });
                } catch (JSONException e) {
                    ((JeuActivity) activite).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((JeuActivity) activite).afficherMessage("Problème dans le JSON des codes secrets");
                        }
                    });
                } catch (IOException e) {
                    ((JeuActivity) activite).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((JeuActivity) activite).afficherMessage("Problème d'accès à l'API");
                        }
                    });
                }
            }
        };
    }

    public void afficheNouvelleTentative(Code input) {
        //tentative dans game
        //affiche tentative et feedback
        //affiche si victoire ou defaite
    }

    public int getNbreTentatives() {
        return game.getNbreTentatives();
    }
}
