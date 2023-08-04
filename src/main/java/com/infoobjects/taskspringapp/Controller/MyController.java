package com.infoobjects.taskspringapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.infoobjects.taskspringapp.entities.Taskstatus;

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

    // add employeee
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


   //get employee with id 
    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Employeedetails> getEmployeeById(@PathVariable Long employeeId) {
        Employeedetails employee = employeeservice.getEmployeeById(employeeId);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //get tasks with id
    @GetMapping("/tasks/{TaskId}")
    public ResponseEntity<Taskdetails> getTasksById(@PathVariable Long TaskId)
    {
        Taskdetails task = taskservice.getTaskbyId(TaskId);
        if(task!=null)
        {
            return ResponseEntity.ok(task);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    //assign tasks
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

    // get employees detail assigned to tasks by task id
    @GetMapping("/employees/assign-to-task/{taskId}")
    public ResponseEntity<List<Employeedetails>> getEmployeeByTaskId(@PathVariable Long taskId)
    {
         List<Employeedetails> employees = employeeservice.getEmployeeByTaskId(taskId);

         if(!employees.isEmpty())
         {
            return ResponseEntity.ok(employees);
         }
         else{
            return ResponseEntity.notFound().build();
         }

    }

    //get task details assigned to employees by employee id
     @GetMapping("/tasks/assign-to-employee/{employeeId}")
    public ResponseEntity<List<Taskdetails>> getTasksByEmployeeId(@PathVariable Long employeeId) {
        try {
            List<Taskdetails> task = taskservice.getTasksByEmployeeId(employeeId);

            if (!task.isEmpty()) {

                return ResponseEntity.ok(task);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    

    //mark task as complete
    @PostMapping("/{taskId}/complete")
    public ResponseEntity<String> markTaskAsComplete(@PathVariable Long taskId , @RequestBody List<Long> employeeIds) {
    
            taskservice.markTaskAsComplete(taskId,employeeIds);

            return ResponseEntity.ok("Task marked as complete.");
    }

    //mark task in progress
    @PostMapping("/{taskId}/inprogress")
    public ResponseEntity<String> markTaskAsInprogress(@PathVariable  long taskId ,@RequestParam Long employeeId)
    {
        taskservice.markTaskAsInprogress(taskId,employeeId);
        return ResponseEntity.ok("Task marked as in progress");
    }

    //get task by their status
    @GetMapping("/taskstatus/{status}")
    public ResponseEntity<List<Taskdetails>> getTaskByStatus(@PathVariable Taskstatus status)
    {
        List<Taskdetails> tasks = taskservice.getTaskByStatus(status);
        return ResponseEntity.ok(tasks);
    }

}