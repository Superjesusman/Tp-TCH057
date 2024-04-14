package com.gctcymd.mastermind.modele;

import java.util.Objects;

public class Feedback {
    private int indicateurExact;
    private int indicateurApproximatif;

    private boolean isEquivalent;

    public Feedback(Code c1, Code c2) {
        String[] code1 = c1.getCode();
        String[] code2 = c2.getCode();
        if (code1.length != code2.length){
            //code pas de la meme longueur!!
            throw new IllegalArgumentException();
        }
        indicateurExact = 0;
        indicateurApproximatif = 0;
        for(int i = 0; i < code1.length; i++){
            for (int j = 0; j < code2.length; j++){
                if (Objects.equals(code1[i], code2[j])){
                    if(i == j) indicateurExact++;
                    else indicateurApproximatif++;
                }
            }
        }
        if (indicateurApproximatif == 0 && indicateurExact == c1.getLongueur()) isEquivalent = true;
    }

    public int getIndicateurExact() {
        return indicateurExact;
    }

    public int getIndicateurApproximatif() {
        return indicateurApproximatif;
    }

    public boolean isEquivalent() {
        return isEquivalent;
    }
}
