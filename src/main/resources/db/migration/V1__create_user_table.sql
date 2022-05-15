create table users(
    id int not null,
    email varchar not null,
    password varchar not null,
    task_limit int not null,
    PRIMARY KEY (id)
)