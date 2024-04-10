package com.gctcymd.mastermind.modele;

public class CodeSecret extends Code{
    private int id;
    private int nCouleurs;

    public CodeSecret(int id, int nCouleurs, String[] code) {
        super(code);
        this.id = id;
        this.nCouleurs = nCouleurs;
    }
    public int getId() {
        return id;
    }

    public int getNCouleurs() {
        return nCouleurs;
    }
}
