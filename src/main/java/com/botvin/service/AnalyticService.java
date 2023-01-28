package com.botvin.service;

import com.botvin.model.DeviceType;
import com.botvin.model.Invoice;
import com.botvin.model.Order;
import com.botvin.model.Telephone;
import com.botvin.repository.OrderListRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class AnalyticService {

    /*private OrderListRepository orderListRepository;

    public AnalyticService(OrderListRepository orderListRepository) {
        this.orderListRepository = orderListRepository;
    }

    public void numberOfGoodsSold() {
        List<Object> objects = orderListRepository.getAll().stream()
                .filter(order -> order.getGoodsList().contains(DeviceType.TELEPHONE))
                .collect(Collectors.toList());
        System.out.println(objects.size());
    }*/
}
