package com.lifebook.Model.Weather;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lifebook.Model.Weather.Forecastday;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast {
    List<Forecastday> forecastday;

    public List<Forecastday> getForecastday() {
        return forecastday;
    }

    public void setForecastday(List<Forecastday> forecastday) {
        this.forecastday = forecastday;
    }

}

