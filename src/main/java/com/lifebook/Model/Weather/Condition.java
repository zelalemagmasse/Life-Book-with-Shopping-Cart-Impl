package com.lifebook.Model.Weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Condition {

    String text;
    String icon;
    int code;
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }


}
