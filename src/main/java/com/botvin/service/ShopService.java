package com.botvin.service;

import com.botvin.model.*;
import com.botvin.repository.OrderListRepository;
import com.botvin.util.Time;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public class ShopService {

    private OrderListRepository orderListRepository;
    private final Random random = new Random();

    public ShopService(OrderListRepository orderListRepository) {
        this.orderListRepository = orderListRepository;
    }

    public Order createOrder(final List<Telephone> telephones, List<Television> televisions, int limit) {
        final List list = Stream.concat(telephones.stream(), televisions.stream()).toList();
        List<Goods> goodsList = new ArrayList<>();
        for (int i = 0; i < random.nextInt(1, 6); i++) {
            goodsList.add((Goods) list.get(random.nextInt(0, list.size())));
        }
        int sum = goodsList.stream()
                .mapToInt(x -> (int) x.getPrice())
                .sum();
        //System.out.println(sum);
        InvoiceType invoiceType = sum > limit ? InvoiceType.WHOLESALE : InvoiceType.RETAIL;
        Customer customer = PersonService.createRandomCustomer();
        String time = Time.getCurrentDate();
        Order order = new Order(goodsList, customer, invoiceType, time);
        return order;
    }

    public void save(final Order order) {
        orderListRepository.save(order);
    }

    public void printAll() {
        LinkedList<Order> orders = orderListRepository.getAll();
        orders.forEach(System.out::println);
    }

    public void numberOfGoodsSold() {
        List<Order> numberOfGoodsSold = orderListRepository.getAll().stream()
                .filter(o -> o.getGoodsList().contains(DeviceType.TELEVISION))
                .collect(Collectors.toList());
        numberOfGoodsSold.forEach(System.out::println);
    }


}
