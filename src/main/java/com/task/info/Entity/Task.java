package com.task.info.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
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

    private int prioritylevel;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "emp_id")
    // private Employee assignedTo;

    public void setAssignedTo(Employee emp) {
        this.assignedTo = emp;
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
