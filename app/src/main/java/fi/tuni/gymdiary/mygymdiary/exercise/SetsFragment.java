package fi.tuni.gymdiary.mygymdiary.exercise;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
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

import fi.tuni.gymdiary.mygymdiary.R;

public class SetsFragment extends Fragment {
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
                MySetsDialog myDialog = new MySetsDialog();
                myDialog.show(getFragmentManager(),"tag");
            }
        });
    }

}
