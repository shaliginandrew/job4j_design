create database users_and_roles;
create table rule(
    id serial primary key,
    name varchar(255)
);
create table role(
    id serial primary key,
    name varchar(255)
);
create table rule_of_role(
    id serial primary key,
    rule_id int references rule(id),
    role_id int references role(id)
);

create table users(
    id serial primary key,
    name varchar(255),
    role_id int references role(id)
);

create table state(
    id serial primary key,
    name varchar(255)
);
create table category(
    id serial primary key,
    name varchar(255)
);
create table item(
    id serial primary key,
    name varchar(255),
    user_id int references users(id),
    state_id int references state(id),
    category_id int references category(id)
);
create table comment(
    id serial primary key,
    name varchar(255),
    item_id int references item(id)
);
create table attachs(
    id serial primary key,
    name varchar(255),
    item_id int references item(id)
);
insert into rule(name) values ('Просматривать');
insert into rule(name) values ('Редактировать');
insert into rule(name) values ('Удалять');

insert into role(name) values ('Гость');
insert into role(name) values ('Пользователь');
insert into role(name) values ('Администратор');

insert into rule_of_role(rule_id, role_id) values (1, 1);
insert into rule_of_role(rule_id, role_id) values (1, 2);
insert into rule_of_role(rule_id, role_id) values (3, 3);

insert into users(name, role_id) values ('Андрей', 3);
insert into users(name, role_id) values ('Сергей', 2);
insert into users(name, role_id) values ('Николай', 1);

insert into state(name) values ('Новая');
insert into state(name) values ('В работе');
insert into state(name) values ('Выполнено');

insert into category(name) values ('Стажер');
insert into category(name) values ('Джуниор');
insert into category(name) values ('Мидл');

insert into item(name, user_id, state_id, category_id) values ('Спроектировать базу данных', 1, 2, 2);

insert into comment(name, item_id) values ('Выполнить до 18.00', 1);

insert into attachs(name, item_id) values ('Использовать UML диаграммы', 1);