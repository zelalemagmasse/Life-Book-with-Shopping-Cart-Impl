package com.lifebook.Model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecastday {
    Date date;
    Day day;
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Day getDay() {
        return day;
    }
    public void setDay(Day day) {
        this.day = day;
    }

}
