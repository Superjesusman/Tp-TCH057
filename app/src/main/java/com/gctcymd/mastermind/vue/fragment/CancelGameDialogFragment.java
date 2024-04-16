package com.gctcymd.mastermind.vue.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

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
                    // Cancel the game
                    listener.onDialogPositiveClick(CancelGameDialogFragment.this);
                })
                .setNegativeButton("Annuler", (dialog, id) -> {
                    // User cancels the dialog
                    dialog.dismiss();                });
        // Create the AlertDialog object and return it.
        return builder.create();
    }

    // Override the Fragment.onAttach() method to instantiate the
    // NoticeDialogListener.
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface. Throw exception.
            throw new ClassCastException(context
                    + " must implement DialogListener");
        }
    }

}

