
--Read Tipo usuario
SELECT DISTINCT rol_cliente, rol
FROM clientes, empleados
WHERE clientes.rol_cliente IN ('cliente') AND empleados.rol in ('gerente', 'admin_datos', 'recepcionista', 'empleado');

--Create Tipo usuario





--R Usuario
SELECT * 
FROM clientes, empleados;

--C
INSERT INTO clientes(num_doc, nombre, email, tipo_doc, rol_cliente)
VALUES(123123123, 'John', 'doe@yahoo.com', 'CC', 'cliente');

INSERT INTO empleados(num_doc, nombre, email, tipo_doc, rol)
VALUES(42324, 'Sarah', 's.winters@gmail.com', 'Passport', 'admin_datos');

--U
UPDATE clientes
SET email='m.newman@hotmail.com'
WHERE num_doc=1;

UPDATE empleados
SET email='s.las@icloud.com'
WHERE num_doc=2;

--D

DELETE FROM clientes WHERE num_doc=123123123;

DELETE FROM empleados WHERE num_doc=42324;





--Tipo habitacion
--R
SELECT *
FROM tipos_habitacion;

--C
INSERT INTO tipos_habitacion(id_tipo, costo, capacidad, descripcion, tipo)
VALUES(100, 200, 10, 'cafetera', 'sencilla');

--U
UPDATE tipos_habitacion
SET costo=1000
WHERE tipo='suite_presidencial';

--D
DELETE FROM tipos_habitacion WHERE capacidad=0;


--Habitacion
--R
SELECT * 
FROM habitaciones;

--C
INSERT INTO habitaciones(id_habitacion, consumo_acumulado, disponibilidad, reservas_id_reserva, tipos_habitacion_id_tipo)
VALUES(13, 5, 1, 27, 100);

--U
UPDATE habitaciones
SET disponibilidad=1
WHERE consumo_acumulado>9000;

--D
DELETE FROM habitaciones WHERE disponibilidad=0;


--SERVICIO
--R
SELECT DISTINCT *
FROM servicios;

--C
INSERT INTO servicios(id_servicio, disponibilidad, nombre, descripcion, ts_tipo_servicio)
VALUES(57, 1, 'kayak_pisicina', 'riesgoso pero no tanto', 'piscina');

--U
UPDATE servicios
SET descripcion='TOCA DEVOLVERLO'
WHERE ts_tipo_servicio='prestamo';

--D
DELETE FROM servicios WHERE id_servicio=57;




--Plan de consumo
--R
SELECT * 
FROM planesdeconsumo;

--C
INSERT INTO planesdeconsumo(id_plan,nombre,descripcion,tipos_plan_tipo_plan)
VALUES(57,'de terror', 'plan limitado por epoca de halloween', 'promocion_particular');

--U
UPDATE planesdeconsumo
SET nombre='PARA MORIRSE'
WHERE id_plan=57;

--D
DELETE FROM planesdeconsumo WHERE id_plan=57;




--Reserva de alojamiento

--R
SELECT id_reserva,num_personas, estados_reservas_fecha_inicial AS fecha_in, estados_reservas_fecha_fin AS fecha_fin, planesdeconsumo_id_plan AS plan_id, clientes_num_doc AS id_cliente, id_habitacion, consumo_acumulado, disponibilidad, tipos_habitacion_id_tipo AS tipo_habitacion
FROM reservas R
INNER JOIN habitaciones H ON R.id_reserva=H.reservas_id_reserva;

--C
INSERT INTO reservas(id_reserva, num_personas, estados_reservas_fecha_inicial, estados_reservas_fecha_fin,planesdeconsumo_id_plan,clientes_num_doc)
VALUES(7, 10, to_date('2022-11-22', 'yyyy-mm-dd'), to_date('2023-10-08','yyyy-mm-dd'), 11, 5);

--U
UPDATE reservas
SET num_personas=0 
WHERE id_reserva=27;
--D
DELETE FROM reservas WHERE id_reserva=49;




--Reservas servicios
--R
SELECT*
FROM reservan;

--C
INSERT INTO reservan (id_habitacion, servicios_id, ts_tipo_servicio, fecha_inicio, fecha_final)
VALUES(201,4, 'piscina', to_date('2022-11-22', 'yyyy-mm-dd'), to_date('2023-10-08','yyyy-mm-dd'));

--U
UPDATE reservan
SET ts_tipo_servicio='spa'
WHERE id_habitacion=201;

--D
DELETE FROM reservan WHERE id_habitacion=201;



--Llegada al hotel
--R
SELECT clientes.num_doc
FROM clientes
INNER JOIN reservas ON clientes.num_doc = reservas.clientes_num_doc
INNER JOIN registran ON clientes.num_doc = registran.clientes_num_doc
WHERE check_in=1;

--C
INSERT INTO registran(clientes_num_doc, empleados_num_doc, check_in)
VALUES(5, 3, 1);

--U
UPDATE registran 
SET check_in = 0
WHERE registran.clientes_num_doc=32;

--D
DELETE FROM registran WHERE check_in=2;


--RF10

SELECT *
FROM clientes C
INNER JOIN reservas R ON R.clientes_num_doc=C.num_doc
INNER JOIN habitaciones H ON H.reservas_id_reserva=R.id_reserva
INNER JOIN reservan Rn ON Rn.id_habitacion=H.id_habitacion
INNER JOIN servicios S ON Rn.servicios_id = S.id_servicio;







