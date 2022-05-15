create table tasks(
    id int not null,
    user_id int,
    name varchar not null,
    PRIMARY KEY (id),
    constraint user
                  FOREIGN KEY (user_id)
                  REFERENCES users (id)
                  ON DELETE SET NULL
)