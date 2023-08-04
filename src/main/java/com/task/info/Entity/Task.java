package com.task.info.Entity;

import org.springframework.data.repository.query.Param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskid;

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    private String title;
    private String des;
    private Double deadline;
    private boolean com;
    private String comp;

    public String getCom() {
        return comp;
    }

    public void setCom(String com) {
        this.comp = com;
    }

    private int prioritylevel;

    public boolean isCom() {
        return com;
    }

    public void setCom(boolean com) {
        this.com = com;
    }

    @OneToOne(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employee assignedTo;

    public Employee getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Employee assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getStatus() {
        if (com && "In Progress".equals(comp)) {
            return "In Progress";
        } else if (com && "Completed".equals(comp)) {
            return "Completed";
        } else if (com && "Postponed".equals(comp)) {
            return "Postponed";
        } else if (com && "Not Started".equals(comp)) {
            return "Not Started";
        } else {
            return "Unknown Status";
        }
    }

    public void setStatus(String status) {
        switch (status) {
            case "Canceled":
                comp = "Canceled";
                break;
            case "postponed":
                comp = "Postponed";
                break;
            case "In Progress":
                comp = "In progress";
                break;
            case "completed":
                comp = "completed";
                break;
        }

    }

    @Override
    public String toString() {
        return "task [title=" + title + ", des=" + des + ", deadline=" + deadline + ", prioritylevel=" + prioritylevel
                + "]";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setDeadline(Double deadline) {
        this.deadline = deadline;
    }

    public void setPrioritylevel(int prioritylevel) {
        this.prioritylevel = prioritylevel;
    }

    public String getTitle() {
        return title;
    }

    public String getDes() {
        return des;
    }

    public Double getDeadline() {
        return deadline;
    }

    public int getPrioritylevel() {
        return prioritylevel;
    }

    public Task() {
    }

    public Task(String title, String des, Double deadline, int prioritylevel) {
        this.title = title;
        this.des = des;
        this.deadline = deadline;
        this.prioritylevel = prioritylevel;
    }

}
