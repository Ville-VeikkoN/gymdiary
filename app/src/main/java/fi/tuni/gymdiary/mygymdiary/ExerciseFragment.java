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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ExerciseFragment extends Fragment {
    TextView textView;
    ListView listView;
    Exercise exercise;
    ArrayList<Activity> listItems;
    ArrayAdapter<String> adapter;
    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);
        textView = view.findViewById(R.id.textviewExercise);
        listView = view.findViewById(R.id.exerciselist);
        listItems = new ArrayList<>();
        fab = view.findViewById(R.id.exercisefab);
        setListView();
        setListeners();
        return view;
    }


    protected void setListView() {
     /*   adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_2,
                listItems);*/
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_2, android.R.id.text1, listItems) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
                Activity activity = listItems.get(position);
                text1.setText(activity.getSets()+" x "+activity.getReps()+" x "+activity.getWeight() + " kg");
                text1.setTextSize(20);
                text2.setText(dateFormat.format(activity.getDate()));
                return view;
            }
        };
        listView.setAdapter(adapter);
        ExerciseActivity exerciseActivity = (ExerciseActivity) getActivity();
        exerciseActivity.setActivities();
        //    listItems = dbHelper.getAllExercises();
        // adapter.notifyDataSetChanged();
    }

    protected void addToListView(Activity activity) {
        String activityString = activity.getSets()+" x "+activity.getReps()+" x "+activity.getWeight()+" kg";
        listItems.add(activity);
     //   listItems.add(exercise.getExercise());
      //  Log.d("MyTag","after db calling "+exercise.getExercise());

    //    adapter.notifyDataSetChanged();
    }

    public void exerciseSelected(Exercise exercise) {
        this.exercise = exercise;
        //textView.setText(exercise.getExercise());
    }

    public void setListeners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyExerciseDialog myDialog = new MyExerciseDialog();
                myDialog.show(getFragmentManager(),"tag");
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

                        Activity activity = new Activity();
                        activity.setExerciseId(exerciseActivity.getSelectedExercise().getId());
                        activity.setDate(new Date());
                        activity.setReps(Integer.parseInt(ed_reps.getText().toString()));
                        activity.setSets(Integer.parseInt(ed_sets.getText().toString()));
                        activity.setWeight(Double.parseDouble(ed_weight.getText().toString()));
                        exerciseActivity.addActivity(activity);
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
}
