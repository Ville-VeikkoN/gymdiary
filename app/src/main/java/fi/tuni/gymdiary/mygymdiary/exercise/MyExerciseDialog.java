package fi.tuni.gymdiary.mygymdiary.exercise;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fi.tuni.gymdiary.mygymdiary.R;

/**
 * Class for displaying dialog when adding Exercise.
 *
 * @author Ville-Veikko Nieminen
 * @version 1.8
 * @since 2019-04-21
 */
public class MyExerciseDialog extends DialogFragment {
    /**
     * Method is called when MyExerciseDialog is created. Sets the layout for dialog.
     * Listens if Buttons is clicked and acts the way needed.
     *
     * @param savedInstanceState Bundle
     * @return Dialog representing dialog for adding Exercise
     */
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        final LayoutInflater inflater = requireActivity().getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View promptView = inflater.inflate(R.layout.dialog_addexercise,null);
        builder.setView(promptView);

        Button btn_add = promptView.findViewById(R.id.btn_addExercise);
        Button btn_cancel = promptView.findViewById(R.id.btn_cancelExercise);
        final EditText ed_exercise = promptView.findViewById(R.id.addexercise);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(ed_exercise.getText().toString())) {
                    ed_exercise.setError("Cannot be empty");
                } else {
                    Log.d("MyTag","Calls ExerciseActivity method");
                    Exercise exercise = new Exercise(ed_exercise.getText().toString());
                    ExerciseActivity exerciseActivity = (ExerciseActivity) getActivity();
                    exerciseActivity.addExercise(exercise);
                    dismiss();
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return builder.create();
    }
}
