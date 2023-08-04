package com.task.info.Controller;

import java.util.List;

import org.aspectj.lang.annotation.AfterReturning;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.info.Entity.Employee;
import com.task.info.Entity.Task;
import com.task.info.Service.EmployeService;
import com.task.info.Service.TaskService;

import jakarta.persistence.EntityNotFoundException;

@Controller // on presentation layer
@RestController
@RequestMapping("/api/auth")
public class AuthoController {
    @Autowired
    private TaskService taskservice;
    @Autowired
    private EmployeService em;

    @GetMapping("/task")
    public List<Task> gettasks() {
        Task t = new Task();
        t.setTitle("backend");
        t.setDes("set the database");
        t.setDeadline(3.5);
        t.setPrioritylevel(2);
        return this.taskservice.getAllTask();
    }

    @PostMapping("/task")
    public Task addtask(@RequestBody Task task) {
        this.taskservice.addtask(task);
        return task;
    }

    @GetMapping("/signin")
    public List<Employee> getEmployees() {
        return this.em.getAllEmployees();
    }

    @PostMapping("/signup")
    public Employee add(@RequestBody Employee emp) {
        this.em.addEmployee(emp);
        return emp;
    }

    @PostMapping("/signin")
    public ResponseEntity<String> addEmployee(@RequestBody Employee emp) {
        try {
            em.addEmployee(emp);
            return new ResponseEntity<String>("Succesfully", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/tasks/{taskid}/assign/{empid}")
    public ResponseEntity<String> assigntask(@PathVariable Integer taskid, @PathVariable Integer empid,
            @RequestParam String title,
            @RequestParam String des,
            @RequestParam Integer priority) {

        taskservice.assigntask(taskid, empid, title, des, priority);
        return new ResponseEntity<String>("Task Assigned Succesfully", HttpStatus.OK);
    }

    @PutMapping("/update/empolyee/{empid}")
    public ResponseEntity<Task> updatestatusbyemployee(
            @PathVariable Integer empid,
            @RequestParam Integer taskid,
            @RequestParam String status) {
        try {
            Task u = taskservice.updatestatusbyemployee(empid, taskid, status);
            return new ResponseEntity<>(u, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/complete/{taskid}")
    public ResponseEntity<String> markTaskComplete(@PathVariable Integer taskid) {
        taskservice.markTaskComplete(taskid);
        return new ResponseEntity<>("Task Marked as Complete", HttpStatus.OK);
    }

    @GetMapping("/task/status")
    public ResponseEntity<List<Map<String, Object>>> getAllTaskStatus() {
        List<Map<String, Object>> tasklist = taskservice.getAllTaskStatus();
        return new ResponseEntity<>(tasklist, HttpStatus.OK);
    }

}
