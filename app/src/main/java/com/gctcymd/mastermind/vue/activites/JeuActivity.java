package com.gctcymd.mastermind.vue.activites;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.gctcymd.mastermind.R;
import com.gctcymd.mastermind.modele.Code;
import com.gctcymd.mastermind.modele.Mastermind;
import com.gctcymd.mastermind.vue.fragment.CancelGameDialogFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class JeuActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener, CancelGameDialogFragment.DialogListener{
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
        bottomNavigationView.setSelectedItemId(R.id.game);

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

        //generate grid
        gridJeu = findViewById(R.id.gridJeu);
        for(int i = 0; i <  nTentatives; i++){

        }
//        CustomListAdapter adapter=new CustomListAdapter(HomePage.this,allElementDetails);
//        gridview.setAdapter(adapter);

        //

        startGame();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                return true;
            case R.id.game:
                return true;
            case R.id.history:
                //startActivity(new Intent(getApplicationContext(),HistoryActivity.class));
                return true;
            case R.id.settings:
                DialogFragment newFragment = new CancelGameDialogFragment();
                newFragment.show(getSupportFragmentManager(), "game");
                //then startActivity(new Intent(getApplicationContext(),ConfigurationActivity.class));
                return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if(v == btnAbandon){
            DialogFragment newFragment = new CancelGameDialogFragment();
            newFragment.show(getSupportFragmentManager(), "game");
            //then startActivity(new Intent(getApplicationContext(),MainActivity.class));
        } else if (v == btnNouvellePartie){
            DialogFragment newFragment = new CancelGameDialogFragment();
            newFragment.show(getSupportFragmentManager(), "game");
        } else if (v == btnValiderJeu){
            //game
            //get le guess
            String[] couleursDuJoueur = {};

            //
            Code tentative = new Code(couleursDuJoueur);
            partieMastermind.nouvelleTentative(tentative);
            partieMastermind.getLastTentative();

            //afficher le feedback
        }
    }

    public void showAbandonDialog(){
        DialogFragment newFragment = new CancelGameDialogFragment();
        newFragment.show(getSupportFragmentManager(), "game");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        scrapGame();//scrap game
        startGame();
    }

    private void startGame(){
        //getCodeSecret avec dao
        //partieMastermind = new Mastermind(codeSecret);
    }

    private void showGame(){

    }

    private void scrapGame(){

    }
}