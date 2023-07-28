package com.infoobjects.taskspringapp.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infoobjects.taskspringapp.Dao.Employeedao;
import com.infoobjects.taskspringapp.entities.Employeedetails;

@Service
public class Employeedetailserviceimpl implements Employeedetailservice{

    @Autowired
    private Employeedao employeedao;

    List<Employeedetails> list1;

    public Employeedetailserviceimpl(){
       list1= new ArrayList<>();
        list1.add(new Employeedetails(12,"name 1", "name1@gmail.com","123"));
        list1.add(new Employeedetails(13,"name 2", "name2@gmail.com","321"));    
    }

    @Override
    public List<Employeedetails> getemployee()
    {
        return employeedao.findAll();
        //return list1;
       
    }

    @Override
    public Employeedetails addemployee(Employeedetails newemployee) {

        // list1.add(newemployee);
        // return newemployee;
        employeedao.save(newemployee);
        return newemployee;
    }
   
  
}
