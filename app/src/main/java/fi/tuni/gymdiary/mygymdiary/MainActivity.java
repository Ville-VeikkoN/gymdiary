package fi.tuni.gymdiary.mygymdiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void buttonClicked(View v) {
        if(v.getId() == R.id.exercises) {
            Intent i = new Intent(this, ExerciseActivity.class);
            startActivity(i);
        } else if (v.getId() == R.id.weight) {
            Intent i = new Intent(this, WeightActivity.class);
            startActivity(i);
        } else if(v.getId() == R.id.counter) {
            Intent i = new Intent(this, CounterActivity.class);
            startActivity(i);
        }
    }
}