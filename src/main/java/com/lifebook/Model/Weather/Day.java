package com.lifebook.Model.Weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Day {

    double  maxtemp_f;
    double  mintemp_f;
    double avgtemp_f;
    double maxwind_mph;
    double totalprecip_in;
    double avgvis_miles;
    double avghumidity;
    Condition condition;

    public double getMaxtemp_f() {
        return maxtemp_f;
    }
    public void setMaxtemp_f(double maxtemp_f) {
        this.maxtemp_f = maxtemp_f;
    }
    public double getMintemp_f() {
        return mintemp_f;
    }
    public void setMintemp_f(double mintemp_f) {
        this.mintemp_f = mintemp_f;
    }
    public double getAvgtemp_f() {
        return avgtemp_f;
    }
    public void setAvgtemp_f(double avgtemp_f) {
        this.avgtemp_f = avgtemp_f;
    }
    public double getMaxwind_mph() {
        return maxwind_mph;
    }
    public void setMaxwind_mph(double maxwind_mph) {
        this.maxwind_mph = maxwind_mph;
    }
    public double getTotalprecip_in() {
        return totalprecip_in;
    }
    public void setTotalprecip_in(double totalprecip_in) {
        this.totalprecip_in = totalprecip_in;
    }
    public double getAvgvis_miles() {
        return avgvis_miles;
    }
    public void setAvgvis_miles(double avgvis_miles) {
        this.avgvis_miles = avgvis_miles;
    }
    public double getAvghumidity() {
        return avghumidity;
    }
    public void setAvghumidity(double avghumidity) {
        this.avghumidity = avghumidity;
    }
    public Condition getCondition() {
        return condition;
    }
    public void setCondition(Condition condition) {
        this.condition = condition;
    }




}


