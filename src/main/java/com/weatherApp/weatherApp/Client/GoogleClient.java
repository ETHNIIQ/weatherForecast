package com.weatherApp.weatherApp.Client;

import com.fasterxml.jackson.databind.JsonNode;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(name = "google_call",value = "${google.geolocation}")
public interface GoogleClient {

//  the endpoint of CONSUMED URL associated
    @RequestLine("GET")
      //                +"json?address=\""+cityName+"\"&key=\""+myKey+"\"";
//      with method I will used inside my controller to fetchdata
    @Headers("Content-Type: application/json")
    Object getGeoloc(@Param("adress")String city, @Param ("key")String key);

}
