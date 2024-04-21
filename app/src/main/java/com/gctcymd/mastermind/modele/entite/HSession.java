package com.gctcymd.mastermind.modele.entite;

import com.gctcymd.mastermind.modele.Mastermind;

public class HSession {
    private String courriel, resultat;
    private int nbCouleurs, nbTentatives;
    private CodeSecret codeSecret;

    public HSession(Mastermind mastermind) {
        this.courriel = mastermind.getUser();
        this.resultat = mastermind.getEtatDuJeu().toString();
        this.nbCouleurs = mastermind.getConfiguration().getNbreCouleurs();
        this.nbTentatives = mastermind.getNbreTentatives();
        this.codeSecret = mastermind.getCodeSecret();
        //System.arraycopy(codeS, 0, this.codeSecret, 0, codeS.length);
    }
    public String getCourriel(){
        return this.courriel;
    }
    public String getResultat(){
        return this.resultat;
    }
    public int getNbCouleurs(){
        return this.nbCouleurs;
    }
    public int getNbTentatives(){
        return this.nbTentatives;
    }
    public CodeSecret getCodeSecret(){
        return this.codeSecret;
    }
}
