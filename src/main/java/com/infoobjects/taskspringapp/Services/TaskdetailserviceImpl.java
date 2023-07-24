package com.infoobjects.taskspringapp.Services;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infoobjects.taskspringapp.Dao.Taskdao;
import com.infoobjects.taskspringapp.entities.Taskdetails;

@Service

public class TaskdetailserviceImpl implements Taskdetailservice{


   @Autowired
   private Taskdao taskdao;

    List<Taskdetails> list;
    public TaskdetailserviceImpl(){
       list= new ArrayList<>();
        LocalDateTime dateTime1 = LocalDateTime.now();
        LocalDateTime dateTime2 = LocalDateTime.now();
        list.add(new Taskdetails(1,"task 1", " this is task 1", dateTime1, 2));
        list.add(new Taskdetails(3,"task 2", " this is task 2", dateTime2, 4));    
    }

// these imp and interface class for loose coupling .

    @Override
    public List<Taskdetails> gettasks()
    {
        return taskdao.findAll();

        //  List<Taskdetails> tasks = new ArrayList<>();
        //  taskdao.findAll().forEach(tasks::add);
        //  return tasks;
        //return list;
    }

    @Override
    public Taskdetails addtask(Taskdetails newtask) {
        
        //  list.add(newtask);
        //  return newtask;

        taskdao.save(newtask);
        return newtask;

    }
    
}
