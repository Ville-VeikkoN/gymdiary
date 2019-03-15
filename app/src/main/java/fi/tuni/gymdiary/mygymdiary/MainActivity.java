package fi.tuni.gymdiary.mygymdiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void buttonClicked(View view) {
        if(view.getId() == R.id.exercises) {
            Intent intent = new Intent(this, ExerciseActivity.class);
            startActivity(intent);
        }
    }
}
