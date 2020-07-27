package com.lzi.myweather.entities;

import java.util.Date;

public class Weather {
    private String date;
    private double min_temperature;
    private double max_temperature;
    private double pressure;
    private int humidity;
    private String image;

    public String getDate() {
        return date;
    }

    public double getMin_temperature() {
        return min_temperature;
    }

    public double getMax_temperature() {
        return max_temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public String getImage() {
        return image;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMin_temperature(double min_temperature) {
        this.min_temperature = min_temperature;
    }

    public void setMax_temperature(double max_temperature) {
        this.max_temperature = max_temperature;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
