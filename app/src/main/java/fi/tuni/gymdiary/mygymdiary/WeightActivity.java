package fi.tuni.gymdiary.mygymdiary;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class WeightActivity extends AppCompatActivity {
    private ListView listView;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        listView = (ListView) findViewById(R.id.weightlist);
        listItems=new ArrayList<>();
        setListView();
    }

    protected void onClick(View view) {
        MyWeightDialog myDialog = new MyWeightDialog();
        myDialog.show(getSupportFragmentManager(),"tag");
    }

    protected void setListView() {
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        listView.setAdapter(adapter);
    }

    protected void addToListView(String weight) {
        Log.d("Tag","ADDING");
        listItems.add(weight);
        adapter.notifyDataSetChanged();
    }

    public static class MyWeightDialog extends DialogFragment {
        @Override
        public Dialog onCreateDialog(final Bundle savedInstanceState) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Get the layout inflater
            final LayoutInflater inflater = requireActivity().getLayoutInflater();
            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout

            //OWN DIALOG.XML FOR THISSSSSSSSS
            View promptView = inflater.inflate(R.layout.dialog_addweight,null);
            builder.setView(promptView);

            Button btn_add = promptView.findViewById(R.id.btn_addWeight);
            Button btn_cancel = promptView.findViewById(R.id.btn_cancelWeight);
            final EditText ed_weight = promptView.findViewById(R.id.addweight);

            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String weight = ed_weight.getText().toString();
                    Log.d("Tag",""+weight);
                    if(TextUtils.isEmpty(weight)) {
                        ed_weight.setError("Cannot be empty");
                    } else {
                        WeightActivity weightActivity = (WeightActivity) getActivity();
                        weightActivity.addToListView(weight);
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

}
