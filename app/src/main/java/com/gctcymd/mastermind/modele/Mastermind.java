package com.gctcymd.mastermind.modele;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Mastermind {
    private CodeSecret codeSecret;

    private HashMap<Code, Feedback> listeTentatives;

    private int nTentatives;

    public Mastermind() {
        codeSecret = null; //api to fetch
        listeTentatives = new HashMap<Code, Feedback>();
        nTentatives = 0;
    }

    public Feedback nouvelleTentative(Code tentative){
        Feedback nouveauFeedback = new Feedback(codeSecret, tentative);
        listeTentatives.put(tentative, nouveauFeedback);
        nTentatives++;
        return nouveauFeedback;
    }
}
