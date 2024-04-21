package com.gctcymd.mastermind.vue.activites;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
//import android.widget.GridLayout;
import androidx.gridlayout.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.gctcymd.mastermind.R;
import com.gctcymd.mastermind.modele.entite.Code;
import com.gctcymd.mastermind.modele.entite.Configuration;
import com.gctcymd.mastermind.modele.entite.Couleur;
import com.gctcymd.mastermind.modele.entite.EtatDuJeu;
import com.gctcymd.mastermind.modele.entite.Feedback;
import com.gctcymd.mastermind.presentateur.PresentateurMastermind;
import com.gctcymd.mastermind.vue.adaptateur.GameAdapter;
import com.gctcymd.mastermind.vue.fragment.CancelGameDialogFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class JeuActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener, CancelGameDialogFragment.DialogListener{
    private BottomNavigationView bottomNavigationView;
    private Button btnAbandon, btnNouvellePartie, btnValiderJeu;

    private GridLayout gridJeu;
    private LinearLayout layoutCouleurs;
    private Button[] boutonsCouleurs;
    private Button[][] boutonsTentative, boutonsFeedback;
    private Configuration configuration;
    String user;
    private PresentateurMastermind presentateurMastermind;
    private GameAdapter adaptateur;

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

        layoutCouleurs = findViewById(R.id.layoutChoixCouleurs);

        btnAbandon.setOnClickListener(this);
        btnNouvellePartie.setOnClickListener(this);
        btnValiderJeu.setOnClickListener(this);

        //Obtenir la configuration choisie
        this.configuration = new Configuration();

        Intent intent = getIntent();
        configuration.setLongueurCode(intent.getIntExtra("LONGUEUR_CODE", 4));
        configuration.setNbreCouleurs(intent.getIntExtra("NOMBRE_COULEURS", 8));
        configuration.setMaxTentatives(intent.getIntExtra("NOMBRES_TENTATIVES", 10));

        //Obtenir l'utilisateur
        String tempUser = "johndoe@mail.com"; //TEMP USER for test

        this.presentateurMastermind = new PresentateurMastermind(this);
        presentateurMastermind.lancerJeu(configuration, tempUser);

        //array of all buttons for attempts
        boutonsTentative = new Button[configuration.getMaxTentatives()][configuration.getLongueurCode()];
        boutonsFeedback =  new Button[configuration.getMaxTentatives()][configuration.getLongueurCode()];
        boutonsCouleurs = new Button[configuration.getNbreCouleurs()];

        //generate grid
        gridJeu = findViewById(R.id.gridPartie);
        gridJeu.setColumnCount(configuration.getLongueurCode()+2);
        gridJeu.setRowCount(configuration.getMaxTentatives());


        //filling the grid with buttons
        for (int i = 1; i < configuration.getMaxTentatives()+1; i++) {
            TextView numTentative = new TextView(this);
            numTentative.setText(String.valueOf(i));
            numTentative.setTextSize(20);
            gridJeu.addView(numTentative);

            for (int j = 0; j < configuration.getLongueurCode(); j++) {
                Button button = new Button(this);
                Drawable drawable = getResources().getDrawable(R.drawable.bouton_oval);
                button.setBackground(drawable);
                button.setOnClickListener(this);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = 150; // Réduire la largeur du bouton
                params.height = 150; // Réduire la hauteur du bouton
                button.setLayoutParams(params);
                boutonsTentative[i-1][j] = button;
                gridJeu.addView(button);
            }
            GridLayout gridFeedback = new GridLayout(this);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT; // Réduire la largeur du bouton
            params.height = ViewGroup.LayoutParams.MATCH_PARENT; // Réduire la hauteur du bouton
            //gridFeedback.setLayoutParams(params);
            gridFeedback.setColumnCount(2);
            //adding the feedback buttons
            for (int j = 0; j < configuration.getLongueurCode(); j++) {
                Button button = new Button(this);
                Drawable drawable = getResources().getDrawable(R.drawable.bouton_oval);
                button.setBackground(drawable);
                boutonsFeedback[i-1][j] = button;
                params.width = 100; // Réduire la largeur du bouton
                params.height = 100; // Réduire la hauteur du bouton
                //button.setLayoutParams(params);
                gridFeedback.addView(button);
            }
            gridFeedback.setBackgroundColor(Color.GRAY);
            gridJeu.addView(gridFeedback);
        }
        for (int i = 0; i < configuration.getNbreCouleurs(); i++) {
            Button button = new Button(this);
            Drawable drawable = getResources().getDrawable(R.drawable.bouton_oval);
            button.setBackground(drawable);
            boutonsCouleurs[i] = button;
            layoutCouleurs.addView(button);
        }
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
                startActivity(new Intent(getApplicationContext(),HistoriqueActivity.class));
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
            Couleur[] couleursDuJoueur = {};

            //
            Code tentative = new Code(couleursDuJoueur);
            this.presentateurMastermind.nouvelleTentative(tentative);
            //afficher le feedback
        }
        else{
            Toast.makeText(getApplicationContext(), "test!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void showAbandonDialog(){
        DialogFragment newFragment = new CancelGameDialogFragment();
        newFragment.show(getSupportFragmentManager(), "game");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        scrapGame();//scrap game
        this.presentateurMastermind.lancerJeu(configuration, user);
    }
    private void scrapGame(){

    }

    private void afficherChoixCouleurs() {
    }
    private void afficheGrilleDeJeu(){
    }

    public void afficheJeu() {
        afficherChoixCouleurs();
        afficheGrilleDeJeu();
    }

    public void afficherMessage(String s) {
    }

    public void afficherFeedback(Feedback lastFeedback) {
    }

    public void afficherFin(EtatDuJeu etatDuJeu) {
    }
}