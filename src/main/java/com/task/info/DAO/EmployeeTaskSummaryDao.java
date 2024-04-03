package com.task.info.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.info.Entity.Employeetasksummary;

public interface EmployeeTaskSummaryDao extends JpaRepository<Employeetasksummary, Integer> {

}
