package fi.tuni.gymdiary.mygymdiary.exercise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import fi.tuni.gymdiary.mygymdiary.MyDbHelper;
import fi.tuni.gymdiary.mygymdiary.R;

public class ExerciseActivity extends AppCompatActivity {
    MyDbHelper dbHelper;
    Exercise selectedExercise;

    ExercisesFragment firstFragment;
    SetsFragment secondFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        dbHelper = new MyDbHelper(this);
        firstFragment = new ExercisesFragment();

        if(findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,firstFragment, "exercisesFragment").commit();
        }
    }

    public void onClick(View view) {
        Log.d("MyTag","WEhadad");
    }

    protected void setExercises() {
        Log.d("MyTag","setMovements " + dbHelper.getAllExercises());

        ArrayList<Exercise> movementsList = dbHelper.getAllExercises();
        for(Exercise e : movementsList) {
            firstFragment.addToListView(e);
            Log.d("MyTag","exercises" +e.getExercise());
        }
    }

    protected void addMExercise(Exercise exercise) {
        dbHelper.addExercise(exercise);
        firstFragment.addToListView(exercise);
    }

    public void deleteSet() {
        dbHelper.deleteSet(getSelectedExercise().getId());
        secondFragment.setListView();
    }

    protected void setSets() {
        Log.d("MyTag","setMovements " + dbHelper.getAllSetsByExercise(getSelectedExercise()));

        ArrayList<Set> setList = dbHelper.getAllSetsByExercise(getSelectedExercise());
        for(Set a : setList) {
            secondFragment.addToListView(a);
        }
    }

    protected void addSet(Set set) {
        dbHelper.addSet(set);
        secondFragment.addToListView(set);
    }

    protected void replaceFragment(SetsFragment fragment) {
        Log.d("Tag","Replacinfgg");
        secondFragment = fragment;

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, secondFragment,"setsFragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

     /*   protected void replaceFragment(Class fragmentClass) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.fragment1, fragment)
                .commit();
    } */

     public void setSelectedExercise(Exercise exercise) {
         this.selectedExercise = exercise;

     }

     public Exercise getSelectedExercise() {
         return selectedExercise;
     }
}
