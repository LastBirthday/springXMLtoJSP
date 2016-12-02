package com.springapp.mvc.model;

/**
 * Created by Dds on 30.10.2015.
 */
public class Position {

    private int id;
    private Double price;
    private int count;

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", price=" + price +
                ", count=" + count +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
