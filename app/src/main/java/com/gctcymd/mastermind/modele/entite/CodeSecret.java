package com.gctcymd.mastermind.modele.entite;

import com.gctcymd.mastermind.modele.entite.Code;
import com.gctcymd.mastermind.modele.entite.Couleur;

public class CodeSecret extends Code {
    private int id;
    private int nCouleurs;

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

    public int getNCouleurs() {
        return nCouleurs;
    }
}
