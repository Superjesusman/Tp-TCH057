package com.gctcymd.mastermind.vue.activites;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.gctcymd.mastermind.R;
import com.gctcymd.mastermind.modele.entite.Code;
import com.gctcymd.mastermind.modele.entite.Couleur;
import com.gctcymd.mastermind.modele.entite.HSession;
import com.gctcymd.mastermind.vue.adaptateur.HistoriqueAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HistoriqueActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private ListView lv;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_historique2);
        lv = findViewById(R.id.lvHistorique);
        List<HSession> listeDeSessions = new ArrayList<HSession>(); //doesn't MASTERMIND IS A SINGLETON!!!!! SHOULD BE FROM LOCAL BDB

        Couleur[] test = {new Couleur(Color.RED), new Couleur(Color.BLUE), new Couleur(Color.GREEN), new Couleur(Color.LTGRAY)};
        Code cS = new Code(test);

        listeDeSessions.add(new HSession("test@test.ca", "succes", 4, 4, cS));
        listeDeSessions.add(new HSession("test@test.ca", "succes", 4, 7, cS));
        listeDeSessions.add(new HSession("test@test.ca", "succes", 4, 2, cS));

        HistoriqueAdapter adapter = new HistoriqueAdapter(this, R.layout.session_jeu, listeDeSessions);

        lv.setAdapter(adapter);

    }
}