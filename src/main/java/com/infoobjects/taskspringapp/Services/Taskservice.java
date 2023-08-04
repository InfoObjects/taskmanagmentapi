package com.infoobjects.taskspringapp.Services;


// import java.util.ArrayList;
// import java.util.Collections;
import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.infoobjects.taskspringapp.Dao.Employeedao;
import com.infoobjects.taskspringapp.Dao.Taskdao;
import com.infoobjects.taskspringapp.entities.Employeedetails;
import com.infoobjects.taskspringapp.entities.Taskdetails;
import com.infoobjects.taskspringapp.entities.Taskstatus;

import jakarta.persistence.EntityNotFoundException;

@Service
public class Taskservice {

    @Autowired
    private Employeedao employeedao;

    @Autowired
    private Taskdao taskdao;


    
    public Taskservice(Taskdao taskdao, Employeedao employeedao) {
        this.taskdao = taskdao;
        this.employeedao = employeedao;
    }
    
    
    public Taskdetails assignTask(Long taskId, List<Long> employeeIds) {
    Taskdetails task = taskdao.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task with ID " + taskId + " not found."));

    List<Employeedetails> employees = employeedao.findAllById(employeeIds);

    task.setAssignedEmployees(employees);

    return taskdao.save(task);
    }



    public void updateTask(Long taskId, Taskdetails updatedTask) {
        Taskdetails existingTask = taskdao.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task ID"));

        // Update the tasks with the new values
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setDeadline(updatedTask.getDeadline());
        existingTask.setPriority(updatedTask.getPriority());

        taskdao.save(existingTask);
    }


    public void deleteTask(Long taskId) {
        Taskdetails task = taskdao.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with ID: " + taskId));

        taskdao.delete(task);
    }


    public Taskdetails getTaskbyId(Long id)
    {
        Taskdetails task= taskdao.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid TaskId"));
        return task;
    }


   public List<Taskdetails> getTaskByStatus(Taskstatus status)
   {
         return taskdao.findByStatus(status);
    }


   public List<Taskdetails> getTasksByEmployeeId(Long employeeId) {
   
    return taskdao.findByAssignedEmployees_Id(employeeId);
     }


  


    public void markTaskAsComplete(Long taskId ,List<Long> employeeIds) {
        Taskdetails taskdetails = taskdao.findById(taskId)
                        .orElseThrow(() -> new IllegalArgumentException("Task not found with ID: " + taskId));
        
             if (taskdetails.getStatus() == Taskstatus.COMPLETED) {
                throw new IllegalArgumentException("Task with ID " + taskId + " is already completed.");
                        }
                taskdetails.setStatus(Taskstatus.COMPLETED);
                taskdao.save(taskdetails);
                
                List<Employeedetails> employee = employeedao.findAllById(employeeIds)
                ;                
                taskdetails.getCompletedbyEmployees().addAll(employee);
                taskdao.save(taskdetails);
    }

    public void markTaskAsInprogress(Long taskId,Long employeeId)
    {
        Taskdetails taskdetails = taskdao.findById(taskId)
              .orElseThrow(() -> new IllegalArgumentException("Task not found with Id : "+ taskId));
              
              if(taskdetails!=null && taskdetails.getStatus() != Taskstatus.IN_PROGRESS){

                taskdetails.setStatus(Taskstatus.IN_PROGRESS);
                taskdao.save(taskdetails);
             }
             else if (taskdetails.getStatus() == Taskstatus.IN_PROGRESS)
             {
                throw new IllegalArgumentException("Task with id "+ taskId + "already in progress");
             }
             else
             {
                throw new IllegalArgumentException("Task with id "+ taskId + "is not  in progress");
            }

            Employeedetails employee = employeedao.findById(employeeId)
                    .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + employeeId));
                
                taskdetails.getInprogressbyEmployees().add(employee);
                taskdao.save(taskdetails);

    }

}