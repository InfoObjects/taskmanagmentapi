package com.task.info.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.info.Entity.TaskStatus;

public interface TaskStatusDao extends JpaRepository<TaskStatus, Integer> {

}
