package com.task.info.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.task.info.Entity.Employee;
import com.task.info.Entity.Task;

@Repository
public interface TaskDao extends JpaRepository<Task, Integer> {
    // Employee findByUserID(Integer userId);
    @Query("SELECT t FROM Task t WHERE t.assignedTo.id = :emp_id AND t.id = :taskId")
    Task findByAssignedToIdAndId(Integer emp_id, Integer taskId);

    @Query("SELECT t FROM Task t WHERE t.assignedTo.id = :employeeId")
    List<Task> findByAssignedToId(Integer employeeId);
}
