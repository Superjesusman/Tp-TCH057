package com.gctcymd.mastermind.modele;

//represente un code de couleur
public class Code {
    private int longueurCode;
    private String[] code;

    public Code(String[] code) {
        this.longueurCode = code.length;
        this.code = code;
    }

    public int getLongueurCode() {
        return longueurCode;
    }

    public String[] getCode() {
        return code;
    }
}
