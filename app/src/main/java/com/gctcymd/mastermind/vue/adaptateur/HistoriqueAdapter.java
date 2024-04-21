package com.gctcymd.mastermind.vue.adaptateur;


import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gctcymd.mastermind.R;
import com.gctcymd.mastermind.modele.entite.HSession;

import java.util.ArrayList;
import java.util.List;

public class HistoriqueAdapter extends ArrayAdapter<HSession> {
    private List<HSession> lesSessionsDuJeu;

    private Context contexte;
    private int viewResourceId;
    private Resources res;

    public HistoriqueAdapter(@NonNull Context contexte,
                             int viewResourceId, @NonNull List<HSession> sessions){
        super(contexte, viewResourceId, sessions);

        this.contexte = contexte;
        this.viewResourceId = viewResourceId;
        this.res = contexte.getResources();
        this.lesSessionsDuJeu = sessions;
    }

    @Override
    public int getCount(){
        return this.lesSessionsDuJeu.size();
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view = convertView;
        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) contexte.getSystemService(Context.
                    LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(this.viewResourceId, parent, false);
        }
        final HSession hSession = this.lesSessionsDuJeu.get(position);

        if(hSession != null){
            final TextView hCourriel = (TextView) view.findViewById(R.id.hCourriel);
            final TextView hNbCouleurs = (TextView) view.findViewById(R.id.hNbCouleurs);
            final TextView hResultat = (TextView) view.findViewById(R.id.hResultat);
            final TextView hNbTentatives = (TextView) view.findViewById(R.id.hNbTentatives);
            //final ImageView hColor1 = (ImageView) view.findViewById(R.id.historiqueColor1);
            //final ImageView hColor2 = (ImageView) view.findViewById(R.id.historiqueColor2);
            //final ImageView hColor3 = (ImageView) view.findViewById(R.id.historiqueColor3);
            //final ImageView hColor4 = (ImageView) view.findViewById(R.id.historiqueColor4);
            //final ImageView hColor5 = (ImageView) view.findViewById(R.id.historiqueColor5);
            //final ImageView hColor6 = (ImageView) view.findViewById(R.id.historiqueColor6);
            ArrayList<Button> hColors = new ArrayList<>();
            hColors.add((Button) view.findViewById(R.id.historiqueColor1));
            hColors.add((Button) view.findViewById(R.id.historiqueColor2));
            hColors.add((Button) view.findViewById(R.id.historiqueColor3));
            hColors.add((Button) view.findViewById(R.id.historiqueColor4));
            hColors.add((Button) view.findViewById(R.id.historiqueColor5));
            hColors.add((Button) view.findViewById(R.id.historiqueColor6));

            hCourriel.setText(hSession.getCourriel());
            hNbCouleurs.setText(String.valueOf(hSession.getNbCouleurs()));
            hResultat.setText(hSession.getResultat());
            hNbTentatives.setText(String.valueOf(hSession.getNbTentatives()));
            int[] codeSecret = hSession.getCodeSecret();
            for (int i=0; i<codeSecret.length ; i++) {
                if (codeSecret[i] != 0){
                    hColors.get(i).setBackgroundColor(codeSecret[i]);
                    hColors.get(i).setVisibility(View.VISIBLE);
                }
            }
        }
    return view;
    }
}
