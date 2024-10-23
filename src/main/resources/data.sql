

INSERT INTO book (id, title, authors, publish_year) VALUES ('cf4b7cb2-b447-4fab-916c-db204599c744', 'book title', 'Author Name', NOW());

insert into language (id,language_name) VALUES(1,'spanish');

insert into language (id,language_name) VALUES(2,'english');

INSERT INTO book (id, title, authors, publish_year) VALUES ('2ffea13c-ee74-45fe-b293-46ca206e1292', 'mi libro', 'mauro castillo rondinara', NOW());

insert into book_language (book_id, language_id) VALUES('cf4b7cb2-b447-4fab-916c-db204599c744',1);
insert into book_language (book_id, language_id) VALUES('cf4b7cb2-b447-4fab-916c-db204599c744',2);

insert into book_language (book_id, language_id) VALUES('2ffea13c-ee74-45fe-b293-46ca206e1292',2);