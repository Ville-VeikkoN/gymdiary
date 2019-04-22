package fi.tuni.gymdiary.mygymdiary.exercise;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;


public class ConfirmDeleteDialog extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final int setId = getArguments().getInt("setId");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Confirm delete")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ExerciseActivity exerciseActivity = (ExerciseActivity) getActivity();
                        exerciseActivity.deleteSet(setId);
                        dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }
                });
        return builder.create();
    }
}
