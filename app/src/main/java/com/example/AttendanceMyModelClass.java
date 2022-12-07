package com.example;

public class AttendanceMyModelClass {
    private  String  Name,Date;

    public AttendanceMyModelClass() {
    }

    public AttendanceMyModelClass(String name, String date) {
        Name = name;
        Date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
