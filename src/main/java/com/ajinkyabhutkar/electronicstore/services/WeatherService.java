package com.ajinkyabhutkar.electronicstore.services;

import com.ajinkyabhutkar.electronicstore.apiresponse.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private static final String apiKey="cc5a4a8f26107db5d6f3ae7bf9a04e1b";

    private static final String apiUrl="http://api.weatherstack.com/current?access_key=apiKey&query=City";

    @Autowired
    private RestTemplate restTemplate;

    //example request url
//    https://api.weatherstack.com/current?access_key=cc5a4a8f26107db5d6f3ae7bf9a04e1b&query=New%20York

    //method to call resttemplate
    public WeatherResponse getWeather(String city){

        String finalApi=apiUrl.replace("apiKey",apiKey).replace("City",city);

//        ResponseEntity<WeatherResponse> weatherResponseResponseEntity =restTemplate.exchange(finalApi, HttpMethod.GET,null, WeatherResponse.class);
        WeatherResponse weatherResponse=restTemplate.getForObject(finalApi, WeatherResponse.class);

        return weatherResponse;
    }


}
