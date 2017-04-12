INSERT INTO users (user_name,
                   user_surname,
                   user_email)
VALUES (
  'USER NAME 1',
  'USER SURNAME 1',
  'test1@mail.com'
);

INSERT INTO tasks (user_id,
                   task_name,
                   task_desc,
                   task_creation_date,
                   task_deadline_date)
VALUES (
  1,
  'TEST_TASK1',
  'TEST TASK DESC1',
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP + 1
);

INSERT INTO tasks (user_id,
                   task_name,
                   task_desc,
                   task_creation_date,
                   task_deadline_date)
VALUES (
  1,
  'TEST_TASK2',
  'TEST TASK DESC2',
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP + 1
);

INSERT INTO tasks (user_id,
                   task_name,
                   task_desc,
                   task_creation_date,
                   task_deadline_date)
VALUES (
  1,
  'TEST_TASK3',
  'TEST TASK DESC3',
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP + 1
);