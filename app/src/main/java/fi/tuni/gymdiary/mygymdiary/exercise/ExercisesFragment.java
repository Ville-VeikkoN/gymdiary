package fi.tuni.gymdiary.mygymdiary.exercise;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import fi.tuni.gymdiary.mygymdiary.MyDbHelper;
import fi.tuni.gymdiary.mygymdiary.R;
import fi.tuni.gymdiary.mygymdiary.weight.Weight;

public class ExercisesFragment extends Fragment {
    View view;
    MyDbHelper dbHelper;
    private ListView listView;
    ArrayList<Exercise> listItems;
    ArrayAdapter<String> adapter;
    FloatingActionButton fab;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movements, container, false);
        dbHelper = new MyDbHelper(getActivity());
        listView = (ListView) view.findViewById(R.id.movementlist);
        listItems=new ArrayList<>();
        fab = view.findViewById(R.id.movementsfab);
        setListeners();
        setListView();
        return view;
    }

    protected void setListView() {
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_2, android.R.id.text1, listItems) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                Exercise exercise = listItems.get(position);
                text1.setText(exercise.getExercise());
                return view;
            }

        };
        listView.setAdapter(adapter);
        ExerciseActivity exerciseActivity = (ExerciseActivity) getActivity();
        exerciseActivity.setExercises();
        //    listItems = dbHelper.getAllExercises();
       // adapter.notifyDataSetChanged();
    }

    protected void addToListView(Exercise exercise) {
        listItems.add(exercise);
        Log.d("MyTag","after db calling "+exercise.getExercise());

     //   adapter.notifyDataSetChanged();
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
                    Exercise exercise = listItems.get(position);
//                    exercise.setId((int) id);
                    SetsFragment setsFragment = new SetsFragment();
                    ExerciseActivity exerciseActivity = (ExerciseActivity) getActivity();
                    exerciseActivity.setSelectedExercise(exercise);
                    setsFragment.exerciseSelected(exercise);
                    exerciseActivity.replaceFragment(setsFragment);
                }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Exercise exercise = listItems.get(position);
                AlertDialog.Builder adb=new AlertDialog.Builder(getContext());
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete selected weight information");
                final int positionToRemove = position;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Delete", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                      //  dbHelper.deleteExercise(exercise.getId());
                      //  setListView();
                    }});
                adb.show();
                return true;
            }
        });

    }

}
