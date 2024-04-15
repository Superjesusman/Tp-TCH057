package com.gctcymd.mastermind.modele;

import static com.gctcymd.mastermind.modele.Mastermind.EtatDuJeu.EN_COURS;

import java.util.ArrayList;

public class Mastermind {

    //Les tentaives du joueur
    private ArrayList<Tentative> lesTentatives;

    //Les statistiques du jeu
    private final int maxTentatives;
    private final int longueurCode;
    private final int nbreCouleurs;
    private final CodeSecret codeSecret;
    private int nbreTentatives; //Le nieme tentative dans la partie courante

    //Le statut de la partie
    private EtatDuJeu etatDuJeu;
    public enum EtatDuJeu {
        EN_COURS,
        VICTOIRE,
        DEFAITE
    }


    public Mastermind(int maxTentative, int longCode, int nCouleurs) {
        //this.codeSecret = codeSecret; //api to fetch a code with long code et nCourleurs to do
        this.maxTentatives = maxTentative;
        this.longueurCode = longCode;
        this.nbreCouleurs = nCouleurs;

        lesTentatives = new ArrayList<>();
        nbreTentatives = 0;

        etatDuJeu = EN_COURS;
    }

    public int getNbreTentatives() {
        return nbreTentatives;
    }

    public int getMaxTentatives() {
        return maxTentatives;
    }

    public int getLongueurCode() {
        return longueurCode;
    }

    public int getNbreCouleurs() {
        return nbreCouleurs;
    }

    public CodeSecret getCodeSecret() {
        return codeSecret;
    }

    //Nouvelle tentative de la part du joeur
    public boolean nouvelleTentative(Code essaiDeCode){
        Tentative tentative = new Tentative(nbreTentatives, essaiDeCode, codeSecret);
        nbreTentatives++;
        Feedback f = tentative.getFeedback();

        //Si le joeur a trouvé le code,
        if (f.isEquivalent()){
            etatDuJeu = EtatDuJeu.VICTOIRE;
        }

        if (nbreTentatives == maxTentatives){
            etatDuJeu = EtatDuJeu.DEFAITE;
        }
        return lesTentatives.add(tentative);
    }

    public Tentative getLastTentative(){
        return lesTentatives.get(nbreTentatives);
    }

    public ArrayList<Tentative> getLesTentatives() {
        return lesTentatives;
    }

    public EtatDuJeu getEtatDuJeu(){
        return etatDuJeu;
    }
}
