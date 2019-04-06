package fi.tuni.gymdiary.mygymdiary;

import java.util.Date;

public class Exercise {

    private int id;

    private String exercise;

    public Exercise() {};

    public Exercise(String exercise) {
        this.exercise = exercise;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
