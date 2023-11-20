
-- Crear usuarios de ejemplo
INSERT INTO usuarios (nombre, apellido, email, password) VALUES
('Usuario1', 'Apellido1', 'usuario1@example.com', 'contraseña1'),
('Usuario2', 'Apellido2', 'usuario2@example.com', 'contraseña2'),
('Usuario3', 'Apellido3', 'usuario3@example.com', 'contraseña3');

-- Asignar roles a los usuarios
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES
(1, 1),  -- Usuario1 tiene el rol ADMIN
(2, 1),  -- Usuario2 tiene el rol ADMIN
(3, 1); -- Usuario3 tiene el rol ADMIN

-- Crear contactos de ejemplo para cada usuario
-- Usuario1
INSERT INTO contactos (nombre, email, celular, fecha_nacimiento, usuario_id) VALUES
('Contacto1-1', 'contacto1_1@example.com', '12345678', '1990-01-01', 1),
('Contacto1-2', 'contacto1_2@example.com', '23456789', '1991-02-02', 1),
('Contacto1-3', 'contacto1_3@example.com', '34567890', '1992-03-03', 1),
('Contacto1-4', 'contacto1_4@example.com', '45678901', '1993-04-04', 1),
('Contacto1-5', 'contacto1_5@example.com', '56789012', '1994-05-05', 1),
('Contacto1-6', 'contacto1_6@example.com', '67890123', '1995-06-06', 1),
('Contacto1-7', 'contacto1_7@example.com', '78901234', '1996-07-07', 1),
('Contacto1-8', 'contacto1_8@example.com', '89012345', '1997-08-08', 1);

-- Usuario2
INSERT INTO contactos (nombre, email, celular, fecha_nacimiento, usuario_id) VALUES
('Contacto2-1', 'contacto2_1@example.com', '98765432', '1990-01-01', 2),
('Contacto2-2', 'contacto2_2@example.com', '87654321', '1991-02-02', 2),
('Contacto2-3', 'contacto2_3@example.com', '76543210', '1992-03-03', 2),
('Contacto2-4', 'contacto2_4@example.com', '65432109', '1993-04-04', 2),
('Contacto2-5', 'contacto2_5@example.com', '54321098', '1994-05-05', 2),
('Contacto2-6', 'contacto2_6@example.com', '43210987', '1995-06-06', 2),
('Contacto2-7', 'contacto2_7@example.com', '32109876', '1996-07-07', 2),
('Contacto2-8', 'contacto2_8@example.com', '21098765', '1997-08-08', 2);

-- Usuario3
INSERT INTO contactos (nombre, email, celular, fecha_nacimiento, usuario_id) VALUES
('Contacto3-1', 'contacto3_1@example.com', '56789012', '1990-01-01', 3),
('Contacto3-2', 'contacto3_2@example.com', '67890123', '1991-02-02', 3),
('Contacto3-3', 'contacto3_3@example.com', '78901234', '1992-03-03', 3),
('Contacto3-4', 'contacto3_4@example.com', '89012345', '1993-04-04', 3),
('Contacto3-5', 'contacto3_5@example.com', '12345678', '1994-05-05', 3),
('Contacto3-6', 'contacto3_6@example.com', '23456789', '1995-06-06', 3),
('Contacto3-7', 'contacto3_7@example.com', '34567890', '1996-07-07', 3),
('Contacto3-8', 'contacto3_8@example.com', '45678901', '1997-08-08', 3);

--Productos_Sistema
INSERT INTO productos (create_at, precio, nombre) VALUES
('2023-11-14', 15.20, 'Cafe Juan Valdez'),
('2022-12-16', 30.50, 'Bolsa Cafe Canela'),
('2023-05-13', 10.25, 'Bolsa Cafe Puro Colombiano');

