package com.infoobjects.taskspringapp.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.infoobjects.taskspringapp.entities.Taskdetails;
@Component

public interface  Taskdao extends JpaRepository<Taskdetails,String>{

        Optional<Taskdetails> findById(Long taskId);

}
