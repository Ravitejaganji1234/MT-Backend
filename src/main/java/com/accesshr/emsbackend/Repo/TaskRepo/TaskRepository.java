package com.accesshr.emsbackend.Repo.TaskRepo;


import java.util.*;

import com.accesshr.emsbackend.Entity.Task;

public interface TaskRepository {
    ArrayList <Task> getTasks();
    Task getTaskById(int taskId);
    Task addTask(Task task);
    Task updateTask(int taskId, Task task);
    void deleteTask(int taskId);
    int countOfTasks(String email);
    List <Task> getTasksAssignedForm(String taskAssignedById);
    List <Task> getTasksAssignedTo(String personId);
    List<Task> getOverDueTasksAssignedFrom(String taskAssignedById);
    List<Task> getPendingTasksAssignedFrom(String taskAssignedById);
    List<Task> getCompletedTasksAssignedFrom(String taskAssignedById);
    List<Task> getOverDueTasksPersonId(String personId);
    List<Task> getPendingTasksPersonId(String personId);
    List<Task> getCompletedTasksPersonId(String personId);
    HashMap<String, Integer> getTasksEfficiency(String personId);
}

