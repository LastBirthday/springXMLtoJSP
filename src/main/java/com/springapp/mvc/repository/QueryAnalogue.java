package com.springapp.mvc.repository;

import com.springapp.mvc.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Dds on 30.10.2015.
 */
@Component
public class QueryAnalogue {

    public Iterator iterator;
    public Iterator iterator2;

    public double sumAll (Customers customers) {
        double sum = 0;
        List customerList = customers.getCustomer();
        for (iterator = customerList.iterator(); iterator.hasNext();) {
            Customer customer = (Customer) iterator.next();
            Orders orders = customer.getOrders();
            List listoforders = orders.getOrder();
            for (iterator2 = listoforders.iterator(); iterator2.hasNext(); ) {
                Order order = (Order) iterator2.next();
                Position position = order.getPosition();
                sum += position.getPrice() * position.getCount();
            }
        }
        return sum;
    }

    public String getClientWithMaxOrdersPrice (Customers customers) {
        String name = "There is no any orders";
        double sums[] = new double[customers.getCustomer().size()];
        int i = 0;
        double max = 0;
        List customerList = customers.getCustomer();
        for (iterator = customerList.iterator(); iterator.hasNext(); ) {
            sums[i] = 0;
            Customer customer = (Customer) iterator.next();
            Orders orders = customer.getOrders();
            List listoforders = orders.getOrder();
            for (iterator2 = listoforders.iterator(); iterator2.hasNext(); ) {
                Order order = (Order) iterator2.next();
                Position position = order.getPosition();
                sums[i] += position.getPrice() * position.getCount();
            }
            if (sums[i] > max) {
                max = sums[i];
                name = customer.getName();
            }
            i++;
        }
        return name;
    }

    public double getMaxOrderPrice (Customers customers) {
        double price = 0;
        List customerList = customers.getCustomer();
        for (iterator = customerList.iterator(); iterator.hasNext();) {
            Customer customer = (Customer) iterator.next();
            Orders orders = customer.getOrders();
            List listoforders = orders.getOrder();
            for (iterator2 = listoforders.iterator(); iterator2.hasNext(); ) {
                Order order = (Order) iterator2.next();
                Position position = order.getPosition();
                if (price == 0) price = position.getPrice() * position.getCount();
                else if (position.getPrice() * position.getCount() > price) price = position.getPrice() * position.getCount();
            }
        }
        return price;
    }

    public double getMinOrderPrice (Customers customers) {
        double price = 0;
        List customerList = customers.getCustomer();
        for (iterator = customerList.iterator(); iterator.hasNext();) {
            Customer customer = (Customer) iterator.next();
            Orders orders = customer.getOrders();
            List listoforders = orders.getOrder();
            for (iterator2 = listoforders.iterator(); iterator2.hasNext(); ) {
                Order order = (Order) iterator2.next();
                Position position = order.getPosition();
                if (price == 0) price = position.getPrice() * position.getCount();
                else if (position.getPrice() * position.getCount() < price) price = position.getPrice() * position.getCount();
            }
        }
        return price;
    }

    public int getOrdersCount (Customers customers) {
        int count = 0;
        List customerList = customers.getCustomer();
        for (iterator = customerList.iterator(); iterator.hasNext();) {
            Customer customer = (Customer) iterator.next();
            Orders orders = customer.getOrders();
            List listoforders = orders.getOrder();
            for (iterator2 = listoforders.iterator(); iterator2.hasNext(); ) {
                count++;
                iterator2.next();
            }
        }
        return count;
    }

    public double avgOrdersPrice (double avg, int count) {
        return avg / (double) count;
    }

    public List getCustomersNames (Customers customers, Double price) {
        double sums[] = new double[customers.getCustomer().size()];
        int i = 0;
        List<String> names = new ArrayList<String>();
        List customerList = customers.getCustomer();
        for (iterator = customerList.iterator(); iterator.hasNext(); ) {
            sums[i] = 0;
            Customer customer = (Customer) iterator.next();
            Orders orders = customer.getOrders();
            List listoforders = orders.getOrder();
            for (iterator2 = listoforders.iterator(); iterator2.hasNext(); ) {
                Order order = (Order) iterator2.next();
                Position position = order.getPosition();
                sums[i] += position.getPrice() * position.getCount();
            }
            if (sums[i] > price) names.add(customer.getName());
            i++;
        }
        return names;
    }

}
