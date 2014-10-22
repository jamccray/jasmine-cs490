-- users table ; userId is like our student Id (but all users arent students)
drop table if exists onlineforms_user;

create table onlineforms_user (
id int not null auto_increment,
userName varchar(15) not null,
userFirstName varchar(30) not null,
userLastName varchar(30) not null,
userEmail varchar(30) not null,
userId varchar(10) not null,
userPassword varchar(20) not null,
inactive boolean,
primary key(id)
);

-- authenticated session; users are added to this table when they succesfully log in
drop table if exists authenticated_Session; 

create table authenticated_Session (
id int auto_increment not null, 
userName varchar(15) not null,
userPassword varchar(20) not null,
userId varchar(10) not null,
TS timestamp,
session_id varchar(60) not null,
primary key(id)
);

select * from authenticated_Session;

-- forms table; all avaliable forms
create table onlineforms_forms (
id int not null auto_increment,
title varchar (60) not null,
courseDept varchar(10) not null,
courseNumber varchar(7) not null,
semester varchar(20) not null,
year int not null,
primary key(id)
);

-- users_forms; the relationship is created when a form is saved for a certain user
drop table if exists users_forms;

CREATE  TABLE users_forms (
  `onlineforms_user_id` INT NOT NULL ,
  `forms_id` INT NOT NULL ,
  PRIMARY KEY (`onlineforms_user_id`, `forms_id`) );
