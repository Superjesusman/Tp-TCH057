package com.gctcymd.mastermind.modele.entite;

public class Couleur {
    private final String codeHex;

    public Couleur(String codeHex) {
        if (codeHex.length() != 8) throw new IllegalArgumentException("The hex should be 8 characters long");
        this.codeHex = codeHex;
    }

    public String getCodeHex() {
        return codeHex;
    }
}
