package com.gctcymd.mastermind.modele.entite;

import com.gctcymd.mastermind.modele.entite.Couleur;

//represente un code de couleur
public class Code {
    protected int longueurCode;
    protected Couleur[] code;

    public Code(Couleur[] code) {
        this.longueurCode = code.length;
        this.code = code;
    }

    public int getLongueur() {
        return longueurCode;
    }

    public Couleur[] getCode() {
        return code;
    }
}
