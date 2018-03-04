CREATE TABLE person(
	id SERIAL PRIMARY KEY,
	name TEXT NOT NULL,
	active BOOLEAN DEFAULT FALSE,
	logradouro TEXT,
	number TEXT,
	complement TEXT,
	neighborhood TEXT,
	cep TEXT,
	city TEXT,
	state TEXT
);

INSERT INTO person (name, active, logradouro, number, complement, neighborhood, cep, city, state) 
		VALUES ('João', true, 'Rua das flores', '589', 'sla', 'Bela vista', '86.360-000', 'bandeirantes', 'pr');
		
INSERT INTO person (name, active, logradouro, number, complement, neighborhood, cep, city, state) 
		VALUES ('Maria', true, 'Rua das Rosas', '781', 'Oi', 'Bairro das rosas', '86.300-000', 'cornelio procopio', 'pr');
		
INSERT INTO person (name, active) 
		VALUES ('Carlos', true);
		
INSERT INTO person (name, logradouro, number, complement, neighborhood, cep, city, state) 
		VALUES ('José', 'Avenida 15 de janeiro', '002', 'apt 056', 'Centauro', '86.350-000', 'santa amelia', 'sp');
		
INSERT INTO person (name, active, logradouro, number) 
		VALUES ('Luis', true, 'Rua das Rosas', '125');

