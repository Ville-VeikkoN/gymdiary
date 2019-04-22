package fi.tuni.gymdiary.mygymdiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import fi.tuni.gymdiary.mygymdiary.counter.CounterActivity;
import fi.tuni.gymdiary.mygymdiary.exercise.ExerciseActivity;
import fi.tuni.gymdiary.mygymdiary.bodyweight.BodyweightActivity;

/**
 * MainActivity
 *
 * @author Ville-Veikko Nieminen
 * @version 1.8
 * @since 2019-04-21
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Method called when MainActivity is created.
     * Sets layout.
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Called when button is clicked. Checks which button it was and acts like wanted.
     *
     * @param v View containing button which was clicked
     */
    public void buttonClicked(View v) {
        if(v.getId() == R.id.exercises) {
            Intent i = new Intent(this, ExerciseActivity.class);
            startActivity(i);
        } else if (v.getId() == R.id.weight) {
            Intent i = new Intent(this, BodyweightActivity.class);
            startActivity(i);
        } else if(v.getId() == R.id.counter) {
            Intent i = new Intent(this, CounterActivity.class);
            startActivity(i);
        }
    }
}