package com.gctcymd.mastermind.modele.entite;

public class CodeSecret extends Code {
    private final int id;
    private final int nbCouleurs;

    public CodeSecret(){
        super(new String[] {});
        this.id = -1;
        this.nbCouleurs = 0;
    }

    public CodeSecret(int id, String[] code, int nCouleurs) {
        super(code);
        this.id = id;
        this.nbCouleurs = nCouleurs;
    }
    public int getId() {
        return id;
    }

    public int getNbCouleurs() {
        return nbCouleurs;
    }
}
