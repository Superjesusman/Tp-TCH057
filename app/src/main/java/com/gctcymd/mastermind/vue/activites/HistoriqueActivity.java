package com.gctcymd.mastermind.vue.activites;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.gctcymd.mastermind.R;
import com.gctcymd.mastermind.modele.entite.HSession;
import com.gctcymd.mastermind.vue.adaptateur.HistoriqueAdapter;

import java.util.ArrayList;
import java.util.List;

public class HistoriqueActivity extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_historique2);
        lv = findViewById(R.id.lvHistorique);
        List<HSession> listeDeSessions = new ArrayList<HSession>();

        int[] cS = {Color.RED, Color.BLUE, Color.GREEN, Color.LTGRAY};

        listeDeSessions.add(new HSession("test@test.ca", "succes", 4, 4, cS));
        listeDeSessions.add(new HSession("test@test.ca", "succes", 4, 7, cS));
        listeDeSessions.add(new HSession("test@test.ca", "succes", 4, 2, cS));

        HistoriqueAdapter adapter = new HistoriqueAdapter(this, R.layout.session_jeu, listeDeSessions);

        lv.setAdapter(adapter);

    }
}