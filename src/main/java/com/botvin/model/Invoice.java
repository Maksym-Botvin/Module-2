package com.botvin.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter

// Invoice - містить список товарів (Telephone/Television), Customer, type
public abstract class Invoice {

    private List<Goods> goodsList;
    private Customer customer;
    private InvoiceType invoiceType;

    public Invoice(List<Goods> goodsList, Customer customer, InvoiceType invoiceType) {
        this.goodsList = goodsList;
        this.customer = customer;
        this.invoiceType = invoiceType;
    }

    @Override
    public String toString() {
        return "Goods: " + goodsList + ", Customer: " + customer + ", InvoiceType: " + invoiceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return invoiceType == invoice.invoiceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceType);
    }
}
