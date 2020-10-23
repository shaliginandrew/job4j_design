create table carbody (
 id serial primary key,
    name varchar(20)
);

create table engine (
 id serial primary key,
   capacity numeric(2,1)
);

create table transmission (
 id serial primary key,
 type varchar(20)
);

create table car(
 id serial primary key,
 name varchar(20),
 id_carbody int references carbody(id),
 id_capacity int references engine(id),
 id_transmission int references transmission(id)
);

insert into carbody(name) values ('Седан');
insert into carbody(name) values ('Кабриолет');
insert into carbody(name) values ('Хетчбек');
insert into carbody(name) values ('Купе');

insert into engine(capacity) values (1.6);
insert into engine(capacity) values (1.8);
insert into engine(capacity) values (2.0);
insert into engine(capacity) values (2.5);
insert into engine(capacity) values (3.0);

insert into transmission(type) values ('АКПП');
insert into transmission(type) values ('МКПП');
insert into transmission(type) values ('Вариатор');
insert into transmission(type) values ('Гидравлическая');
insert into transmission(type) values ('Электромеханическая');

insert into car(name, id_carbody, id_capacity, id_transmission) values ('Вольво S60', 1, 2, 1);
insert into car(name, id_carbody, id_capacity, id_transmission) values ('Вольво S80', 3, 4, 2);
insert into car(name, id_carbody, id_capacity, id_transmission) values ('Мерседес', 2, 1, 1);
insert into car(name, id_carbody, id_capacity, id_transmission) values ('Ауди', 1, 4, 1);
insert into car(name, id_carbody, id_capacity, id_transmission) values ('БМВ', 1, 2, 2);
insert into car(name, id_carbody, id_capacity, id_transmission) values ('Шкода', 2, 3, 2);
insert into car(name, id_carbody, id_capacity, id_transmission) values ('Нива', 2, 3, 5);

-- Вывести список всех машин и все привязанные к ним детали
select car.name, carbody.name, engine.capacity, transmission.type
from car
join carbody on car.id_carbody = carbody.id
join engine on car.id_capacity = engine.id
join transmission on car.id_transmission = transmission.id;

-- Вывести отдельно детали, которые не используются в машине: кузова, двигатели, коробки передач
select carbody.name as Тип_кузова from carbody left join car on carbody.id = car.id_carbody where car.name is null;

select engine.capacity as Объем_двигателя from engine left join car on engine.id = car.id_capacity where car.name is null;

select transmission.type as Коробка_передач from transmission left join car on transmission.id = car.id_transmission where car.name is null;