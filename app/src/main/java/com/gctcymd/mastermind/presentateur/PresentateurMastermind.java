package com.gctcymd.mastermind.presentateur;

import static java.util.Arrays.copyOfRange;

import android.app.Activity;

import com.gctcymd.mastermind.modele.Mastermind;
import com.gctcymd.mastermind.modele.ModelManager;
import com.gctcymd.mastermind.modele.dao.HttpJsonService;
import com.gctcymd.mastermind.modele.entite.Code;
import com.gctcymd.mastermind.modele.entite.CodeSecret;
import com.gctcymd.mastermind.modele.entite.Configuration;
import com.gctcymd.mastermind.modele.entite.Couleur;
import com.gctcymd.mastermind.modele.entite.EtatDuJeu;
import com.gctcymd.mastermind.vue.activites.JeuActivity;

import org.json.JSONException;

import java.io.IOException;

public class PresentateurMastermind {
    private Activity activite;
    private Mastermind game;
    HttpJsonService h;

    public PresentateurMastermind(Activity activite) {
        this.activite = activite;
        this.game = ModelManager.getGame();
        this.h = new HttpJsonService();
    }

    public void lancerJeu(Configuration configuration, String user) {
        new Thread() {
            @Override
            public void run() {
                try {
                    game.setConfiguration(configuration);
                    game.setUser(user);
                    CodeSecret codeSecret = h.getRandomCodeSecret(configuration);
                    game.setCodeSecret(codeSecret);
                    Couleur[] couleursDispos = copyOfRange(h.getCouleursDisponibles(), 0, configuration.getNbreCouleurs());
                    activite.runOnUiThread(() -> ((JeuActivity) activite).afficheJeu(couleursDispos));
                } catch (JSONException e) {
                    activite.runOnUiThread(() -> ((JeuActivity) activite).afficherMessage("Problème dans le JSON des codes secrets"));
                } catch (IOException e) {
                    activite.runOnUiThread(() -> ((JeuActivity) activite).afficherMessage("Problème d'accès à l'API"));
                }
            }
        }.start();
    }

    public void detruireJeu(){
        ModelManager.detruire();
    }

    public void nouvelleTentative(Code input) {
        boolean success = game.nouvelleTentative(input);
        if (!success){
            ((JeuActivity) activite).afficherMessage("Problème d'ajout à l'ajout de la tentative");
        }
        ((JeuActivity) activite).afficherFeedback(game.getLastFeedback());

        if(game.getEtatDuJeu() != EtatDuJeu.EN_COURS) {
            if(game.getEtatDuJeu() == EtatDuJeu.VICTOIRE) {
                ((JeuActivity) activite).afficherFin(EtatDuJeu.VICTOIRE);
            } else if (game.getEtatDuJeu() == EtatDuJeu.DEFAITE) {
                ((JeuActivity) activite).afficherFin(EtatDuJeu.DEFAITE);
            }
            new Thread() {
                @Override
                public void run() {
                    try {
                        h.pushNewStats();
                        //and locally as well
                    } catch (IOException e) {
                        activite.runOnUiThread(() -> ((JeuActivity) activite).afficherMessage("Problème d'accès à l'API"));
                    }
                }
            } .start();
        }
    }
}
