package com.accesshr.emsbackend.Repo.TaskRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.accesshr.emsbackend.Entity.Task;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;
import java.util.List;

@Repository

public interface TaskJpaRepository extends JpaRepository<Task,Integer>{
  @Query("SELECT COUNT(t) FROM Task t WHERE t.personId = :personId AND t.taskStatus = :taskStatus")
  int countByPersonIdAndTaskStatus(@Param("personId") String personId, @Param("taskStatus") Boolean taskStatus);
  List<Task> findAllByTaskAssignedById(String taskAssignedById);
  List<Task> findAllByPersonId(String personId);


  @Query("SELECT t FROM Task t WHERE t.taskAssignedById = :taskAssignedById AND t.taskStatus=false AND t.dueDate < CURDATE()")
  List<Task> allOverDueTasksAssignedFrom(@Param ("taskAssignedById") String taskAssignedById);

  @Query("SELECT t FROM Task t WHERE t.taskAssignedById = :taskAssignedById AND t.taskStatus=false AND (t.dueDate >= CURDATE() OR t.dueDate IS NULL)")
  List<Task> allPendingTasksAssignedFrom(@Param ("taskAssignedById") String taskAssignedById);

  @Query("SELECT t FROM Task t WHERE t.taskAssignedById = :taskAssignedById AND t.taskStatus=true")
  List<Task> allCompletedTasksAssignedFrom(@Param ("taskAssignedById") String taskAssignedById);

  @Query("SELECT t FROM Task t WHERE t.personId = :personId AND t.taskStatus=false AND t.dueDate < CURDATE()")
  List<Task> allOverDueTasksPersonId(@Param ("personId") String personId);

  @Query("SELECT t FROM Task t WHERE t.personId = :personId AND t.taskStatus=false AND (t.dueDate >= CURDATE() OR t.dueDate IS NULL)")
  List<Task> allPendingTasksPersonId(@Param ("personId") String personId);

  @Query("SELECT t FROM Task t WHERE t.personId = :personId AND t.taskStatus=true")
  List<Task> allCompletedTasksPersonId(@Param ("personId") String personId);
}

