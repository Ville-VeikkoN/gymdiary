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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        if(findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            MovementsFragment firstFragment = new MovementsFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,firstFragment, "movementsFragment").commit();
        }
    }

    public void onClick(View view) {
        Log.d("Tag","WEhadad");
    }

    protected void addToMovementsListView(String string) {
        MovementsFragment movementsFragment = (MovementsFragment)  getSupportFragmentManager().findFragmentByTag("movementsFragment");
        movementsFragment.addToListView(string);
    }

    protected void addToExerciseListView() {
        ExerciseFragment exerciseFragment = (ExerciseFragment)  getSupportFragmentManager().findFragmentByTag("exerciseFragment");
        //METODIN KUTSU
    }

    protected void replaceFragment(ExerciseFragment fragment) {
        Log.d("Tag","Replacinfgg");

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment,"exercisesFragment");
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

}
