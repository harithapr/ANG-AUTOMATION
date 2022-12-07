package com.example;

public class FoodStockMyModel {
    private  String date,rice,grains,otherfood;


    public FoodStockMyModel(String date, String rice, String grains, String otherfood) {
        this.date = date;
        this.rice = rice;
        this.grains = grains;
        this.otherfood = otherfood;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRice() {
        return rice;
    }

    public void setRice(String rice) {
        this.rice = rice;
    }

    public String getGrains() {
        return grains;
    }

    public void setGrains(String grains) {
        this.grains = grains;
    }

    public String getOtherfood() {
        return otherfood;
    }

    public void setOtherfood(String otherfood) {
        this.otherfood = otherfood;
    }
}
