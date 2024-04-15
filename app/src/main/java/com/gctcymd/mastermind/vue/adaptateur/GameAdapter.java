package com.gctcymd.mastermind.vue.adaptateur;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gctcymd.mastermind.modele.Mastermind;
import com.gctcymd.mastermind.modele.entite.Tentative;
import com.gctcymd.mastermind.presentateur.PresentateurMastermind;

import java.util.List;

public class GameAdapter extends ArrayAdapter<Tentative> {
    private Context context;
    private int viewResourceId;
    private Resources ressources;

    private PresentateurMastermind presentateur;

    public GameAdapter(@NonNull Context context, int resource, PresentateurMastermind presentateur) {
        super(context, resource);
        this.context = context;
        this.viewResourceId = resource;
        this.ressources = context.getResources();
        this.presentateur = presentateur;
    }

    public  int getCount() {
        return presentateur.getNbreTentatives();
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view = convertView;
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(this.viewResourceId, parent, false);
        }
        //if tentative is null        //all greyed
        // else tentative shows info
        return null;
    }
}
