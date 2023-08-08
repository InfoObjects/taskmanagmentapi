package com.task.info.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.task.info.DAO.EmployeeDao;
import com.task.info.Entity.Employee;

import jakarta.transaction.Transactional;

@Component
@Service
@Transactional
public class EmployeService {
    // @Autowired
    // private Employee emp;
    @Autowired
    private EmployeeDao dao;
    private static List<Employee> list = new ArrayList<>();

    static {

        list.add(new Employee(332, "xyz.c@infoobjects.com", "Abhishek", "@nis33"));
    }

    public List<Employee> getAllEmployees() {
        return list;
    }

    public void addEmployee(Employee task) {
        // list.add(task);
        String email = task.getEmail();
        if (dao.existsByEmail(email)) {
            throw new IllegalArgumentException("Email is already taken");
        } else {

            task.setId(task.getId());

            dao.save(task);
            System.out.println(task);
        }
    }

    public void add(Employee e) {
        list.add(e);
        dao.save(e);
    }

}
