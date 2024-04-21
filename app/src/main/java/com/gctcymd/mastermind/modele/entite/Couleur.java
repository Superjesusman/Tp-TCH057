package com.gctcymd.mastermind.modele.entite;

public class Couleur {
    private final int codeHex;

    public Couleur(String codeHex) {
        if (codeHex.length() != 8) throw new IllegalArgumentException("The hex should be 8 characters long");
        this.codeHex = (int)Long.parseLong(codeHex,16);
    }

    public Couleur(int codeHex) {
        this.codeHex = codeHex;
    }

    public int getCodeHex() {
        return codeHex;
    }
}
