package com.infoobjects.taskspringapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  (scanBasePackages = {"com.infoobjects.taskspringapp.Controller","com.infoobjects.taskspringapp.Dao","com.infoobjects.taskspringapp.entities","com.infoobjects.taskspringapp.Services","com.infoobjects.taskspringapp.Config"})

public class TaskspringappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskspringappApplication.class, args);
	}

}

// {
//     "id":3,
// "title": "Task3",
// "description": "this is task3",
// "deadline": "2024-07-13T08:31:30.303Z",
// "priority": 7
// }

//  {
//         "id": 12,
//         "name": "name 1",
//         "email": "name1@gmail.com",
//         "password": "123"
//     },

