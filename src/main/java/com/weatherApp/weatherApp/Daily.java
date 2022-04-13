package com.weatherApp.weatherApp;

import com.weatherApp.weatherApp.Weather;

import java.util.List;


public class Daily {
    private double dt;
    private double sunrise;
    private double sunset;
    private double day;
    private List<Weather> weather;

    public Daily() {

    }

    public double getDt() {
        return dt;
    }

    public void setDt(double dt) {
        this.dt = dt;
    }

    public double getSunrise() {
        return sunrise;
    }

    public void setSunrise(double sunrise) {
        this.sunrise = sunrise;
    }

    public double getSunset() {
        return sunset;
    }

    public void setSunset(double sunset) {
        this.sunset = sunset;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public double getDay() {
        return day;
    }

    public void setDay(double day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Daily{" +
                "dt=" + dt +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                ", day=" + day +
                ", weather=" + weather +
                '}';
    }
}
