package com.springapp.mvc.model;

/**
 * Created by Dds on 30.10.2015.
 */
public class Order {

    private int id;
    private Position position;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", position=" + position +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
