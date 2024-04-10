package com.gctcymd.mastermind.activites;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.gctcymd.mastermind.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ConfigurationActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener, AdapterView.OnItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    private Spinner spinLongCode, spinNCouleurs, spinNTentatives;

    private Button btnReinitialiser, btnSoumettre;

    private static final Integer[] valLongCode = new Integer[]{2, 3, 4, 5, 6};
    private static final Integer[] valNCouleurs = new Integer[]{2, 3, 4, 5, 6, 7, 8};
    private static final Integer[] valNTentatives = new Integer[]{8, 9, 10, 11, 12};

    private int longCode = 4;
    private int nCouleurs = 8;
    private int nTentatives = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Creation
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        //Construction du menu navigation
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.settings);

        //Construction des spinner et de leurs valeurs
        spinLongCode = findViewById(R.id.spinnerLongCode);
        spinNCouleurs = findViewById(R.id.spinnerNCouleurs);
        spinNTentatives = findViewById(R.id.spinnerNTentatives);

        //Attacher les valeurs associes aux spinners
        afficherSpinner(valLongCode, spinLongCode);
        afficherSpinner(valNCouleurs, spinNCouleurs);
        afficherSpinner(valNTentatives, spinNTentatives);

        //Construction des boutons et des listeners
        btnReinitialiser = findViewById(R.id.btnReinitialiserConfig);
        btnSoumettre = findViewById(R.id.btnSoumettreConfig);
        btnReinitialiser.setOnClickListener(this);
        btnSoumettre.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                return true;
            case R.id.game:
                startActivity(new Intent(getApplicationContext(),JeuActivity.class));
                return true;
            case R.id.history:
                //startActivity(new Intent(getApplicationContext(),HistoryActivity.class));
                return true;
            case R.id.settings:
                return true;
        }
        return false;
    }

    private void afficherSpinner(Integer[] intArray, Spinner spinner){
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, intArray);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnSoumettre){
            Intent changementDeConfigIntent = new Intent(this, JeuActivity.class);
            changementDeConfigIntent.putExtra("LONGUEUR_CODE", longCode);
            changementDeConfigIntent.putExtra("NOMBRE_COULEURS", nCouleurs);
            changementDeConfigIntent.putExtra("NOMBRES_TENTATIVES", nTentatives);
        } else if (v == btnReinitialiser){
            longCode = 4;
            nCouleurs = 8;
            nTentatives = 10;
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if ( view == spinLongCode){
            longCode  = (Integer) parent.getSelectedItem();
        } else if (view == spinNCouleurs) {
            nCouleurs  = (Integer) parent.getSelectedItem();
        } else if (view == spinNTentatives) {
            nTentatives  = (Integer) parent.getSelectedItem();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Rien ne se passe
    }

}
