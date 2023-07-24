package com.infoobjects.taskspringapp.Services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.infoobjects.taskspringapp.entities.Taskdetails;

@Component
public interface Taskdetailservice {


    public List<Taskdetails> gettasks();
    
    public Taskdetails addtask(Taskdetails newtask);
    
}
