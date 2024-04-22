package com.gctcymd.mastermind.vue.adaptateur;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gctcymd.mastermind.modele.entite.Couleur;

public class CodeAdapater extends ArrayAdapter<String> {
    private Context context;
    private int viewResourceId;
    private Resources ressources;
    private Couleur[] lesCodeDeCouleurs;

    public CodeAdapater(@NonNull Context context, int resource) { // Constructeur temporaire pour faire des tests
        super(context, resource);
    }

    /*public CodeAdapater(@NonNull Context context, int resource, @NonNull Code code) {
        //super(context, resource, code.getCode());
        this.context = context;
        this.viewResourceId = resource;
        this.ressources = context.getResources();
        this.lesCodeDeCouleurs = code.getCode();
    }*/

    @Override
    public int getCount() {
        return lesCodeDeCouleurs.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(this.viewResourceId, parent, false);
        }
        final Couleur couleur = this.lesCodeDeCouleurs[position];
        if (couleur != null){
        }

        return super.getView(position, convertView, parent);
    }
}
