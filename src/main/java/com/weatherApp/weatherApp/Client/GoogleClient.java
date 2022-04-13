package com.weatherApp.weatherApp.Client;

import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(name = "google_call",value = "${google.geolocation}")
public interface GoogleClient {

//  the endpoint of CONSUMED URL associated
    @RequestLine("GET/{adress}{myKey}")
      //                +"json?address=\""+cityName+"\"&key=\""+myKey+"\"";
//      with method I will used inside my controller to fetchdata
    String getGeoloc(@RequestParam ("adress")String city,@RequestParam ("myKey")String key);

}
