package com.botvin.repository;

import com.botvin.model.Order;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class OrderListRepository {

    private static LinkedList<Order> orders = new LinkedList<>();

    public LinkedList<Order> save(final Order order) {
        orders.add(order);
        return orders;
    }

    public LinkedList<Order> getAll() {
        LinkedList<Order> orders1 = new LinkedList<>();
        for (Order order : orders) {
            orders1.add(order);
        }
        return orders1;
    }

}
