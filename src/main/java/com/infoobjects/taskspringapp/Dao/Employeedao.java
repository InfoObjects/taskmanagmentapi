package com.infoobjects.taskspringapp.Dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infoobjects.taskspringapp.entities.Employeedetails;

public interface Employeedao extends JpaRepository<Employeedetails,Long>{
   
     Boolean existsByEmail(String email);

     List<Employeedetails> findByName(String name);

    
     Optional<Employeedetails> findById(Long taskId);


     // List<Employeedetails> findByCompletedTasks_Id(Long taskId);


}
