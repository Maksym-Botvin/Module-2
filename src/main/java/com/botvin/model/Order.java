package com.botvin.model;

import com.botvin.util.Time;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order extends Invoice {

    private String time;

    public Order(List<Goods> goodsList, Customer customer, InvoiceType invoiceType, String time) {
        super(goodsList, customer, invoiceType);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[%s] [%s] [%s, %s]", getTime(), getCustomer(), getGoodsList(), getInvoiceType());
    }

}
