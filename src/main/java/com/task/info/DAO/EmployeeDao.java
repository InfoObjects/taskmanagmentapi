package com.task.info.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.info.Entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {

    Boolean existsByEmail(String email);
}
