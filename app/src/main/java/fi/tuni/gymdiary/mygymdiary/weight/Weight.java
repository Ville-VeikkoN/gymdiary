package fi.tuni.gymdiary.mygymdiary.weight;

import java.util.Date;

/**
 * Represents bodyweight
 *
 * @author Ville-Veikko Nieminen
 * @version 1.8
 * @since 2019-04-21
 */
public class Weight {
    /**
     * Id for the weight
     */
    private int id;
    /**
     * Weight for the weight
     */
    private double weight;
    /**
     * Date for the weight
     */
    private Date date;

    /**
     * Constructor for weight.
     *
     * @param id Integer containing id for the weight
     * @param weight double containing weight for weight
     * @param date Date containing date for the weight
     */
    public Weight(int id, double weight, Date date) {
        this.id = id;
        this.weight = weight;
        this.date = date;
    }

    /**
     * Empty constructor
     */
    public Weight(){};

    /**
     * Returns id of the weight
     *
     * @return Integer representing id of the weight
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id for the weight
     *
     * @param id Integer containing id for the weight
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns weight of the weight.
     *
     * @return Double representing weight of the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets weight for the weight.
     *
     * @param weight containing weight for the weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Returns date of the weight.
     *
     * @return Date representing date of the weight
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date for the weight.
     *
     * @param date Date containing date for the weight
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
