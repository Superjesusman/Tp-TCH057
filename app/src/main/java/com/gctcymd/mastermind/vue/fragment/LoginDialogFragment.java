package com.gctcymd.mastermind.vue.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class LoginDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Vous DEVEZ vous connecter. Entrez votre courriel svp.")
                .setPositiveButton("OK", (dialog, id) -> {dialog.dismiss();});
        return builder.create();
    }

}

