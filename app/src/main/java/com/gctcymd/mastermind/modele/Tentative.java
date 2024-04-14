package com.gctcymd.mastermind.modele;

public class Tentative {
    private final int nTentative;
    private final Code essaiDeCode;
    private final Feedback feedback;

    public Tentative(int nTentative, Code essaiDeCode, Code codeSecret) {
        this.nTentative = nTentative;
        this.essaiDeCode = essaiDeCode;
        this.feedback = new Feedback(essaiDeCode, codeSecret);;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public int getnTentative() {
        return nTentative;
    }

    public Code getEssaiDeCode() {
        return essaiDeCode;
    }
}
