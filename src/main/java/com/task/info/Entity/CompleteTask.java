package com.task.info.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

//this table for a user can se all the complete task
@Entity
public class CompleteTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String des;
    private boolean comp;
    @Temporal(TemporalType.TIMESTAMP)
    private Date completionDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public boolean isComp() {
        return comp;
    }

    public void setComp(boolean comp) {
        this.comp = comp;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }
}
