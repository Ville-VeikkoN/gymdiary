package fi.tuni.gymdiary.mygymdiary.exercise;

import java.util.Date;

/**
 * Represents the Set.
 *
 * @author Ville-Veikko Nieminen
 * @version 1.8
 * @since 2019-04-21
 */
public class Set {

    private int id;
    private int exerciseId;
    private int sets;
    private int reps;
    private double weight;
    private Date date;

    /**
     * Empty constructor for Set.
     */
    public Set() {};


    /**
     * Returns Sets id.
     *
     * @return Integer representing id for Set
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id for Set.
     *
     * @param id Integer containing id for Set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns Sets exerciseId which tells which is the Exercise for this Set.
     *
     * @return Integer representing Sets exerciseId
     */
    public int getExerciseId() {
        return exerciseId;
    }

    /**
     * Sets exerciseId for the Set.
     *
     * @param exerciseId Integer containing the exerciseId
     */
    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    /**
     * Returns amount of Sets.
     *
     * @return Integer representing amount of sets.
     */
    public int getSets() {
        return sets;
    }

    /**
     * Sets amount of sets.
     *
     * @param sets Integer containing amount of sets.
     */
    public void setSets(int sets) {
        this.sets = sets;
    }

    /**
     * Returns amount of reps.
     *
     * @return Integer representing amount of reps
     */
    public int getReps() {
        return reps;
    }

    /**
     * Sets the amount of reps.
     *
     * @param reps Integer containing amount of reps.
     */
    public void setReps(int reps) {
        this.reps = reps;
    }

    /**
     * Returns used weight.
     *
     * @return double representing used weight.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets used weight.
     *
     * @param weight double containing used weight.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Returns date when the Set is created.
     *
     * @return Date representing the date when Set was done.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date for Set
     *
     * @param date containing Date when Set was done
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
