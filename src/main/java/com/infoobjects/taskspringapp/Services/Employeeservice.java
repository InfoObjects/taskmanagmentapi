package com.infoobjects.taskspringapp.Services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.infoobjects.taskspringapp.Dao.Employeedao;
import com.infoobjects.taskspringapp.Dao.Taskdao;
import com.infoobjects.taskspringapp.entities.Employeedetails;
import com.infoobjects.taskspringapp.entities.Taskdetails;

@Service

public class Employeeservice {

    @Autowired 
    private Taskdao taskdao;

    @Autowired
    private Employeedao employeedao;

    public List<Employeedetails> getEmployeesByName(String name) {
        return employeedao.findByName(name);
    }

    
    public Employeedetails getEmployeeById(Long id) {
        return employeedao.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID"));
    }

    public List<Employeedetails> getEmployeeByTaskId(Long taskId){
        Taskdetails task  = taskdao.findById(taskId).orElse(null);
        if(task!=null){
            return task.getAssignedemployee();
        }
        else{
            return null;
        }
    }

   

    
}
