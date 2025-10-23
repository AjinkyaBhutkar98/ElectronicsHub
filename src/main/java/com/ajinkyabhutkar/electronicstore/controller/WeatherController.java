package com.ajinkyabhutkar.electronicstore.controller;

import com.ajinkyabhutkar.electronicstore.apiresponse.WeatherResponse;
import com.ajinkyabhutkar.electronicstore.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {


    @Autowired
    WeatherService weatherService;

    @GetMapping("/{city}")
    public ResponseEntity<?> getWeather(@PathVariable String city){

        WeatherResponse weatherResponse=weatherService.getWeather(city);

        String greeting="";
        if(weatherResponse!=null){
            greeting="Hello Ajinkya!! today the weather in "+city+" feels like:"+weatherResponse.getCurrent().getWeatherDescriptions().toString();
        }
        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }
}
