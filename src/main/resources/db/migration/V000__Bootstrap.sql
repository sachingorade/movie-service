CREATE SEQUENCE movie_id_seq INCREMENT 1;

CREATE TABLE movies (
    id BIGINT PRIMARY KEY ,
    name VARCHAR(255) NOT NULL ,
    length INT NOT NULL,
    year INT NOT NULL,
    thumbnail VARCHAR(130)
);