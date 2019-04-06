package fi.tuni.gymdiary.mygymdiary;

import java.util.Date;

public class Weight {

    private int id;
    private double weight;
    private Date date;

    public Weight(int id, double weight, Date date) {
        this.id = id;
        this.weight = weight;
        this.date = date;
    }

    public Weight(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
