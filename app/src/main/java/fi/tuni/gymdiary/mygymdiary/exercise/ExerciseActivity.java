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

/**
 * Activity for using fragments methods and MyDbHelper for database.
 *
 * @author Ville-Veikko Nieminen
 * @version 1.8
 * @since 2019-04-21
 */
public class ExerciseActivity extends AppCompatActivity {
    /**
     * Databasehelper for using database
     */
    MyDbHelper dbHelper;
    /**
     * Exercise to tell which exercise is currently selected
     */
    Exercise selectedExercise;
    /**
     * Fragment for showing exercises
     */
    ExercisesFragment firstFragment;
    /**
     * Fragment for showing sets
     */
    SetsFragment secondFragment;

    /**
     * Method is called when ExerciseActivity is created.
     * Intializes variables and sets layout.
     *
     * @param savedInstanceState Bundle
     */
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

    /**
     * Calls methods to set all Exercises from database to the firstFragments listView
     */
    protected void setExercises() {
        ArrayList<Exercise> movementsList = dbHelper.getAllExercises();
        for(Exercise e : movementsList) {
            firstFragment.addToListView(e);
        }
    }

    /**
     * Calls methods to add Exercise to database and to ListView.
     *
     * @param exercise Exercise containing info about it
     */
    protected void addExercise(Exercise exercise) {
        dbHelper.addExercise(exercise);
        firstFragment.setListView();
    }

    /**
     * Calls methods to delete Set from database and for updating ListView for Sets.
     *
     * @param setId Integer containing id of the set
     */
    public void deleteSet(int setId) {
        dbHelper.deleteSet(setId);
        secondFragment.setListView();
    }

    /**
     * Calls methods to delete Exercise from database and for updating ListView for Exercises.
     *
     * @param exerciseId Integer containing id of the currently selected exercise
     */
    public void deleteExercise(int exerciseId) {
        dbHelper.deleteExercise(exerciseId);
        firstFragment.setListView();
    }

    /**
     * Call methods to get all Sets from database and to set those to ListView.
     */
    protected void setSets() {
        ArrayList<Set> setList = dbHelper.getAllSetsByExercise(getSelectedExercise());
        for(Set a : setList) {
            secondFragment.addToListView(a);
        }
    }

    /**
     * Calls method to get all Sets by Exercise from database and returns amount of sets.
     *
     * @param exercise Exercise containing info about exercise
     * @return Integer representing size of the list
     */
    protected int getSetsAmount(Exercise exercise) {
        ArrayList<Set> setList = dbHelper.getAllSetsByExercise(exercise);
        return setList.size();
    }

    /**
     * Calls methods for adding given Set to database and for updating ListView.
     *
     * @param set Set containing info about set
     */
    protected void addSet(Set set) {
        dbHelper.addSet(set);
        secondFragment.addToListView(set);
    }

    /**
     * Replace current fragment with a new, given one.
     *
     * @param fragment Fragment which replace current fragment
     */
    protected void replaceFragment(SetsFragment fragment) {
        Log.d("Tag","Replacinfgg");
        secondFragment = fragment;

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, secondFragment,"setsFragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * Sets selectedExercise to know which Exercise is currently selected.
     *
     * @param exercise Exercise containing info about exercise
     */
     public void setSelectedExercise(Exercise exercise) {
         this.selectedExercise = exercise;

     }

    /**
     * Returns currently selected Exercise.
     *
     * @return selectedExercise Exercise representing currently selected exercise
     */
     public Exercise getSelectedExercise() {
         return selectedExercise;
     }
}
