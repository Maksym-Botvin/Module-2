package com.botvin;

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
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter limit: ");
        int limit = scanner.nextInt();

        FileReadService fileReadService = new FileReadService();

        LinkedList<String> list = fileReadService.readCsv("csv/data.csv");

        List<Telephone> telephoneList = fileReadService.telephoneList(list);
        //telephoneList.forEach(System.out::println);

        List<Television> televisionList = fileReadService.televisionList(list);
        //televisionList.forEach(System.out::println);

        System.out.println();

        ShopService shopService = new ShopService(new OrderListRepository());
        for (int i = 0; i < 2; i++) {
            Order order = shopService.createOrder(telephoneList, televisionList, limit);
            shopService.save(order);
        }
        shopService.printAll();

        System.out.println();

        shopService.numberOfGoodsSold();





    }
}
