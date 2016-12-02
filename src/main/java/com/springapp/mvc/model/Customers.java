package com.springapp.mvc.model;

import java.util.List;

/**
 * Created by Dds on 30.10.2015.
 */
public class Customers {

    private List<Customer> customer;

    @Override
    public String toString() {
        return "Customers{" +
                "customer=" + customer +
                '}';
    }

    public List<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = customer;
    }
}
