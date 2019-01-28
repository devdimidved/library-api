INSERT INTO user(id, last_name, first_name, username, password, balance) VALUES (100, 'Doe', 'John', 'john.doe', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 140.00);
INSERT INTO user(id, last_name, first_name, username, password, balance) VALUES (101, 'Fox', 'Samantha', 'sam.fox', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 750.33);
INSERT INTO user(id, last_name, first_name, username, password, balance) VALUES (102, 'Brown', 'Robert', 'bob.bro', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 1300.17);

INSERT INTO book(id, title, author, isbn, rent_price) values (100, 'Thinking in Java (4th Edition)', 'Bruce Ekel', '978-0131872486', 100);
INSERT INTO book(id, title, author, isbn, rent_price) values (101, 'Effective Java', 'Joshua Bloch', '978-0134685991', 125);
INSERT INTO book(id, title, author, isbn, rent_price) values (102, 'Spring Boot in Action', 'Craig Walls', '978-1617292545', 130);
INSERT INTO book(id, title, author, isbn, rent_price) values (103, 'Head First Java, 2nd Edition', 'Kathy Sierra', '978-0596009205', 95);
INSERT INTO book(id, title, author, isbn, rent_price) values (104, 'Kotlin in Action', 'Dmitry Jemerov', '978-1617293290', 180);
INSERT INTO book(id, title, author, isbn, rent_price) values (105, 'Kotlin for Android App Development', 'Peter Sommerhoff', '978-0134854199', 165);
INSERT INTO book(id, title, author, isbn, rent_price) values (106, 'Spring Boot in Action 1', 'Craig Walls', '978-1617292545', 100);
INSERT INTO book(id, title, author, isbn, rent_price) values (107, 'Spring Boot in Action 2', 'Craig Walls', '978-1617292545', 100);
INSERT INTO book(id, title, author, isbn, rent_price) values (108, 'Spring Boot in Action 3', 'Craig Walls', '978-1617292545', 100);
INSERT INTO book(id, title, author, isbn, rent_price) values (109, 'Spring Boot in Action 4', 'Craig Walls', '978-1617292545', 100);
INSERT INTO book(id, title, author, isbn, rent_price) values (110, 'Spring Boot in Action 5', 'Craig Walls', '978-1617292545', 100);
INSERT INTO book(id, title, author, isbn, rent_price) values (111, 'Spring Boot in Action 6', 'Craig Walls', '978-1617292545', 100);
INSERT INTO book(id, title, author, isbn, rent_price) values (112, 'Spring Boot in Action 7S', 'Craig Walls', '978-1617292545', 100);

INSERT INTO rent(id, user_id, book_id, amount, creation_date, expiry_date, is_active) values (1, 100, 101, 125, '2018-12-01', '2019-01-01', false);
INSERT INTO rent(id, user_id, book_id, amount, creation_date, expiry_date, is_active) values (2, 100, 101, 125, '2019-01-03', '2019-02-03', true);
