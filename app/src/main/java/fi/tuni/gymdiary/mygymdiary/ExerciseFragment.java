package fi.tuni.gymdiary.mygymdiary;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class ExerciseFragment extends Fragment {
    TextView textView;
    ListView listView;
    String exercise;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);
        textView = view.findViewById(R.id.textviewExercise);
        textView.setText(exercise);
        listView = view.findViewById(R.id.exerciselist);
        return view;
    }

    public void exerciseSelected(Object exercise) {
        this.exercise = exercise.toString();
    }
}
