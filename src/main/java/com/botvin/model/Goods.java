package com.botvin.model;

import lombok.Getter;
import lombok.Setter;

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

}
