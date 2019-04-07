package fi.tuni.gymdiary.mygymdiary.exercise;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import fi.tuni.gymdiary.mygymdiary.R;

public class MySetsDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        final LayoutInflater inflater = requireActivity().getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View promptView = inflater.inflate(R.layout.dialog_addactivity,null);
        builder.setView(promptView);

        Button btn_add = promptView.findViewById(R.id.btn_addActivity);
        Button btn_cancel = promptView.findViewById(R.id.btn_cancelActivity);
        final EditText ed_sets = promptView.findViewById(R.id.addsets);
        final EditText ed_reps = promptView.findViewById(R.id.addreps);
        final EditText ed_weight = promptView.findViewById(R.id.addweight);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(ed_sets.getText().toString()) ||
                        TextUtils.isEmpty(ed_reps.getText().toString()) ||
                        TextUtils.isEmpty(ed_weight.getText().toString())) {
                    ed_reps.setError("Cannot be empty");
                } else {
                    ExerciseActivity exerciseActivity = (ExerciseActivity) getActivity();

                    Set set = new Set();
                    set.setExerciseId(exerciseActivity.getSelectedExercise().getId());
                    set.setDate(new Date());
                    set.setReps(Integer.parseInt(ed_reps.getText().toString()));
                    set.setSets(Integer.parseInt(ed_sets.getText().toString()));
                    set.setWeight(Double.parseDouble(ed_weight.getText().toString()));
                    exerciseActivity.addSet(set);
                    dismiss();

                    //   exerciseActivity.addToMovementsListView(exercise);
                    //    dismiss();
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