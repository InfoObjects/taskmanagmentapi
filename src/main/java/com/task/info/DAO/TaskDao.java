package com.task.info.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.info.Entity.Employee;
import com.task.info.Entity.Task;

@Repository
public interface TaskDao extends JpaRepository<Task, Integer> {
    Employee findByUserID(Integer userId);
}
