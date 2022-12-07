package com.example;

import java.util.ArrayList;

public class ViewAttendancemodelclass {
    ArrayList<AttendanceMyModelClass> Present;

    public ViewAttendancemodelclass() {
    }

    public ViewAttendancemodelclass(ArrayList present) {
        Present = present;
    }

    public ArrayList getPresent() {
        return Present;
    }

    public void setPresent(ArrayList present) {
        Present = present;
    }
}

