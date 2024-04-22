package com.gctcymd.mastermind.modele.entite;

public class CodeSecret extends Code {
    private final int id;
    private final int nCouleurs;

    public CodeSecret(){
        super(new Couleur[] {});
        this.id = -1;
        this.nCouleurs = 0;
    }

    public CodeSecret(int id, int nCouleurs, Couleur[] code) {
        super(code);
        this.id = id;
        this.nCouleurs = nCouleurs;
    }
    public int getId() {
        return id;
    }

}
