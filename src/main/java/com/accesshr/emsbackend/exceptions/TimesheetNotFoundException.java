package com.accesshr.emsbackend.exceptions;


import com.accesshr.emsbackend.Entity.Timesheet;

import java.util.List;

public class TimesheetNotFoundException extends  RuntimeException{

    public TimesheetNotFoundException(String employeeId) {

        super(employeeId);
    }


    public TimesheetNotFoundException(Long id) {

        super("Timesheet not found with ID: " + id);
    }

    public TimesheetNotFoundException(List<Timesheet> timesheet) {

        super("Timesheet is empty: " + timesheet);
    }


    public TimesheetNotFoundException(String managerId, Timesheet.Status status) {
        super("No timesheet found with Manager ID: " + managerId + " and Status: " + status);
    }

    public TimesheetNotFoundException(Timesheet.Status status){
        super("There is no timesheet with status: " + status);
    }





}
