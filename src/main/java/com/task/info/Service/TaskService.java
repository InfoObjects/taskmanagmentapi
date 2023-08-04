package com.task.info.Service;

import java.sql.Date;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.task.info.DAO.CompleteDao;
import com.task.info.DAO.EmployeeDao;
import com.task.info.DAO.TaskDao;
import com.task.info.DAO.TaskStatusDao;
import com.task.info.Entity.CompleteTask;
import com.task.info.Entity.Employee;
import com.task.info.Entity.Task;
import com.task.info.Entity.TaskStatus;

import jakarta.transaction.Transactional;

@Component
@Service
@Transactional
public class TaskService {
    @Autowired
    private EmployeeDao ee;
    @Autowired
    private TaskStatusDao f;
    @Autowired
    private TaskDao tt;
    @Autowired
    private CompleteDao com;

    private static List<Task> list = new ArrayList<>();

    static {

        list.add(new Task("Backend", "set database", 2.3, 3));
    }

    public List<Task> getAllTask() {
        return list;
    }

    // public void addtask(Task task) {
    // list.add(task);
    // }
    public void addtask(Task task) {
        // list.add(e);
        Task savedTask = tt.save(task);
        TaskStatus taskstatus = new TaskStatus();
        taskstatus.setId(savedTask.getTaskid());
        taskstatus.setStatus(savedTask.getStatus());
        f.save(taskstatus);

    }

    public void assigntask(Integer taskid, Integer empid, String title, String des, Integer prioritylevel) {
        Task t = tt.findById(taskid).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        Employee e = ee.findById(empid).orElseThrow(() -> new IllegalArgumentException("Employe not found"));
        t.setAssignedTo(e);
        t.setTitle(title);
        t.setDes(des);
        t.setPrioritylevel(prioritylevel);
        tt.save(t);
    }

    public Task updatestatusbyemployee(Integer empid, Integer taskid, String completed) {
        Task t = tt.findByAssignedToIdAndId(empid, taskid);

        if (t != null) {
            t.setStatus(completed);
            return tt.save(t);
        } else {

            throw new IllegalArgumentException("Task not found");
        }

    }

    public void markTaskComplete(Integer taskid) {
        Task t = tt.findById(taskid).orElse(null);
        if (t != null && !t.isCom()) {
            t.setCom(true);
            tt.save(t);
        }

        CompleteTask c = new CompleteTask();
        c.setDes(t.getDes());
        c.setComp(true);
        c.setCompletionDate(new java.util.Date());
        com.save(c);
    }

    public List<Map<String, Object>> getAllTaskStatus() {
        List<Task> tasks = tt.findAll();
        List<Map<String, Object>> taskStatusList = new ArrayList<>();

        for (Task task : tasks) {
            Map<String, Object> taskStatus = new HashMap<>();
            taskStatus.put("taskId", task.getTaskid());
            taskStatus.put("status", task.getStatus());
            taskStatusList.add(taskStatus);
        }

        return taskStatusList;
    }

}
