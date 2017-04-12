SET DATABASE SQL SYNTAX ORA TRUE;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
  user_id INTEGER IDENTITY,
  user_name varchar(45) NOT NULL,
  user_surname varchar(45) NOT NULL,
  user_email varchar(45) NOT NULL,
  PRIMARY KEY (user_id)
);

DROP TABLE IF EXISTS tasks;
CREATE TABLE tasks (
task_id INTEGER IDENTITY,
user_id INTEGER NOT NULL,
task_name varchar(45) NOT NULL,
task_desc varchar(45) NOT NULL,
task_creation_date date NOT NULL,
task_deadline_date date NOT NULL,
PRIMARY KEY (task_id),
CONSTRAINT fk_tasks_1 FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);
