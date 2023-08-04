package com.task.info.EventListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.task.info.DAO.TaskStatusDao;
import com.task.info.Entity.Task;
import com.task.info.Entity.TaskStatus;

import jakarta.persistence.PostPersist;

@Component
public class TaskEntityListener {
    @Autowired
    private TaskStatusDao t;

    @PostPersist
    public void onTaskPersist(Task task) {
        TaskStatus taskstatus = new TaskStatus();
        taskstatus.setId(task.getTaskid());
        taskstatus.setStatus(task.getCom());
        t.save(taskstatus);
    }
}
