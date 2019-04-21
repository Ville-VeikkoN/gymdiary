package fi.tuni.gymdiary.mygymdiary.exercise;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import fi.tuni.gymdiary.mygymdiary.R;
import fi.tuni.gymdiary.mygymdiary.weight.Weight;

/**
 * Fragment for displaying Sets in a ListView
 *
 * @author Ville-Veikko Nieminen
 * @version 1.8
 * @since 2019-04-21
 */
public class SetsFragment extends Fragment {
    TextView textView;
    ListView listView;
    Exercise exercise;
    ArrayList<Set> listItems;
    ArrayAdapter<String> adapter;
    FloatingActionButton fab;

    /**
     * Method called when SetsFragment is created.
     * Intializes variables, sets layout and calls needed methods.
     *
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return view
     */
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


    /**
     * Sets ListView for sets.
     */
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
                TextView text3 = (TextView) view.findViewById(R.id.text3);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
                Set set = listItems.get(position);
                text1.setText(set.getSets()+" x "+ set.getReps()+" x "+ set.getWeight() + " kg");
                text1.setTextSize(20);
                text2.setText(dateFormat.format(set.getDate()));

                long diff = new Date().getTime() - set.getDate().getTime();
                long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

                text3.setText(days +" days ago");
                text3.setTextColor(getResources().getColor(R.color.positive));

                return view;
            }
        };
        listView.setAdapter(adapter);
        ExerciseActivity exerciseActivity = (ExerciseActivity) getActivity();
        exerciseActivity.setSets();
        //    listItems = dbHelper.getAllExercises();
        // adapter.notifyDataSetChanged();
    }

    /**
     * Adds set to ArrayList and by that to ListView.
     *
     * @param set containing info about Set
     */
    protected void addToListView(Set set) {
        String activityString = set.getSets()+" x "+ set.getReps()+" x "+ set.getWeight()+" kg";
        listItems.add(set);
     //   listItems.add(exercise.getExercise());
      //  Log.d("MyTag","after db calling "+exercise.getExercise());

    //    adapter.notifyDataSetChanged();
    }

    /**
     * Intializes exercise with cuurrently selected exercise.
     *
     * @param exercise containing info about exercise
     */
    public void exerciseSelected(Exercise exercise) {
        this.exercise = exercise;
        //textView.setText(exercise.getExercise());
    }

    /**
     * Sets listeners for floating action button and ListView
     */
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
