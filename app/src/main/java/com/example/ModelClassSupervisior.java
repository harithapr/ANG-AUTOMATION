package com.example;

public class ModelClassSupervisior {
    private String Supervisor_Duties, Supervisor_Duty_Date;

    public ModelClassSupervisior() {
    }

    public String getSupervisor_Duties() {
        return Supervisor_Duties;
    }

    public void setSupervisor_Duties(String supervisor_Duties) {
        Supervisor_Duties = supervisor_Duties;
    }

    public ModelClassSupervisior(String supervisor_Duties, String supervisor_Duty_Date, String supervisor_ID) {
        Supervisor_Duties = supervisor_Duties;
        Supervisor_Duty_Date = supervisor_Duty_Date;
      //  Supervisor_ID = supervisor_ID;
    }

    public String getSupervisor_Duty_Date() {
        return Supervisor_Duty_Date;
    }

    public void setSupervisor_Duty_Date(String supervisor_Duty_Date) {
        Supervisor_Duty_Date = supervisor_Duty_Date;
    }

   // public String getSupervisor_ID() {
    //    return Supervisor_ID;
   // }

   // public void setSupervisor_ID(String supervisor_ID) {
      //  Supervisor_ID = supervisor_ID;
    //}
}