package com.lifebook.Service;


public enum TaxRate {
    Maryland(2.5), DC(2.3), Texas(2.4), California(3.1);
    private double value;

    private TaxRate(double value) {
        this.value = value;
    }
};