package com.example;

public class foodreportmodelclass {
    public  String date,url;

    public foodreportmodelclass() {
    }

    public foodreportmodelclass(String date, String url) {
        this.date = date;
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
