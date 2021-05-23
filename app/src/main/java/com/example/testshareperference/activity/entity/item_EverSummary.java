package com.example.testshareperference.activity.entity;

public class item_EverSummary {
    String date;
    String content;

    public item_EverSummary() {
    }

    public item_EverSummary(String date, String content) {
        this.date = date;
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
