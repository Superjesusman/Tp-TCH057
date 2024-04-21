package com.example.tp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private String currentEmail = "";
    private boolean isConnected = false;
    private TextView welcomeMessage, emailMessage, warningMessage;
    private EditText email;
    private Button connect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeMessage = findViewById(R.id.textViewWelcomeMessage);
        emailMessage = findViewById(R.id.textViewEmail);
        warningMessage = findViewById(R.id.textViewWarning);
        email = findViewById(R.id.editTextEmailAddress);
        connect = findViewById(R.id.buttonConnect);

        connect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        currentEmail = email.getText().toString();
        if(currentEmail.matches("")&& !isConnected){
            Toast.makeText(getApplicationContext(), "Veuillez entrer votre email!",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if(isConnected){
            welcomeMessage.setText("Veuillez rentrer");
            emailMessage.setText("votre courriel");
            warningMessage.setVisibility(View.VISIBLE);
            email.setVisibility(View.VISIBLE);
            connect.setText("Connexion");
            connect.setBackgroundColor(Color.GREEN);
            isConnected = false;
            currentEmail = "";
            return;
        }
        welcomeMessage.setText("Vous êtes connecté en tant que:");
        emailMessage.setText(currentEmail);
        warningMessage.setVisibility(View.INVISIBLE);
        email.setVisibility(View.INVISIBLE);
        email.getText().clear();
        connect.setText("Déconnexion");
        connect.setBackgroundColor(Color.RED);
        isConnected = true;
    }
}