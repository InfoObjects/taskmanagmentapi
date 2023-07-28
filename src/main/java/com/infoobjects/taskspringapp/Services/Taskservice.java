package com.infoobjects.taskspringapp.Services;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

//     public void assignTask(Long taskId, Long employeeId) {
//         Taskdetails task = taskdao.findById(taskId)
//                 .orElseThrow(() -> new IllegalArgumentException("Invalid task ID"));

//         Employeedetails employee = employeedao.findById(employeeId)
//                 .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID"));

//         task.setAssignedTo(employee);
//         taskdao.save(task);
//     }


public Taskdetails assignTask(Long taskId, List<Long> employeeIds) {
    Taskdetails task = taskdao.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Task with ID " + taskId + " not found."));

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

    public void markTaskAsComplete(Long taskId) {
        Taskdetails task = taskdao.findById(taskId)
                        .orElseThrow(() -> new IllegalArgumentException("Task not found with ID: " + taskId));
        if (task != null && task.getStatus() != Taskstatus.COMPLETED) {
            task.setStatus(Taskstatus.COMPLETED);
            taskdao.save(task);
         } 
        else if (task.getStatus() == Taskstatus.COMPLETED) {
            throw new IllegalArgumentException("Task with ID " + taskId + " is already completed.");
        } else {
            throw new IllegalArgumentException("Task with ID " + taskId + " is not in progress.");
        } 
    }


    public void markTaskAsInprogress(Long taskId)
    {
        Taskdetails task = taskdao.findById(taskId)
              .orElseThrow(() -> new IllegalArgumentException("Task not found with Id : "+ taskId));
              if(task!=null && task.getStatus() != Taskstatus.IN_PROGRESS){
                task.setStatus(Taskstatus.IN_PROGRESS);
                taskdao.save(task);
             }
             else if (task.getStatus() == Taskstatus.IN_PROGRESS)
             {
                throw new IllegalArgumentException("Task with id "+ taskId + "already in progress");
             }
             else
             {
                throw new IllegalArgumentException("Task with id "+ taskId + "is not  in progress");
            }

    }

}