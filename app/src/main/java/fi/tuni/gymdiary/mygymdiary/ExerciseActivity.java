package fi.tuni.gymdiary.mygymdiary;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
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

public class ExerciseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
    }

    protected void onClick(View view) {
        Log.d("Tag","WEhadad");
    }

    /*
    private ListView listView;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_movements);
        listView = (ListView) findViewById(R.id.exerciselist);
        listItems=new ArrayList<>();
        setListView();
    }

    protected void setListView() {
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        listView.setAdapter(adapter);
    }

    protected void addToListView(String exercise) {
        Log.d("Tag","ADDING");
        listItems.add(exercise);
        adapter.notifyDataSetChanged();
    }



    protected void onClick(View view) {
        MyExerciseDialog myDialog = new MyExerciseDialog();
        myDialog.show(getSupportFragmentManager(),"tag");
    }

    public static class MyExerciseDialog extends DialogFragment {
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
                    String exercise = ed_exercise.getText().toString();
                    Log.d("Tag",""+exercise);
                    if(TextUtils.isEmpty(exercise)) {
                        ed_exercise.setError("Cannot be empty");
                    } else {
                        ExerciseActivity exerciseActivity = (ExerciseActivity) getActivity();
                        exerciseActivity.addToListView(exercise);
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
*/


}
