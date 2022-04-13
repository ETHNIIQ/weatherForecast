package com.weatherApp.weatherApp;

import java.util.List;

public class WeatherDto {
    private Coord coord;
    private String city;
    private List<Weather>weather;


    public WeatherDto() {
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }


    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }
}
