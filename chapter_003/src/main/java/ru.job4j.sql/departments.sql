-- Даны две сущности, представленные в таблицах departments и emploees.
-- Отношение one-to-many. В таблицах только поле name.
-- Создать таблицы и заполнить их начальными данными

create table departments(
    id serial primary key,
    name varchar(255)
);

create table emploees(
    id serial primary key,
    name varchar(255),
    departments_id int references  departments(id)
);


insert into departments(name) values ('IT отдел');
insert into departments(name) values ('Общий отдел');
insert into departments(name) values ('Юридический отдел');
insert into departments(name) values ('Отдел кадров');

insert into emploees(name, departments_id) values ('Иванов', 1);
insert into emploees(name, departments_id) values ('Петров', 2);
insert into emploees(name, departments_id) values ('Сидоров', 3);
insert into emploees(name, departments_id) values ('Зайцев', 4);
insert into emploees(name, departments_id) values ('Шалыгин', 2);
insert into emploees(name, departments_id) values ('Протапов', 2);
insert into emploees(name, departments_id) values ('Никифоров', 3);
insert into emploees(name, departments_id) values ('Нежмаков', 4);
insert into emploees(name, departments_id) values ('Будукина', null);
insert into emploees(name, departments_id) values ('Жидкова', null);

select *from departments;
select *from emploees;

-- Используя left join найти работников, которые не относятся ни к одному из департаментов
select * from emploees e left join departments d on e.departments_id = d.id  where d.id is null;

--Используя left и right join написать запросы, которые давали бы одинаковый результат.
select * from  departments d left join emploees e on e.departments_id = d.id;
select * from emploees e right join departments d on e.departments_id = d.id;

select * from emploees e left join departments d on e.departments_id = d.id;
select * from departments d  right join emploees e on e.departments_id = d.id;

-- full and cross
select * from  emploees e full join departments d  on e.departments_id = d.id;
select * from emploees e cross join departments d;

--Создать таблицу teens с атрибутами name, gender и заполнить ее.

create table teens(
    id serial primary key,
    name varchar(255),
    gender varchar(15)
);

insert into teens(name, gender) values ('Андрей', 'мужской');
insert into teens(name, gender) values ('Ирина', 'женский');
insert into teens(name, gender) values ('Николай', 'мужской');
insert into teens(name, gender) values ('Сергей', 'мужской');


--Используя cross join составить все возможные разнополые пары

SELECT  t1.name, t1.gender, t2.name, t2.gender FROM  teens t1 CROSS JOIN teens t2
WHERE t1.gender = 'женский' and t2.gender = 'мужской';