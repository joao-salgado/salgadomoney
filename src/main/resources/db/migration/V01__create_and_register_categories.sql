CREATE TABLE category(
	id SERIAL PRIMARY KEY,
	name TEXT NOT NULL
);

INSERT INTO category (name) VALUES ('lazer');
INSERT INTO category (name) VALUES ('Alimentação');
INSERT INTO category (name) VALUES ('Supermercado');
INSERT INTO category (name) VALUES ('Farmácia');
INSERT INTO category (name) VALUES ('Outros');
