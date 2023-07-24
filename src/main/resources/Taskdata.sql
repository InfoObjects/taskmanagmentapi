create table PUBLIC.taskmanager (
  title varchar(50) NOT NULL,
  description varchar(50) NOT NULL,
  deadline datetime NOT NULL,
  priority int NOT NULL
);

insert into taskmanager(title, description, deadline, priority)values('Software Engineer', 'Develop new features for the company's software', '2023-07-31', 'High');
