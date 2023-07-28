package com.infoobjects.taskspringapp.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.infoobjects.taskspringapp.entities.Taskdetails;
import com.infoobjects.taskspringapp.entities.Taskstatus;
@Component

public interface  Taskdao extends JpaRepository<Taskdetails,String>{

        Optional<Taskdetails> findById(Long taskId);

        List<Taskdetails> findByStatus(Taskstatus status);

}
