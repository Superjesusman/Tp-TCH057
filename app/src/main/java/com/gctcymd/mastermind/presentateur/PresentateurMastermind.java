package com.gctcymd.mastermind.presentateur;

import static java.util.Arrays.copyOfRange;

import android.app.Activity;

import com.gctcymd.mastermind.modele.Mastermind;
import com.gctcymd.mastermind.modele.dao.HttpJsonService;
import com.gctcymd.mastermind.modele.entite.Code;
import com.gctcymd.mastermind.modele.entite.CodeSecret;
import com.gctcymd.mastermind.modele.ModelManager;
import com.gctcymd.mastermind.modele.entite.Configuration;
import com.gctcymd.mastermind.modele.entite.Couleur;
import com.gctcymd.mastermind.modele.entite.EtatDuJeu;
import com.gctcymd.mastermind.vue.activites.JeuActivity;

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        }.start();
    }

    public void nouvelleTentative(Code input) {
        game.nouvelleTentative(input);
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
                        ((JeuActivity) activite).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((JeuActivity) activite).afficherMessage("Problème d'accès à l'API");
                            }
                        });
                    }
                }
            } .start();
        }
    }
}
