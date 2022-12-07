package com.example;

public class StudentViewModelClass {
    String Name;

    public StudentViewModelClass() {
    }

    public StudentViewModelClass(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
