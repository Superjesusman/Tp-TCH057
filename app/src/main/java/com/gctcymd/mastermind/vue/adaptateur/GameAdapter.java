package com.gctcymd.mastermind.vue.adaptateur;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gctcymd.mastermind.R;
import com.gctcymd.mastermind.modele.Mastermind;
import com.gctcymd.mastermind.modele.Tentative;

import java.util.List;

public class GameAdapter extends ArrayAdapter<Tentative> {
    private Context context;
    private int viewResourceId;
    private Resources ressources;
    private Mastermind mastermind; //? ou plutot below
    private List<Tentative> lesTentatives;

    public GameAdapter(@NonNull Context context, int resource, Mastermind mastermind) {
        super(context, resource);
        this.context = context;
        this.viewResourceId = resource;
        this.ressources = context.getResources();
        this.lesTentatives = mastermind.getLesTentatives();
    }

    public  int getCount() {
        return mastermind.getNbreTentatives();
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view = convertView;
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(this.viewResourceId, parent, false);
        }
        final Tentative tentative = this.lesTentatives.get(position);
        if (tentative==null){

        }
        //final Tentative tentative = this.
    }
}
