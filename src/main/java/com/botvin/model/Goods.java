package com.botvin.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public abstract class Goods {
    private DeviceType deviceType;
    private String series;
    private String screenType;
    private double price;

    public Goods(DeviceType deviceType, String series, String screenType, double price) {
        this.deviceType = deviceType;
        this.series = series;
        this.screenType = screenType;
        this.price = price;
    }

    @Override
    public String toString() {
        return "deviceType: " + deviceType + ", series: " + series + ", screenType: " + screenType + ", price" + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Double.compare(goods.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
