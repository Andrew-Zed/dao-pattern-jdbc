drop table if exists bookdb2 cascade;
drop table if exists author;

CREATE TABLE IF NOT EXISTS bookdb2 (
  id BIGSERIAL PRIMARY KEY,
  isbn VARCHAR(50),
  publisher VARCHAR(255),
  title VARCHAR(255),
  author_id BIGINT
);


CREATE TABLE author (
  id BIGSERIAL PRIMARY KEY,
  first_name VARCHAR(50),
  last_name VARCHAR(50)
);

ALTER TABLE bookdb2
    ADD CONSTRAINT book_author_fk FOREIGN KEY (author_id) REFERENCES author(id);

INSERT INTO author (first_name, last_name) VALUES ('Brian', 'Tracy');

INSERT INTO bookdb2 (isbn, publisher, title, author_id) VALUES ('971-1617294945', 'Brian Tracy', 'Self Discipline',
        (SELECT id FROM author WHERE first_name = 'Brian' and last_name = 'Tracy'));

INSERT INTO author (first_name, last_name) VALUES ('Craig', 'Walls');

INSERT INTO bookdb2 (isbn, publisher, title, author_id) VALUES ('741-2727294754', 'Craig Walls', 'Spring in Action, 5th
        Edition', (SELECT id FROM author WHERE first_name = 'Craig' and last_name = 'Walls'));

INSERT INTO author (first_name, last_name) VALUES ('George R', 'Martin');

INSERT INTO bookdb2 (isbn, publisher, title, author_id) VALUES ('641-2997294235', 'George R Martin', 'A Song of Ice and Fire',
        (SELECT id FROM author WHERE first_name = 'George R' and last_name = 'Martin'));

INSERT INTO author (first_name, last_name) VALUES ('John', 'Grisham');

INSERT INTO bookdb2 (isbn, publisher, title, author_id) VALUES ('294-1059934220', 'John Grisham', 'A Song of Ice and Fire',
        (SELECT id FROM author WHERE first_name = 'John' and last_name = 'Grisham'));