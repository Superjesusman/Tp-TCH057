package com.gctcymd.mastermind.modele.entite;

public class Couleur {
    private String codeHex;

    public Couleur(String codeHex) {
        if (codeHex.length() != 8) throw new IllegalArgumentException("The hex should be 8 characters long: " + codeHex);
    }

    public String getCodeHex() {
        return codeHex;
    }
    public int toInt(){
        return (int)Long.parseLong(codeHex,16);
    }
}
