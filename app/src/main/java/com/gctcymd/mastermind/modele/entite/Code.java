package com.gctcymd.mastermind.modele.entite;

//represente un code de couleur
public class Code {
    protected Couleur[] code;

    public Code(Couleur[] code) {
        this.code = code;
    }

    public int getLongueur(){
        return code.length;
    }

    public Couleur[] getCode() {
        return code;
    }
}
