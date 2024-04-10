package com.gctcymd.mastermind.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.gctcymd.mastermind.R;
import com.gctcymd.mastermind.modele.Code;
import com.gctcymd.mastermind.modele.Feedback;
import com.gctcymd.mastermind.modele.Mastermind;
import com.gctcymd.mastermind.modele.Stats;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class JeuActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener{
    private BottomNavigationView bottomNavigationView;
    private Button btnAbandon, btnNouvellePartie, btnValiderJeu;

    private GridView gridJeu;
    private LinearLayout layoutCouleurs;

    private int longCode = 4;
    private int nCouleurs = 8;
    private int nTentatives = 10;

    private Mastermind partieMastermind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        //Construction du menu navigation
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.settings);

        //Construction des boutons
        btnAbandon = findViewById(R.id.btnAbandon);
        btnNouvellePartie = findViewById(R.id.btnNouvellePartie);
        btnValiderJeu = findViewById(R.id.btnValiderJeu);

        btnAbandon.setOnClickListener(this);
        btnNouvellePartie.setOnClickListener(this);
        btnValiderJeu.setOnClickListener(this);

        //if intent
        Intent intent = getIntent();
        longCode = intent.getIntExtra("LONGUEUR_CODE", 4);
        nCouleurs = intent.getIntExtra("NOMBRE_COULEURS", 8);
        nTentatives = intent.getIntExtra("NOMBRES_TENTATIVES", 10);

        //draft vague

        //Construction de la grille
        GridLayout grille = findViewById(R.id.gridLayout);
        grille.setColumnCount(longCode + 2);
        grille.setRowCount(nTentatives);
        //grille.addView(composant);

        //construction des choix de couleurs
        LinearLayout layoutCouleurs = findViewById(R.id.layoutChoixCouleurs);
        Drawable drawable = getResources().getDrawable(R.drawable.bouton_oval);
        //for ... nCouleur
        //new button
        Button newButton = new Button(this);
        newButton.setBackground(drawable);
        newButton.getBackground().setTint(0xffff0000);
        ViewGroup.LayoutParams params;
        params = newButton.getLayoutParams();
        params.width = 100;
        params.height = 100;
        ((ViewGroup.MarginLayoutParams)params).setMargins(10,5,10,5);

        startGame();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                //pop up
                //clear game
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                return true;
            case R.id.game:
                return true;
            case R.id.history:
                //pop up
                //clear game
                //startActivity(new Intent(getApplicationContext(),HistoryActivity.class));
                return true;
            case R.id.settings:
                //pop up
                //clear game
                startActivity(new Intent(getApplicationContext(),ConfigurationActivity.class));
                return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if(v == btnAbandon){
            //pop up
            //clear game
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        } else if (v == btnNouvellePartie){
            //pop up
            //clear game and new game
        } else if (v == btnValiderJeu){
            //game
            //get le guess
            String[] couleursDuJoueur = {};

            //
            Code tentative = new Code(couleursDuJoueur);
            Feedback nouveauFeedback = partieMastermind.nouvelleTentative(tentative);

            //afficher le feedback
        }
    }

    private void startGame(){
        partieMastermind = new Mastermind();
    }
}