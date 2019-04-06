package fi.tuni.gymdiary.mygymdiary;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
    MyDbHelper dbHelper;
    Exercise selectedExercise;

    MovementsFragment firstFragment;
    ExerciseFragment secondFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        dbHelper = new MyDbHelper(this);
        firstFragment = new MovementsFragment();

        if(findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,firstFragment, "movementsFragment").commit();
        }
    }

    public void onClick(View view) {
        Log.d("MyTag","WEhadad");
    }

    protected void setMovements() {
        Log.d("MyTag","setMovements " + dbHelper.getAllExercises());

        ArrayList<Exercise> movementsList = dbHelper.getAllExercises();
        for(Exercise e : movementsList) {
            firstFragment.addToListView(e);
            Log.d("MyTag","exercises" +e.getExercise());
        }
    }

    protected void addMovement(Exercise exercise) {
        dbHelper.addExercise(exercise);
        firstFragment.addToListView(exercise);
    }

    protected void setActivities() {
        Log.d("MyTag","setMovements " + dbHelper.getAllActivitiesByExercise(getSelectedExercise()));

        ArrayList<Activity> activityList = dbHelper.getAllActivitiesByExercise(getSelectedExercise());
        for(Activity a : activityList) {
            secondFragment.addToListView(a);
        }
    }

    protected void addActivity(Activity activity) {
        dbHelper.addActivity(activity);
        secondFragment.addToListView(activity);
    }

    protected void replaceFragment(ExerciseFragment fragment) {
        Log.d("Tag","Replacinfgg");
        secondFragment = fragment;

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, secondFragment,"exerciseFragment");
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
