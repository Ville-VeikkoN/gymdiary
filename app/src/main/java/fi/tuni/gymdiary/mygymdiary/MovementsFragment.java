package fi.tuni.gymdiary.mygymdiary;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MovementsFragment extends Fragment {
    View view;
    private ListView listView;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    FloatingActionButton fab;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movements, container, false);
        listView = (ListView) view.findViewById(R.id.exerciselist);
        listItems=new ArrayList<>();
        fab = view.findViewById(R.id.movementsfab);
        setListeners();
        setListView();
        return view;
    }

    protected void setListView() {
        adapter=new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,
                listItems);
        listView.setAdapter(adapter);
    }

    protected void addToListView(String exercise) {
        Log.d("Tag","ADDING");
        listItems.add(exercise);
        adapter.notifyDataSetChanged();
    }

    protected void setListeners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyExerciseDialog myDialog = new MyExerciseDialog();
                myDialog.show(getFragmentManager(),"tag");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("MyTag", position +"  " + parent.getAdapter().getItem(position));
                    ExerciseFragment exerciseFragment = new ExerciseFragment();
                    exerciseFragment.exerciseSelected(parent.getAdapter().getItem(position));
                    ((ExerciseActivity) getActivity()).replaceFragment(exerciseFragment);
                }
        });

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
                        exerciseActivity.addToMovementsListView(exercise);
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
