package com.springapp.mvc.model;

import java.util.List;

/**
 * Created by Dds on 30.10.2015.
 */
public class Orders {

    private List<Order> order;

    @Override
    public String toString() {
        return "Orders{" +
                "order=" + order +
                '}';
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }
}
