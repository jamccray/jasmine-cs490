SET FOREIGN_KEY_CHECKS=0;
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

use fooddb;

-- users table ; userId is like our student Id (but all users arent students)
drop table if exists onlineforms_user;

create table onlineforms_user (
id int not null auto_increment,
userName varchar(15) not null,
userFirstName varchar(30) not null,
userLastName varchar(30) not null,
userEmail varchar(30) not null,
userId varchar(10) unique not null,
userPassword varchar(20) not null,
inactive boolean,
primary key(id)
);


insert into onlineforms_user (userName, userFirstName, userLastName, userEmail, userId, userPassword, inactive) values ('cjmack', 'Charles', 'Mack', 'cjmack@g.coastal.edu', '0123456', '13243254',FALSE);

insert into onlineforms_user (userName, userFirstName, userLastName, userEmail, userId, userPassword, inactive) values ('jmccray', 'Jasmine', 'McCray', 'jmccray@g.coastal.edu', '0987654','ads8er94',FALSE);

insert into onlineforms_user (userName, userFirstName, userLastName, userEmail, userId, userPassword, inactive) values ('aatay', 'Aydin', 'Atay', 'aatay@g.coastal.edu', '0993723', '0aojdfo4',FALSE);

insert into onlineforms_user (userName, userFirstName, userLastName, userEmail, userId, userPassword, inactive) values ('badams', 'Bobby', 'Adams', 'badams@g.coastal.edu','9998765','ha598449',FALSE);

SELECT * from onlineforms_user;

-- authenticated session; users are added to this table when they succesfully log in
drop table if exists authenticated_Session; 

create table authenticated_Session(
id int auto_increment not null, 
userName varchar(15) not null,
userPassword varchar(20) not null,
userId varchar(10) not null,
TS timestamp,
session_id varchar(60) not null,
onlineforms_user_id int not null,
primary key(id),
INDEX `fk_authSession_users1_idx` (`onlineforms_user_id` ASC) ,
CONSTRAINT `fk_authSession_users1`
FOREIGN KEY (`onlineforms_user_id`)
    REFERENCES `onlineforms_user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

select * from authenticated_Session;

-- forms table; all avaliable forms
drop table if exists onlineforms_forms;

create table onlineforms_forms (
id int not null auto_increment,
courseDept varchar(10) not null,
courseNumber varchar(7) not null,
semester varchar(20) not null,
year int not null,
title varchar (60) not null,
primary key(id)
);
select * from onlineforms_forms;

-- users_forms; the relationship is created when a form is saved for a certain user
drop table if exists users_forms;

CREATE  TABLE users_forms (
  user_id INT NOT NULL ,
  forms_id INT NOT NULL ,
  PRIMARY KEY (`user_id`, `forms_id`),
INDEX `fk_users_forms_users1_idx` (`user_id` ASC) ,
CONSTRAINT `fk_users_forms_users1`
  FOREIGN KEY (`user_id`)
    REFERENCES `onlineforms_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
INDEX `fk_users_forms_forms1_idx` (`forms_id` ASC) ,
CONSTRAINT `fk_users_forms_forms1`
  FOREIGN KEY (`forms_id`)
    REFERENCES `onlineforms_forms` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

select * from users_forms;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
SET FOREIGN_KEY_CHECKS=1;