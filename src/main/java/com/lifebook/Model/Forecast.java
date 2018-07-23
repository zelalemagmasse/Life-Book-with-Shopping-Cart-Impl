package com.lifebook.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

