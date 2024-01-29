create table users
(
    id       bigint       NOT NULL AUTO_INCREMENT,
    name varchar(100) not null,
    age int not null,
    primary key (id)
);

INSERT INTO users (id, NAME, AGE)
VALUES
    (1, 'user', 30),
    (2, 'user2', 33),
    (3, 'user3', 39),
    (4, 'user4', 23),
    (5, 'user5', 44);
