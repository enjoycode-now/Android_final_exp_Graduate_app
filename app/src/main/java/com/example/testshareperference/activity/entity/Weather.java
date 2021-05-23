package com.example.testshareperference.activity.entity;

public class Weather {

    private int code;
    private String msg;
    private WeatherData data;

    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }

    public void setData(WeatherData data) {
        this.data = data;
    }
    public WeatherData getData() {
        return data;
    }
}

