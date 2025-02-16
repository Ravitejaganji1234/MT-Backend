package com.accesshr.emsbackend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Data
@NoArgsConstructor
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private String employeeId;

    private String employeeName;

    private double numberOfHours;
    private double extraHours;

    @Column(name = "reporting_manager")
    private String reportingManager;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "project_name")
    private String projectName;


    private String manager;

    @Column(name = "manager_Id", nullable = false)
    private String managerId;

    private String taskType;
    @Column(name = "work_location")
    private String workLocation;

    private String taskDescription;
    private String emailId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;  // Start date for the task
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Column(name = "on_call_support")
    private boolean onCallSupport;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PENDING,
        APPROVED,
        REJECTED
    }
    private String comments;






}
