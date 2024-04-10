package com.gctcymd.mastermind.modele;

import java.util.ArrayList;

public class Mastermind {
    private CodeSecret codeSecret;

    private ArrayList<Code> listeTentatives;
    private ArrayList<Feedback> listeFeedbacks;

    private int nTentatives;

    private boolean victoire;

    public Mastermind(CodeSecret codeSecret) {
        this.codeSecret = codeSecret; //api to fetch
        listeTentatives = new ArrayList<>();
        listeFeedbacks = new ArrayList<>();
        nTentatives = 0;
        victoire = false;
    }

    public boolean nouvelleTentative(Code tentative){
        Feedback nouveauFeedback = new Feedback(codeSecret, tentative);
        boolean addedCode = listeTentatives.add(tentative);
        boolean addedFeedback = listeFeedbacks.add(nouveauFeedback);
        nTentatives++;

        if (codeSontEquivalents(nouveauFeedback.getIndicateurExact(), nouveauFeedback.getIndicateurApproximatif(), codeSecret)){
            victoire = true;
        }
        return addedCode && addedFeedback;
    }

    public Code getLastTentative(){
        return listeTentatives.get(nTentatives);
    }

    public Feedback getLastFeedback(){
        return listeFeedbacks.get(nTentatives);
    }

    public boolean isVictoire() {
        return victoire;
    }

    private boolean codeSontEquivalents(int indicExact, int indicApprox, Code c){
        return indicApprox==0 && indicExact==c.getLongueurCode();
    }
}
