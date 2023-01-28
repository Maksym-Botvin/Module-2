package com.botvin.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Television extends Goods {

    private float diagonal; // double
    private String country;

    public Television(DeviceType deviceType, String series, float diagonal, String screenType, String country, double price) {
        super(deviceType, series, screenType, price);
        this.diagonal = diagonal;
        this.country = country;
    }

    @Override
    public String toString() {
        return String.format("Type: %s, Series: %s, Diagonal: %.2f, Screen type: %s, Country: %s, Price: %.2f"
                , getDeviceType(), getSeries(), getDiagonal(), getScreenType(), getCountry(), getPrice());
    }


}
