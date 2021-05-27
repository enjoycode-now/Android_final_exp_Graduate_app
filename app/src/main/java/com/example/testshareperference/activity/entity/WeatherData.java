package com.example.testshareperference.activity.entity;

public class WeatherData {

    private String address;

    private String citycode;
    private String temp;
    private String weather;

    private String winddirection;

    private String windpower;
    private String humidity;

    private String reporttime="";



    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }
    public String getCitycode() {
        return citycode;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
    public String getTemp() {
        return temp;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
    public String getWeather() {
        return weather;
    }

    public void setWinddirection(String winddirection) {
        this.winddirection = winddirection;
    }
    public String getWinddirection() {
        return winddirection;
    }

    public void setWindpower(String windpower) {
        this.windpower = windpower;
    }
    public String getWindpower() {
        return windpower;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
    public String getHumidity() {
        return humidity;
    }


    public String getReporttime() {
        return this.reporttime;
    }
}
