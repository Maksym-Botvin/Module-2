package com.botvin.service;

import com.botvin.model.*;
import com.botvin.repository.OrderListRepository;
import com.botvin.util.Time;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
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

    // К-сть проданих товарів за категоріями (Telephone/Television)
    public Map<Object, Long> numberOfGoodsSold() {
        Map<Object, Long> collect = orderListRepository.getAll().stream()
                .flatMap(list -> list.getGoodsList().stream())
                .collect(Collectors.groupingBy(
                        x -> x.getDeviceType(),
                        Collectors.counting()
                ));
        return collect;
    }

    // Суму найменшого чека та інформацію про покупця
    /*public Map<Object, Long> minSumAndCustomerInfo() {
        Map<Object, Long> collect = orderListRepository.getAll().stream()

    }*/




}
