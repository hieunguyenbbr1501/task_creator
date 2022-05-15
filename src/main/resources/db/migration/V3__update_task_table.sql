alter table tasks
add column if not exists created_at datetime;

alter table tasks
    add column if not exists description text;