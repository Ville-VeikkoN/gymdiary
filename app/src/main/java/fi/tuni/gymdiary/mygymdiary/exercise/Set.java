package fi.tuni.gymdiary.mygymdiary.exercise;

import java.util.Date;

public class Set {

    private int id;
    private int exerciseId;
    private int sets;
    private int reps;
    private double weight;
    private Date date;

    public Set() {};

    public Set(int sets, int reps, double weight, Date date) {
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.date = date;
    }

    public Set(int exerciseId, int sets, int reps, double weight, Date date) {
        this.exerciseId = exerciseId;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
