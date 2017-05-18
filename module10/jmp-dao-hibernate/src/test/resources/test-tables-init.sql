DROP TABLE USERS
IF EXISTS;
CREATE TABLE USERS (
  user_id      INTEGER IDENTITY,
  user_name    VARCHAR(45) NOT NULL,
  user_surname VARCHAR(45) NOT NULL,
  user_email   VARCHAR(45) NOT NULL
);

/*DROP TABLE USERS_TASKS
IF EXISTS;
CREATE TABLE USERS_TASKS
(
  user_id INT,
  task_id INT
);*/

DROP TABLE TASKS
IF EXISTS;
CREATE TABLE TASKS (
  task_id            INTEGER IDENTITY,
  user_id            INTEGER,
  task_name          VARCHAR(45) NOT NULL,
  task_desc          VARCHAR(45) NOT NULL,
  task_creation_date DATE        NOT NULL,
  task_deadline_date DATE        NOT NULL,
  CONSTRAINT fk_tasks_1 FOREIGN KEY (user_id) REFERENCES USERS (user_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
