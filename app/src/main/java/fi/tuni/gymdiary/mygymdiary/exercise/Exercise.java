package fi.tuni.gymdiary.mygymdiary.exercise;

/**
 * Represents an Exercise.
 *
 * @author Ville-Veikko Nieminen
 * @version 1.8
 * @since 2019-04-21
 */
public class Exercise {

    /**
     * Id for the exercise
     */
    private int id;
    /**
     * String for exercises name
     */
    private String exercise;

    /**
     * Empty constructor for Exercise.
     */
    public Exercise() {};

    /**
     * Constructor for Exercise.
     *
     * @param exercise String containing name for exercise
     */
    public Exercise(String exercise) {
        this.exercise = exercise;
    }

    /**
     * Returns String exercise.
     *
     * @return String representing name of the excercise.
     */
    public String getExercise() {
        return exercise;
    }

    /**
     * Sets the String exercise.
     *
     * @param exercise String representing name for exercise
     */
    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    /**
     * Returns Exercises id.
     *
     * @return Integer representing id of the exercise
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the Exercises id.
     *
     * @param id Integer containing id for the exercise
     */
    public void setId(int id) {
        this.id = id;
    }
}
