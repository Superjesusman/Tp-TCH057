package com.gctcymd.mastermind.modele.entite;

public class Tentative {
    private final int nbTentative;
    private final Code essaiDeCode;
    private final Feedback feedback;

    public Tentative(int nTentative, Code essaiDeCode, Code codeSecret) {
        this.nbTentative = nTentative;
        this.essaiDeCode = essaiDeCode;
        this.feedback = new Feedback(essaiDeCode, codeSecret);;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public int getNbTentative() {
        return nbTentative;
    }

    public Code getEssaiDeCode() {
        return essaiDeCode;
    }
}
