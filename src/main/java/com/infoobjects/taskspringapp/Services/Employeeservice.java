package com.infoobjects.taskspringapp.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infoobjects.taskspringapp.Dao.Employeedao;
import com.infoobjects.taskspringapp.entities.Employeedetails;

@Service

public class Employeeservice {
     @Autowired
    private Employeedao employeedao;

    public List<Employeedetails> getEmployeesByName(String name) {
        return employeedao.findByName(name);
    }
}
