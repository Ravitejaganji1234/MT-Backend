package com.accesshr.emsbackend.Entity;

import jakarta.persistence.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity

public class Task {
    @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="taskid")
    int taskId;
    @Column (name="taskAssignedById")
    String taskAssignedById;
    @Column (name="taskAssignedByEmail")
    String taskAssignedByEmail;
    @Column (name="taskAssignedByName")
    String taskAssignedByName;
    @Column(name="personId")
    String personId;
    @Column(name="personName")
    String personName;
    @Column(name="personEmail")
    String personEmail;
    @Column (name="taskName")
    String taskName;
    @Column (name="taskDetails")
    String taskDetails;
    
    @Column (name ="effectiveDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date effectiveDate;
   
    @Column (name="dueDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date dueDate;
    @Column (name="taskStatus")
    boolean taskStatus;
    public Task(){}

    public Task(int taskId, String taskAssignedId, String taskAssignedByEmail, String taskAssignedByName, String personId, String personName, String personEmail, String taskName, String taskDetails, Date effectiveDate, Date dueDate, boolean taskStatus){
        this.taskId=taskId;
        this.taskAssignedById=taskAssignedById;
        this.taskAssignedByEmail=taskAssignedByEmail;
        this.taskAssignedByName=taskAssignedByName;
        this.personId=personId;
        this.personName=personName;
        this.personEmail=personEmail;
        this.taskName=taskName;
        this.taskDetails=taskDetails;
        this.effectiveDate=effectiveDate;
        this.dueDate=this.dueDate;
        this.taskStatus=taskStatus;
    }

    public int getTaskId(){
        return taskId;
    }

    public void setTaskId(int taskId){
        this.taskId=taskId;
    }

    public String getTaskAssignedById(){
        return taskAssignedById;
    }

    public void setTaskAssignedById(String taskAssignedById){
        this.taskAssignedById=taskAssignedById;
    }

    public String getTaskAssignedByEmail(){
        return taskAssignedByEmail;
    }

    public void setTaskAssignedByEmail(String taskAssignedByEmail){
        this.taskAssignedByEmail=taskAssignedByEmail;
    }

    public String getTaskAssignedByName(){
        return taskAssignedByName;
    }

    public void setTaskAssignedByName(String taskAssignedByName){
        this.taskAssignedByName=taskAssignedByName;
    }

    public String getPersonId(){
        return personId;
    }

    public void setPersonId(String personId){
        this.personId=personId;
    }

    public String getPersonName(){
        return personName;
    }

    public void setPersonName(String personName){
        this.personName=personName;
    }

    public String getPersonEmail(){
        return personEmail;
    }

    public void setPersonEmail(String personEmail){
        this.personEmail=personEmail;
    }

    public String getTaskName(){
        return taskName;
    }

    public void setTaskName(String taskName){
        this.taskName=taskName;
    }

    public String getTaskDetails(){
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails){
        this.taskDetails=taskDetails;
    }

    public Date getEffectiveDate(){
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate){
        this.effectiveDate=effectiveDate;
    }

    public Date getDueDate(){
        return dueDate;
    }

    public void setDueDate(Date dueDate){
        this.dueDate=dueDate;
    }

    public boolean getTaskStatus(){
        return taskStatus;
    }

    public void setTaskStatus(boolean taskStatus){
        this.taskStatus=taskStatus;
    }

}


