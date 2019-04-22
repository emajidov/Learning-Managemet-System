insert into user (name, surname, username, password,role) values ('Admin','Admin','user1', 'password', 'ROLE_ADMIN');
insert into user (name, surname, username, password,role) values ('Admin','Admin','user3', 'password', 'ROLE_ADMIN');
insert into user (name, surname, username, password,role) values ('Admin','Admin','user2', 'password','ROLE_USER');

insert into task (name, description,user_id, deadline) values ('Homework 1', 'you should submit a full web application as developed in the class',1,CURRENT_TIMESTAMP());
insert into task (name, description,user_id, deadline) values ('Homework 2', 'you should submit a full web application as developed in the class',1,CURRENT_TIMESTAMP());
insert into task (name, description,user_id,deadline) values ('Homework 3', 'you should submit a full web application as developed in the class',2,CURRENT_TIMESTAMP());
insert into task (name, description,user_id,deadline) values ('Homework 4', 'you should submit a full web application as developed in the class',2,CURRENT_TIMESTAMP());

insert into submission(name,submission_date,solution,task_id,is_graded) values ('Elgun Majidov',current_timestamp(),'This is the solution for the task',1,false)
insert into submission(name,submission_date,solution,task_id,is_graded) values ('Elgun Majidov',current_timestamp(),'This is the solution for the task',1,false)
insert into submission(name,submission_date,solution,task_id,is_graded) values ('Elgun Majidov',current_timestamp(),'This is the solution for the task',1,false)
insert into submission(name,submission_date,solution,task_id,is_graded) values ('Elgun Majidov',current_timestamp(),'This is the solution for the task',1,false)
insert into submission(name,submission_date,solution, task_id,is_graded) values ('Elgun Majidov',current_timestamp(),'This is the solution for the task',2,false)
insert into submission(name,submission_date,solution, task_id,is_graded) values ('Elgun Majidov',current_timestamp(),'This is the solution for the task',2,false)
insert into submission(name,submission_date,solution, task_id,is_graded) values ('Elgun Majidov',current_timestamp(),'This is the solution for the task',2,false)
insert into submission(name,submission_date,solution, task_id,is_graded) values ('Elgun Majidov',current_timestamp(),'This is the solution for the task',3,false)
insert into submission(name,submission_date,solution, task_id,is_graded) values ('Elgun Majidov',current_timestamp(),'This is the solution for the task',3,false)
insert into submission(name,submission_date,solution, task_id,is_graded) values ('Elgun Majidov',current_timestamp(),'This is the solution for the task',3,false)
insert into submission(name,submission_date,solution, task_id,is_graded) values ('Elgun Majidov',current_timestamp(),'This is the solution for the task',3,false)
insert into submission(name,submission_date,solution, task_id,is_graded) values ('Elgun Majidov',current_timestamp(),'This is the solution for the task',4,false)
insert into submission(name,submission_date,solution, task_id,is_graded) values ('Elgun Majidov',current_timestamp(),'This is the solution for the task',4,false)