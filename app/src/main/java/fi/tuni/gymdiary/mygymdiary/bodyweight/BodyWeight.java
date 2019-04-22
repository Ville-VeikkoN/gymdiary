package fi.tuni.gymdiary.mygymdiary.bodyweight;

import java.util.Date;

/**
 * Represents bodyweight
 *
 * @author Ville-Veikko Nieminen
 * @version 1.8
 * @since 2019-04-21
 */
public class BodyWeight {
    /**
     * Id for the bodyweight
     */
    private int id;
    /**
     * BodyWeight for the bodyweight
     */
    private double weight;
    /**
     * Date for the bodyweight
     */
    private Date date;

    /**
     * Constructor for bodyweight.
     *
     * @param id Integer containing id for the weight
     * @param weight double containing weight for weight
     * @param date Date containing date for the weight
     */
    public BodyWeight(int id, double weight, Date date) {
        this.id = id;
        this.weight = weight;
        this.date = date;
    }

    /**
     * Empty constructor
     */
    public BodyWeight(){};

    /**
     * Returns id of the bodyweight
     *
     * @return Integer representing id of the bodyweight
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id for the bodyweight
     *
     * @param id Integer containing id for the bodyweight
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns weight of the bodyweight.
     *
     * @return Double representing weight of the bodyweight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets weight for the bodyweight.
     *
     * @param weight containing weight for the bodyweight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Returns date of the bodyweight.
     *
     * @return Date representing date of the bodyweight
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date for the bodyweight.
     *
     * @param date Date containing date for the bodyweight
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
