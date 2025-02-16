package com.accesshr.emsbackend.Service.TaskService;


import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.http.HttpStatus;
  import org.springframework.stereotype.Service;
  import org.springframework.web.server.ResponseStatusException;

import com.accesshr.emsbackend.Entity.*;
import com.accesshr.emsbackend.Repo.TaskRepo.*;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
  import java.util.*;

  @Service 
public class TaskJpaService implements TaskRepository {
    @Autowired 
    public TaskJpaRepository taskRepository;
    @Autowired
    private JavaMailSender emailSender;


    @Override 

    public void deleteTask(int taskId){
      try{
        taskRepository.deleteById(taskId);
      }catch (Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }

    @Override 

    public Task updateTask(int taskId, Task task){
      try{
        Task newTask=taskRepository.findById(taskId).get();
        if(task.getTaskAssignedById()!=null){
          newTask.setTaskAssignedById(task.getTaskAssignedById());
        }
        if(task.getTaskAssignedByEmail()!=null){
          newTask.setTaskAssignedByEmail(task.getTaskAssignedByEmail());
        }
        if(task.getTaskAssignedByName()!=null){
          newTask.setTaskAssignedByName(task.getTaskAssignedByName());
        }
        if(task.getPersonId()!=null){
          newTask.setPersonId(task.getPersonId());
        }
        if(task.getPersonName()!=null){
            newTask.setPersonName(task.getPersonName());
          }
          if(task.getPersonEmail()!=null){
            newTask.setPersonEmail(task.getPersonEmail());
          }

          if(task.getTaskName()!=null){
            newTask.setTaskName(task.getTaskName());
          }
          if(task.getTaskDetails()!=null){
            newTask.setTaskDetails(task.getTaskDetails());
          }
          if(task.getEffectiveDate()!=null){
            newTask.setEffectiveDate(task.getEffectiveDate());
          }
          if(task.getDueDate()!=null){
            newTask.setDueDate(task.getDueDate());
          }
          
            newTask.setTaskStatus(task.getTaskStatus());
          
        taskRepository.save(newTask);
        return newTask;
      }catch (Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }

    @Override 

    public Task addTask(Task task){
      taskRepository.save(task);

      String text="Dear "+task.getPersonName()+"\n Please open My tasks in employee portal to view task details";

      SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(task.getPersonEmail());
        message.setSubject("New task");
        message.setText(text);
        emailSender.send(message);

      return task;
    }

    

    

    @Override 

    public Task getTaskById(int taskId){
      try{
        return taskRepository.findById(taskId).get();
      }
      catch (Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }

    @Override 
    public ArrayList <Task> getTasks(){
        return (ArrayList<Task>) taskRepository.findAll();
    }

    @Override 
    public List <Task> getTasksAssignedForm(String taskAssignedById){
        return (List<Task>) taskRepository.findAllByTaskAssignedById(taskAssignedById);
    }

    @Override 
    public List <Task> getTasksAssignedTo(String personId){
        return (List<Task>) taskRepository.findAllByPersonId(personId);
    }

    @Override
    public int countOfTasks(String email){
      return taskRepository.countByPersonIdAndTaskStatus(email, false);
    }

    @Override
    public List getOverDueTasksAssignedFrom(String taskAssignedById){
      return taskRepository.allOverDueTasksAssignedFrom(taskAssignedById);
    }

    @Override
    public List getPendingTasksAssignedFrom(String taskAssignedById){
      return taskRepository.allPendingTasksAssignedFrom(taskAssignedById);
    }

    @Override
    public List getCompletedTasksAssignedFrom(String taskAssignedById){
      return taskRepository.allCompletedTasksAssignedFrom(taskAssignedById);
    }

    @Override
    public List getOverDueTasksPersonId(String personId){
      return taskRepository.allOverDueTasksPersonId(personId);
    }

    @Override
    public List getPendingTasksPersonId(String personId){
      return taskRepository.allPendingTasksPersonId(personId);
    }

    @Override
    public List getCompletedTasksPersonId(String personId){
      return taskRepository.allCompletedTasksPersonId(personId);
    }

    @Override 

    public HashMap<String,Integer> getTasksEfficiency(String personId){
      int overDueTasks=taskRepository.allOverDueTasksPersonId(personId).size();
      int pendingTasks=taskRepository.allPendingTasksPersonId(personId).size();
      int completedTasks=taskRepository.allCompletedTasksPersonId(personId).size();
      int totalTasks=overDueTasks+pendingTasks+completedTasks;
      double tasksEfficiency=(double) completedTasks/totalTasks*100;
      HashMap<String, Integer>tasksDetails = new HashMap<>();
      tasksDetails.put("overDueTasks",overDueTasks);
      tasksDetails.put("pendingTasks",pendingTasks);
      tasksDetails.put("completedTasks",completedTasks);
      tasksDetails.put("tasksEfficiency",(int) tasksEfficiency);
      return tasksDetails;

    }

}

