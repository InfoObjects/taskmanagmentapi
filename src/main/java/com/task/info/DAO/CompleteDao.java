package com.task.info.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.info.Entity.CompleteTask;

@Repository
public interface CompleteDao extends JpaRepository<CompleteTask, Integer> {

}
