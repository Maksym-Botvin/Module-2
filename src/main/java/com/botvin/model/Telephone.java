package com.botvin.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Telephone extends Goods {

    private String model;

    public Telephone(DeviceType deviceType, String series, String model, String screenType, double price) {
        super(deviceType, series, screenType, price);
        this.model = model;
    }

    @Override
    public String toString() {
        return String.format("Type: %s, Series: %s, Model: %s, Screen type: %s, Price: %.2f"
                , getDeviceType(), getSeries(), getModel(), getScreenType(), getPrice());
    }


}
