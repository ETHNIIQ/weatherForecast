package com.weatherApp.weatherApp;

import com.weatherApp.weatherApp.Client.GoogleClient;
import com.weatherApp.weatherApp.Client.WeatherClient;
import feign.Feign;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
public class Controller {
//    @Autowired
//    WeatherClient getClient;

    private final WeatherClient getClient;
    private final Service service;
    @Value("${google.geolocation}")
    private String geolocation;
    public Controller(WeatherClient getClient, Service service,@Value("${google.geolocation}")String geo){
        this.getClient=getClient;
        this.service=service;
        this.geolocation=geo;
    }

    @GetMapping
    public List<WeatherDto> getAllWeather(){
        List<WeatherDto> weatherList= new ArrayList<>();
        return weatherList;
    }

    @GetMapping("weather")
    public WeatherDto getWeatherBylocation(@RequestParam("") String lat, @RequestParam String lon, @RequestParam String appid){
        WeatherDto weather= new WeatherDto();
        if(lat!=null && lon!=null){
            weather=getClient.getWeatherByID(lat,lon,appid);
            /*Coord coord = new Coord();
            coord.setLat(lat);
            coord.setLon(lon);
            weather.setCoord(coord);
            Weather weather1=new Weather();
            weather1.setDescription();
            weather.setWeather(new ArrayList<Weather>());*/
        }

//        return forecast to redirect to html thymeleaf page
        return weather;
    }

    @GetMapping("/forecast")
    public ForecastDto getPrevisionnalWeather(@RequestParam("") String lat,
                                              @RequestParam String lon,
                                              @RequestParam String appid){
        return service.getForecastDto(lat,lon,appid);
    }

    /**Check what's not working **/
    @GetMapping("/forecastByCity")
    public String cityToCoordinate (@RequestParam("city")String cityName,
                                    @RequestParam("zipCode")int zipcode,
                                    @RequestParam("country")String country
                                     ){
        /*OPTION 1
        * 1-get city name from param
        *  get country from city name with google API
        * 2-get country code iso 60 with locale object
        * 3-get zip/postal code
        * 4-rerieve appid from my uri
        * 5-make API call to get the lon lat from open weather app
        * 6- make a call to forecast uri with lon lat
        * */

        /*OPTION 2
        * 1- Use google Api to retrieve long lat from city name only
        * or to convert city to country and follow option 1
        * */
        String myKey="AIzaSyDezbSehAw5lkTecreu0Nk2bosYXKrhR9Y";
//        String URL=geolocation+"json?address=\""+cityName+"\"&key=\""+myKey+"\"";
        String URL="https://maps.googleapis.com/maps/api/geocode/json?address=paris&key=AIzaSyDezbSehAw5lkTecreu0Nk2bosYXKrhR9Y";
        GoogleClient api = Feign.builder()
                .target(GoogleClient.class, URL);
//        request
        String geolocContent= api.getGeoloc(cityName,myKey);
        //just retrieve the country that I will use bellow in the locale


        //OPTION 1 :associate country to local code for Api weather request
        Map<String,Locale> map = new HashMap<String,Locale>();
        Locale[] localeIsoList =Locale.getAvailableLocales();
        String[] localIsoSList = Locale.getISOCountries();
        for (Locale locale : localeIsoList) {
            map.put(locale.getDisplayCountry(), locale);

        }

        /*
        Option 2: for map all value and retrieve Locale
        *Map<String,String> mapbis = new HashMap<String,String>();
        for (String locale : localIsoSList) {
            Locale localvar = new Locale("en", locale);
            mapbis.put(localvar.getDisplayCountry(), locale);
        }
        * */
        Locale LocaleISO=map.get(country);

        String redirect="forecast?lat=\""+37.39+"\"&lon=\""+-122.08+"\"&appid=330179169034de0ad89d684b4712ba32";

//For mvc
        return "redirect:"+redirect;

    }
}
