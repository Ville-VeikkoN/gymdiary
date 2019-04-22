package fi.tuni.gymdiary.mygymdiary.weight;

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

/**
 * Class for displaying dialog when adding bodyweight.
 *
 * @author Ville-Veikko Nieminen
 * @version 1.8
 * @since 2019-04-21
 */
public class MyWeightDialog extends DialogFragment {
    /**
     * Method is called when MyWeightDialog is created. Sets layout for Dialog.
     * Listens if Buttons is clicked and acts the way needed.
     *
     * @param savedInstanceState Bundle
     * @return Dialog representing dialog for adding bodyweight
     */
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = requireActivity().getLayoutInflater();
        View promptView = inflater.inflate(R.layout.dialog_addweight,null);
        builder.setView(promptView);

        Button btn_add = promptView.findViewById(R.id.btn_addWeight);
        Button btn_cancel = promptView.findViewById(R.id.btn_cancelWeight);
        final EditText ed_weight = promptView.findViewById(R.id.addweight);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(ed_weight.getText().toString())) {
                    ed_weight.setError("Cannot be empty");
                } else {
                    WeightActivity weightActivity = (WeightActivity) getActivity();
                    Weight weight = new Weight();
                    weight.setWeight(Double.parseDouble(ed_weight.getText().toString()));
                    weight.setDate(new Date());
                    weightActivity.addBodyWeight(weight);
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
