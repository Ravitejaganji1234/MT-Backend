package com.accesshr.emsbackend.Dto.TimesheetDTO;

import com.accesshr.emsbackend.Entity.Timesheet;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TimesheetDTO {
    private Long id;  // ID of the timesheet
    private String employeeId;  // ID of the employee
    private String employeeName;
    private double numberOfHours;  // Normal working hours
    private double extraHours;  // Extra hours worked
    private String reportingManager;  // Reporting manager's name
    private String clientName;  // Client's name
    private String projectName;  // Name of the project
    private String taskType;  // Task type name
    private String workLocation;  // Work location
    private String taskDescription;  // Description of the task
    private String emailId;  // Employee's email ID
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;  // Start date for the task
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private boolean onCallSupport;  // On-call support indicator
    private Timesheet.Status status;
    private double totalNumberOfHours;
    private String comments;
    private String manager;
    private String managerId;



}