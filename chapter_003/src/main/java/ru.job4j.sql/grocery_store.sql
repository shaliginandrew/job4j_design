create database grocery_store;

create table type(
id serial primary key,
name varchar(255)
);

create table product(
id serial primary key,
name citext,
type_id int references type(id),
expired_date date,
price integer
);

insert into type(name) values ('Сыр');
insert into type(name) values ('Молоко');
insert into type(name) values ('Мороженное');

insert into product(name, type_id, expired_date, price) values ('Голландский', 1, '2020-11-15', 150);
insert into product(name, type_id, expired_date, price) values ('Пармезан', 1, '2021-10-14', 190);
insert into product(name, type_id, expired_date, price) values ('Российский', 1, '2020-11-15', 90);
insert into product(name, type_id, expired_date, price) values ('Швейцарский', 1, '2021-08-20', 110);

insert into product(name, type_id, expired_date, price) values ('Домик в деревне', 2, '2020-11-18', 115);
insert into product(name, type_id, expired_date, price) values ('Простоквашино', 2, '2020-12-15', 120);
insert into product(name, type_id, expired_date, price) values ('Останкинское', 2, '2020-11-18', 130);
insert into product(name, type_id, expired_date, price) values ('33 коровы', 2, '2020-12-15', 140);

insert into product(name, type_id, expired_date, price) values ('Сахарный рожок', 3, '2020-11-18', 55);
insert into product(name, type_id, expired_date, price) values ('Эскимо', 3, '2022-08-18', 40);
insert into product(name, type_id, expired_date, price) values ('Максибон', 3, '2021-10-18', 50);
insert into product(name, type_id, expired_date, price) values ('Мороженное Пломбир', 3, '2021-09-18', 60);
insert into product(name, type_id, expired_date, price) values ('Вкусное мороженное Север', 3, '2021-09-18', 60);

select *from type;
select *from product;

--Написать запрос получение всех продуктов с типом "СЫР"
select *from product where type_id = 1;

--Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select *from product where strpos(name, 'мороженное')>0;

--Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select *from product where expired_date between date('2020-11-01') and date('2020-11-30');

--Написать запрос, который выводит самый дорогой продукт.
SELECT *FROM product WHERE price = (SELECT max(price) FROM product);

--Написать запрос, который выводит количество всех продуктов определенного типа
Select (select COUNT(*) AS Молоко FROM product where type_id = 1),
(SELECT COUNT(*) AS Сыр FROM product where type_id = 2),
(SELECT COUNT(*) AS Мороженное FROM product where type_id = 3);

--Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT *FROM product WHERE type_id = 1 OR type_id = 2;

--Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT type.name FROM type
INNER JOIN product ON product.type_id = type.id
GROUP BY type.name HAVING count(*) < 10;

-- Вывести все продукты и их тип.
SELECT product.name, type.name FROM product
INNER JOIN type ON product.type_id = type.id;