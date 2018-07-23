package com.lifebook.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.lifebook.Model.Weather;

@Component
public class WeatherService {
    private RestTemplate restTemplate = new RestTemplate();

    @Value("${apixu.apikey}")
    String apiKey;
    @Value("${apixu.url}")
    String url;

    public Weather fetchForcast(String zipCode, int days) {
        return restTemplate.getForObject(getUrl(zipCode, days), Weather.class);
    }

    private String getUrl(String zipCode, int days) {

        return url + "?key=" + apiKey + "&q=" + zipCode + "&days=" + days;

    }

}

