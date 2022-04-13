package com.weatherApp.weatherApp;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    private  String restURL;
    public Service ( @Value("${rest.url}")String restURL){
        this.restURL=restURL;
    }

    @Autowired
    private RestTemplate restTemplate;


    //option1
    public ForecastDto getForecastDto(String lat, String lon, String appkey) {
        String URI_FORECAST_WEATHER= restURL+"?lat="+lat+"&lon="+lon+"&appid="+appkey+"&exclude=minutely,hourly";
        String result = restTemplate.getForObject(URI_FORECAST_WEATHER, String.class);
        ForecastDto forecastDto=new ForecastDto();

        try {
            var jsonObject = new JSONObject(result);
            JSONArray dailyWeather= jsonObject.getJSONArray("daily");
            List<Daily> dailyList=new ArrayList<>();
            for(int i=0; i<dailyWeather.length();i++){
                Daily daily= new Daily();
                Weather weather=new Weather();
               var jsonDaily= dailyWeather.getJSONObject(i);
               var jsonWeather=jsonDaily.getJSONArray("weather");
               var temp=jsonDaily.getJSONObject("temp");

                System.out.println(jsonWeather.length());
                for(int j=0; j< jsonWeather.length();j++){
                    List<Weather> weatherList=new ArrayList<>();
                    String weatherIcon=jsonWeather.getJSONObject(j).getString("icon");
                   String weatherDescription=jsonWeather.getJSONObject(j).getString("description");
                   String weatherMain=jsonWeather.getJSONObject(j).getString("main");
                   int weatherId=jsonWeather.getJSONObject(j).getInt("id");
                    weather.setMain(weatherIcon);
                    weather.setDescription(weatherDescription);
                    weather.setMain(weatherMain);
                    weather.setId(weatherId);
                    weatherList.add(weather);
                    daily.setWeather(weatherList);

                }
                var dailyWeatherDt=jsonDaily.getDouble("dt");
                var dailyWeatherTempDay=temp.getInt("day");
                daily.setDt(dailyWeatherDt);
                daily.setDay(dailyWeatherTempDay);
                dailyList.add(daily);
            }
            forecastDto.setDaily(dailyList);
        }
        catch (Exception e ){
            e.printStackTrace();       }


        return forecastDto;
    }



}
