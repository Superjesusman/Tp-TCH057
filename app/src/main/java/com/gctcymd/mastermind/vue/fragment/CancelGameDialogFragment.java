package com.gctcymd.mastermind.vue.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class CancelGameDialogFragment extends DialogFragment {

    public interface DialogListener {
        void onDialogPositiveClick(DialogFragment dialog);
    }

    DialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Êtes vous sûre d'abandonner le jeu?")
                .setPositiveButton("Abandonner", (dialog, id) -> {
                    listener.onDialogPositiveClick(CancelGameDialogFragment.this);
                })
                .setNegativeButton("Annuler", (dialog, id) -> {
                    dialog.dismiss();                });
        return builder.create();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context
                    + " must implement DialogListener");
        }
    }

}

