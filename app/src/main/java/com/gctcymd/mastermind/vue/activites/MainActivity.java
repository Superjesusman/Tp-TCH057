package com.gctcymd.mastermind.vue.activites;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.gctcymd.mastermind.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener , View.OnClickListener {
    private BottomNavigationView bottomNavigationView;
    private String currentEmail = "";
    private boolean isConnected = false;
    private TextView welcomeMessage, emailMessage, warningMessage, funMessage;
    private EditText email;
    private Button connect, config, history, game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

        welcomeMessage = findViewById(R.id.textViewWelcomeMessage);
        emailMessage = findViewById(R.id.textViewEmail);
        warningMessage = findViewById(R.id.textViewWarning);
        funMessage = findViewById(R.id.txtViewPourBtn);
        email = findViewById(R.id.editTextEmailAddress);
        connect = findViewById(R.id.buttonConnect);
        config = findViewById(R.id.buttonConfig);
        history = findViewById(R.id.buttonHistory);
        game = findViewById(R.id.buttonGame);

        connect.setOnClickListener(this);
        config.setOnClickListener(this);
        history.setOnClickListener(this);
        game.setOnClickListener(this);

        game.setVisibility(View.INVISIBLE);
        game.setVisibility(View.INVISIBLE);
        game.setVisibility(View.INVISIBLE);
        config.setVisibility(View.INVISIBLE);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //if not connected and item.getItemId() != R.id.home, pop up to connect!
        switch (item.getItemId()) {
            case R.id.home:
                return true;
            case R.id.game:
                if (!isConnected) {
                    Toast.makeText(getApplicationContext(), "Veuillez entrer votre email!",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }
                startActivity(new Intent(getApplicationContext(),JeuActivity.class));
                return true;
            case R.id.history:
                //startActivity(new Intent(getApplicationContext(),HistoryActivity.class));
                return true;
            case R.id.settings:
                if (!isConnected) {
                    Toast.makeText(getApplicationContext(), "Veuillez entrer votre email!",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }
                startActivity(new Intent(getApplicationContext(),ConfigurationActivity.class));
                return true;
        }
        return false;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        if (v == connect) {
            currentEmail = email.getText().toString();
            if (currentEmail.matches("") && !isConnected) {
                Toast.makeText(getApplicationContext(), "Veuillez entrer votre email!",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            if (isConnected) {
                welcomeMessage.setText("Veuillez entrer");
                emailMessage.setText("votre courriel");
                warningMessage.setVisibility(View.VISIBLE);
                email.setVisibility(View.VISIBLE);
                connect.setText("Connexion");
                connect.setBackgroundColor(getColor(R.color.soft_green));
                isConnected = false;
                currentEmail = "";
                funMessage.setText("Consulter les parties antérieures:");
                config.setVisibility(View.INVISIBLE);
                game.setVisibility(View.INVISIBLE);
                return;
            }
            funMessage.setText("Tu veux t'amuser?");
            welcomeMessage.setText("Vous êtes connecté en tant que:");
            config.setVisibility(View.VISIBLE);
            game.setVisibility(View.VISIBLE);
            emailMessage.setText(currentEmail);
            warningMessage.setVisibility(View.INVISIBLE);
            email.setVisibility(View.INVISIBLE);
            email.getText().clear();
            connect.setText("Déconnexion");
            connect.setBackgroundColor(getColor(R.color.soft_red));
            isConnected = true;
        }
        if(v==config){
            startActivity(new Intent(getApplicationContext(),ConfigurationActivity.class));
        }
        if(v==history){
            startActivity(new Intent(getApplicationContext(), HistoriqueActivity.class));
        }
        if(v==game){
            startActivity(new Intent(getApplicationContext(),JeuActivity.class));
        }
    }
}