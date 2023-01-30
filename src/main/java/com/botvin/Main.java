package com.botvin;

import com.botvin.model.Goods;
import com.botvin.model.Order;
import com.botvin.model.Telephone;
import com.botvin.model.Television;
import com.botvin.repository.OrderListRepository;
import com.botvin.service.AnalyticService;
import com.botvin.service.FileReadService;
import com.botvin.service.ShopService;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Enter limit: ");
        int limit = scanner.nextInt();*/
        int limit = 1000;

        FileReadService fileReadService = new FileReadService();

        LinkedList<String> list = fileReadService.readCsv("csv/data.csv");

        List<Telephone> telephoneList = fileReadService.telephoneList(list);
        //telephoneList.forEach(System.out::println);

        List<Television> televisionList = fileReadService.televisionList(list);
        //televisionList.forEach(System.out::println);

        System.out.println("*".repeat(50) + " Generated orders: " + "*".repeat(50));

        ShopService shopService = new ShopService(new OrderListRepository());
        for (int i = 0; i < 15; i++) { // i < 15 -> generate 15 orders
            Order order = shopService.createOrder(telephoneList, televisionList, limit);
            shopService.save(order);
        }
        shopService.printAll();

        System.out.println("*".repeat(50) + " 1-st method's result: " + "*".repeat(50));

        Map<Object, Long> map = shopService.numberOfGoodsSold();
        for (Map.Entry<Object, Long> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
        }

        System.out.println("*".repeat(50) + " 3-rd method's result: " + "*".repeat(50));

        double sumOfAllPurchases = shopService.totalOfAllPurchases();
        System.out.println(sumOfAllPurchases + " - total of all purchases");

        System.out.println("*".repeat(50) + " 4-th method's result: " + "*".repeat(50));

        long checks = shopService.numberOfRetailChecks();
        System.out.println(checks + " - the number of checks with the retail category");

        System.out.println("*".repeat(50) + " 7-th method's result: " + "*".repeat(50));

        List<Order> lowAgeOrders = shopService.lowAgeOrders();
        lowAgeOrders.forEach(System.out::println);





    }
}
