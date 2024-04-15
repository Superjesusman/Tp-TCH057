package com.gctcymd.mastermind.modele;

import static com.gctcymd.mastermind.modele.entite.EtatDuJeu.*;

import com.gctcymd.mastermind.modele.entite.Code;
import com.gctcymd.mastermind.modele.entite.CodeSecret;
import com.gctcymd.mastermind.modele.entite.Configuration;
import com.gctcymd.mastermind.modele.entite.EtatDuJeu;
import com.gctcymd.mastermind.modele.entite.Feedback;
import com.gctcymd.mastermind.modele.entite.Tentative;

import java.util.ArrayList;

public class Mastermind {
    private Configuration configuration;
    private String user;
    private CodeSecret codeSecret;

    private ArrayList<Tentative> listTentatives;     //Les tentaives du joueur
    private int nbreTentatives; //Le nieme tentative dans la partie courante
    private EtatDuJeu etatDuJeu; //Le statut de la partie

    public Mastermind() {
        configuration = new Configuration();
        user = "";
        codeSecret = new CodeSecret();

        listTentatives = new ArrayList<>();
        nbreTentatives = 0;
        etatDuJeu = EN_COURS;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public String getUser() {
        return user;
    }

    public int getNbreTentatives() {
        return nbreTentatives;
    }

    public CodeSecret getCodeSecret() {
        return codeSecret;
    }

    public ArrayList<Tentative> getListTentatives() {
        return listTentatives;
    }

    public EtatDuJeu getEtatDuJeu(){
        return etatDuJeu;
    }
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setCodeSecret(CodeSecret codeSecret) {
        this.codeSecret = codeSecret;
    }

    //Nouvelle tentative de la part du joeur
    public boolean nouvelleTentative(Code essaiDeCode){
        Tentative tentative = new Tentative(nbreTentatives, essaiDeCode, codeSecret);
        nbreTentatives++;
        Feedback f = tentative.getFeedback();

        //Si le joeur a trouvé le code,
        if (f.isEquivalent()){
            etatDuJeu = EtatDuJeu.VICTOIRE;
        }

        if (nbreTentatives == configuration.getMaxTentatives()){
            etatDuJeu = EtatDuJeu.DEFAITE;
        }
        return listTentatives.add(tentative);
    }
}
