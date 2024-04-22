package com.gctcymd.mastermind.modele.entite;

import java.util.Arrays;

//represente un code de couleur
public class Code {
    protected Couleur[] code;

    public Code(Couleur[] code){
        this.code = code;
    }

    public Code(String[] str) {
        code = new Couleur[str.length];
        int i = 0;
        for(String s: str){
            code[i] = new Couleur(s);
            i++;
        }
    }


    public int getLongueur(){
        return code.length;
    }

    public Couleur[] getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Code{" +
                "code=" + Arrays.toString(code) +
                '}';
    }
}
