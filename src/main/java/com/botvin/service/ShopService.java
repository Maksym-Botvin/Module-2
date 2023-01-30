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

    // 1. К-сть проданих товарів за категоріями (Telephone/Television)
    public Map<Object, Long> numberOfGoodsSold() {
        Map<Object, Long> collect = orderListRepository.getAll().stream()
                .flatMap(list -> list.getGoodsList().stream())
                .collect(Collectors.groupingBy(
                        x -> x.getDeviceType(),
                        Collectors.counting()
                ));
        return collect;
    }

    // 2. Суму найменшого чека та інформацію про покупця
    /*public Map<Object, Long> minSumAndCustomerInfo() {
        Map<Object, Long> collect = orderListRepository.getAll().stream()

    }*/

    // 3. Суму всіх покупок
    public double totalOfAllPurchases() {
        double total = orderListRepository.getAll().stream()
                .flatMap(list -> list.getGoodsList().stream()) // list of telephones/televisions
                .mapToInt(x -> (int) x.getPrice())
                .sum();
        return total;
    }

    // 4. К-сть чеків з категорією retail
    public long numberOfRetailChecks() {
        long checks = orderListRepository.getAll().stream()
                .filter(x -> x.getInvoiceType().equals(InvoiceType.RETAIL))
                .count();
        return checks;
    }

    // 5. Чеки, які містять тільки один тип товару
    // заходимо в чек, рахуємо к-сть телефонів/телевізорів,
    // якщо к-сть = 1, то додаємо цей чек в ліст
    /*public List<Order> oneTypeOfProduct() {
        long telephones = orderListRepository.getAll().stream()
                *//*.flatMap(list -> list.getGoodsList().stream()
                        .filter(x -> x.getDeviceType().equals(DeviceType.TELEPHONE)))
                .count();*//*
    }*/

    // 6. Перші три чеки зроблені покупцями


    // 7. Інформацію по чеках куплених користувачем молодше 18 років, при цьому змінити тип чека на low_age
    public List<Order> lowAgeOrders() {
        List<Order> lowAgeOrders = orderListRepository.getAll().stream()
                .filter(x -> x.getCustomer().getAge() < 18)
                .peek(y -> y.setInvoiceType(InvoiceType.LOW_AGE))
                .collect(Collectors.toList());
        return lowAgeOrders;
    }

    // 8. Написати метод, який сортує (будь-яким відомим способом) усі замовлення в
    //такому порядку:
    //○ Спочатку за віком покупця від більшого до меншого
    //○ Далі за кількістю куплених предметів
    //○ Далі за загальною сумою куплених предметів


}
