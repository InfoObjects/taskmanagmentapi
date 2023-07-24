package com.infoobjects.taskspringapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infoobjects.taskspringapp.Dao.Employeedao;
import com.infoobjects.taskspringapp.Services.Employeedetailservice;
import com.infoobjects.taskspringapp.Services.Employeeservice;
import com.infoobjects.taskspringapp.Services.Taskdetailservice;
import com.infoobjects.taskspringapp.Services.Taskservice;
import com.infoobjects.taskspringapp.entities.Employeedetails;
import com.infoobjects.taskspringapp.entities.Taskdetails;

@Controller
@RestController
@RequestMapping("/api/auth")
public class MyController {


    @Autowired
    private Employeedao employeedao;

    @Autowired
    private Taskdetailservice taskdetailservice;

    @Autowired
    private Employeedetailservice employeedetailservice;

    @Autowired
    private Taskservice taskservice;

    @Autowired
    private Employeeservice employeeservice;
   
    //get task
    @GetMapping("/tasks")
    public List<Taskdetails> gettasks()
    {
        return this.taskdetailservice.gettasks();
        
    }


    // add Task
    @PostMapping("/tasks")
    public Taskdetails addtask(@RequestBody Taskdetails newtask)
    {     
        return  this.taskdetailservice.addtask(newtask);
    }

    // get employee
    @GetMapping("/employees")
    public List<Employeedetails> getemployee()
    {
        return this.employeedetailservice.getemployee();
    }

    // add employee
    //1
    // @PostMapping("/employees")
    // public Employeedetails addemployee(@RequestBody Employeedetails newemployee)
    // {
    //     return this.employeedetailservice.addemployee(newemployee);
    // }

    //2
//     @PostMapping("/employees")
// public ResponseEntity<String> addEmployee(@RequestBody Employeedetails newEmployee) {
//     Employeedetails addedEmployee = employeedetailservice.addemployee(newEmployee);
//     if (addedEmployee != null) {
//         return ResponseEntity.ok("Employee successfully added");
//     } else {
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add employee");
//     }
// }

//3
@PostMapping("/employees")

public ResponseEntity<String> addEmployee(@RequestBody Employeedetails newEmployee) {
    // Check if an employee with the same email already exists
    if (employeedao.existsByEmail(newEmployee.getEmail())) {
        return ResponseEntity.badRequest().body("Employee with the same email already exists");
    }

    // Save the new employee
    employeedao.save(newEmployee);
    return ResponseEntity.ok("Employee added successfully");
}


//assign task to employee 
//  @PostMapping("/{taskId}/assign/{employeeId}")
//     public ResponseEntity<String> assignTask(@PathVariable Long taskId, @PathVariable Long employeeId) {
//         taskservice.assignTask(taskId,employeeId);
//         return ResponseEntity.ok("Task assigned successfully");
//     }


 @PostMapping("/{taskId}/assign")
    public Taskdetails assignEmployeesToTask(@PathVariable Long taskId, @RequestBody List<Long> employeeIds) {
        return taskservice.assignTask(taskId, employeeIds);
    }

// update task
    @PutMapping("/updatetasks/{taskId}")
    public ResponseEntity<String> updateTask(@PathVariable Long taskId, @RequestBody Taskdetails updatedTask) {
        try {
            taskservice.updateTask(taskId, updatedTask);
            return ResponseEntity.ok("Task updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//delete task

 @DeleteMapping("/deletetasks/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
        taskservice.deleteTask(taskId);
        return ResponseEntity.ok("Task deleted successfully");
    }

// find employee by name
     @GetMapping("/employee-by-name")
    public List<Employeedetails> getEmployeesByName(@RequestParam String name) {
        return employeeservice.getEmployeesByName(name);
    }

}
