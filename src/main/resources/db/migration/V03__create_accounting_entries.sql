CREATE TABLE entry(
	id SERIAL PRIMARY KEY,
	description TEXT NOT NULL,
	due_date DATE NOT NULL,
	date_payment DATE,
	value DECIMAL NOT NULL,
	observation TEXT,
	type TEXT NOT NULL,
	id_category BIGINT NOT NULL,
	id_person BIGINT NOT NULL,
	FOREIGN KEY (id_category) REFERENCES category(id),
	FOREIGN KEY (id_person) REFERENCES person(id)
);


INSERT INTO entry (description, due_date, date_payment, value, observation, type, id_category, id_person) values ('Salário mensal', '2017-06-10', null, 6500.00, 'Distribuição de lucros', 'RECEITA', 1, 1);
INSERT INTO entry (description, due_date, date_payment, value, observation, type, id_category, id_person) values ('Bahamas', '2017-02-10', '2017-02-10', 100.32, null, 'DESPESA', 2, 2);
INSERT INTO entry (description, due_date, date_payment, value, observation, type, id_category, id_person) values ('Top Club', '2017-06-10', null, 120, null, 'RECEITA', 3, 3);
INSERT INTO entry (description, due_date, date_payment, value, observation, type, id_category, id_person) values ('CEMIG', '2017-02-10', '2017-02-10', 110.44, 'Geração', 'RECEITA', 3, 4);
INSERT INTO entry (description, due_date, date_payment, value, observation, type, id_category, id_person) values ('DMAE', '2017-06-10', null, 200.30, null, 'DESPESA', 3, 5);
INSERT INTO entry (description, due_date, date_payment, value, observation, type, id_category, id_person) values ('Extra', '2017-03-10', '2017-03-10', 1010.32, null, 'RECEITA', 4, 5);
INSERT INTO entry (description, due_date, date_payment, value, observation, type, id_category, id_person) values ('Bahamas', '2017-06-10', null, 500, null, 'RECEITA', 1, 4);
INSERT INTO entry (description, due_date, date_payment, value, observation, type, id_category, id_person) values ('Top Club', '2017-03-10', '2017-03-10', 400.32, null, 'DESPESA', 4, 3);
INSERT INTO entry (description, due_date, date_payment, value, observation, type, id_category, id_person) values ('Despachante', '2017-06-10', null, 123.64, 'Multas', 'DESPESA', 3, 2);
INSERT INTO entry (description, due_date, date_payment, value, observation, type, id_category, id_person) values ('Pneus', '2017-04-10', '2017-04-10', 665.33, null, 'RECEITA', 5, 1);
INSERT INTO entry (description, due_date, date_payment, value, observation, type, id_category, id_person) values ('Café', '2017-06-10', null, 8.32, null, 'DESPESA', 1, 5);
INSERT INTO entry (description, due_date, date_payment, value, observation, type, id_category, id_person) values ('Eletrônicos', '2017-04-10', '2017-04-10', 2100.32, null, 'DESPESA', 5, 4);
INSERT INTO entry (description, due_date, date_payment, value, observation, type, id_category, id_person) values ('Instrumentos', '2017-06-10', null, 1040.32, null, 'DESPESA', 4, 3);
INSERT INTO entry (description, due_date, date_payment, value, observation, type, id_category, id_person) values ('Café', '2017-04-10', '2017-04-10', 4.32, null, 'DESPESA', 4, 2);
INSERT INTO entry (description, due_date, date_payment, value, observation, type, id_category, id_person) values ('Lanche', '2017-06-10', null, 10.20, null, 'DESPESA', 4, 1);