package com.infoobjects.taskspringapp.Services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infoobjects.taskspringapp.Dao.Employeedao;
import com.infoobjects.taskspringapp.Dao.Taskdao;
import com.infoobjects.taskspringapp.entities.Employeedetails;
import com.infoobjects.taskspringapp.entities.Taskdetails;

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
        // Retrieve the task by taskId from the database
        Taskdetails task = taskdao.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Invalid task ID"));

        if (task == null) {
            
            throw new EntityNotFoundException("Task with ID " + taskId + " not found.");
        }

        // Retrieve the employees by employeeIds from the database
        List<Employeedetails> employees = employeedao.findAllById(employeeIds);

        // Update the task's assignedEmployees list
        task.setAssignedEmployees(employees);

        // Save the updated task to the database
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

        // Save the updated task
        taskdao.save(existingTask);
    }


     public void deleteTask(Long taskId) {
        Taskdetails task = taskdao.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with ID: " + taskId));

        taskdao.delete(task);
    }
}


