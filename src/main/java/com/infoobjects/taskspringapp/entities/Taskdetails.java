package com.infoobjects.taskspringapp.entities;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="tasks")
public class Taskdetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String Title;
    private String Description;
    private LocalDateTime Deadline ;//LocalDateTime.of(2023, 7, 31, 17, 0); // July 31, 2023, 5:00 PM
    private int Priority;


    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employeedetails employee;

    @ManyToMany
    @JoinTable(name = "task_employee",
               joinColumns = @JoinColumn(name = "task_id"),
               inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employeedetails> assignedEmployees;

   

    // public void setAssignedTo(Employeedetails assignto) {
    //     this.employee = assignto;
    // }

    public void setAssignedEmployees(List<Employeedetails> assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
    }

      @Override
    public String toString() {
        return "Taskdetails [id=" + id + ", Title=" + Title + ", Description=" + Description + ", Deadline=" + Deadline
                + ", Priority=" + Priority + "]";
    }

    public Taskdetails() {
        super();
    }

    public Taskdetails(long id, String title, String description, LocalDateTime deadline, int priority) {
        this.id = id;
        this.Title = title;
        this.Description = description;
        this.Deadline = deadline;
        this.Priority = priority;
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return Title;
    }


    public void setTitle(String title) {
        Title = title;
    }


    public String getDescription() {
        return Description;
    }


    public void setDescription(String description) {
        Description = description;
    }


    public LocalDateTime getDeadline() {
        return Deadline;
    }


    public void setDeadline(LocalDateTime deadline) {
        Deadline = deadline;
    }


    public int getPriority() {
        return Priority;
    }


    public void setPriority(int priority) {
        Priority = priority;
    }


   


    









    
}
