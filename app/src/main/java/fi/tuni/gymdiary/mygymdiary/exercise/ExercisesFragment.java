package fi.tuni.gymdiary.mygymdiary.exercise;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import fi.tuni.gymdiary.mygymdiary.MyDbHelper;
import fi.tuni.gymdiary.mygymdiary.R;

public class ExercisesFragment extends Fragment {
    View view;
    MyDbHelper dbHelper;
    private ListView listView;
    ArrayList<String> listItems;
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
        adapter=new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,
                listItems);
        listView.setAdapter(adapter);
        ExerciseActivity exerciseActivity = (ExerciseActivity) getActivity();
        exerciseActivity.setMovements();
        //    listItems = dbHelper.getAllExercises();
       // adapter.notifyDataSetChanged();
    }

    protected void addToListView(Exercise exercise) {
        listItems.add(exercise.getExercise());
        Log.d("MyTag","after db calling "+exercise.getExercise());

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
                    Exercise exercise = new Exercise(parent.getAdapter().getItem(position).toString());
                    exercise.setId((int) id);
                    SetsFragment setsFragment = new SetsFragment();
                    ExerciseActivity exerciseActivity = (ExerciseActivity) getActivity();
                    exerciseActivity.setSelectedExercise(exercise);
                    setsFragment.exerciseSelected(exercise);
                    exerciseActivity.replaceFragment(setsFragment);
                }
        });

    }

}
