package com.weatherApp.weatherApp.Client;

import com.weatherApp.weatherApp.ForecastDto;
import com.weatherApp.weatherApp.WeatherDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "${feign.name}", url = "${feign.url}")
public interface WeatherClient {

    @GetMapping(name = "",path = "/weather")
     List<WeatherDto> getAllWeather();

//    default url I will CONSUME
    @GetMapping(name = "",path = "weather")
//   method I will call to fetch from consumed url should have same param as controller method signature in client api
     WeatherDto getWeatherByID(@RequestParam String lat, @RequestParam String lon, @RequestParam String appid);

    @GetMapping("onecall")
    ForecastDto getFiveForecast(@RequestParam String lat,@RequestParam String lon, @RequestParam String appid);
}
