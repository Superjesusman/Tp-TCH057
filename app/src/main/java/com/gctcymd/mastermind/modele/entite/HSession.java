package com.gctcymd.mastermind.modele.entite;

public class HSession {
    int id;
    private String courriel, resultat;
    private int nbCouleurs, nbTentatives;
    private int[] codeSecret;

    public HSession(int id, String courriel, String resultat, int nbCouleurs, int nbTentatives, int[] codeS) {
        this.id = id;
        this.courriel = courriel;
        this.resultat = resultat;
        this.nbCouleurs = nbCouleurs;
        this.nbTentatives = nbTentatives;
        this.codeSecret = new int[codeS.length];
        System.arraycopy(codeS, 0, this.codeSecret, 0, codeS.length);
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
    public int[] getCodeSecret(){
        return this.codeSecret;
    }
}
