package com.gctcymd.mastermind.modele.entite;

public class Configuration {
    private int maxTentatives;
    private int longueurCode;
    private int nbreCouleurs;

    public Configuration() {
    }

    public int getMaxTentatives() {
        return maxTentatives;
    }

    public void setMaxTentatives(int maxTentatives) {
        this.maxTentatives = maxTentatives;
    }

    public int getLongueurCode() {
        return longueurCode;
    }

    public void setLongueurCode(int longueurCode) {
        this.longueurCode = longueurCode;
    }

    public int getNbreCouleurs() {
        return nbreCouleurs;
    }

    public void setNbreCouleurs(int nbreCouleurs) {
        this.nbreCouleurs = nbreCouleurs;
    }
}
