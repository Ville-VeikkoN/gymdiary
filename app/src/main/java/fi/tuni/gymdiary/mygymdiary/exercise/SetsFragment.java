package fi.tuni.gymdiary.mygymdiary.exercise;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fi.tuni.gymdiary.mygymdiary.R;

public class SetsFragment extends Fragment {
    TextView textView;
    ListView listView;
    Exercise exercise;
    ArrayList<Set> listItems;
    ArrayAdapter<String> adapter;
    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);
        textView = view.findViewById(R.id.textviewExercise);
        textView.setText(exercise.getExercise());
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
        listItems.clear();
        adapter = new ArrayAdapter(getActivity(), R.layout.mysimple_list_layout, R.id.text1, listItems) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(R.id.text1);
                TextView text2 = (TextView) view.findViewById(R.id.text2);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
                Set set = listItems.get(position);
                text1.setText(set.getSets()+" x "+ set.getReps()+" x "+ set.getWeight() + " kg");
                text1.setTextSize(20);
                text2.setText(dateFormat.format(set.getDate()));
                return view;
            }
        };
        listView.setAdapter(adapter);
        ExerciseActivity exerciseActivity = (ExerciseActivity) getActivity();
        exerciseActivity.setSets();
        //    listItems = dbHelper.getAllExercises();
        // adapter.notifyDataSetChanged();
    }

    protected void addToListView(Set set) {
        String activityString = set.getSets()+" x "+ set.getReps()+" x "+ set.getWeight()+" kg";
        listItems.add(set);
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Set set = listItems.get(position);
                AlertDialog.Builder adb=new AlertDialog.Builder(getContext());
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete selected set");
                final int positionToRemove = position;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Delete", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ExerciseActivity exerciseActivity = (ExerciseActivity) getActivity();
                        exerciseActivity.deleteSet(set.getId());
                    }});
                adb.show();
            }
        /*        ConfirmDeleteDialog deleteDialog = new ConfirmDeleteDialog();
                Bundle args = new Bundle();
                Set set = listItems.get(position);
                args.putInt("setId", set.getId());
                deleteDialog.setArguments(args);
                deleteDialog.show(getFragmentManager(),"tag");*/
            //}
        });
    }

}
