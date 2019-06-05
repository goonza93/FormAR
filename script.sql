-- SCRIPT PARA CREAR LA BASE DE DATOS
-- EJECUTAR DIRECTAMENTE, NO IMPORTA SI EXISTE O NO PREVIAMENTE

-- CREA LA BD, SI YA EXISTIA PRIMERO LA ELIMINA
DROP DATABASE IF EXISTS formar_grupo4;
CREATE DATABASE formar_grupo4;
USE formar_grupo4;

-- ESQUEMA SALAS
CREATE TABLE formar_salas (
	ID INT(4) AUTO_INCREMENT,
	numero INT(4) NOT NULL,
	nombre VARCHAR(50),
	capacidad INT(4) NOT NULL,
	activo BOOLEAN NOT NULL,
	PRIMARY KEY (ID)
);

-- ESQUEMA CONFIGURACION
CREATE TABLE formar_configuracion (
	clave VARCHAR(100),
	valor VARCHAR(100),
    PRIMARY KEY (clave)
);

-- ESQUEMA DIAS DE LA SEMANA
CREATE TABLE formar_dias (
	ID INT(4) AUTO_INCREMENT,
	descripcion VARCHAR(20),
	PRIMARY KEY (ID)
);

-- ESQUEMA ROLES DE EMPLEADO
CREATE TABLE formar_roles (
	ID INT(1) AUTO_INCREMENT,
	descripcion VARCHAR(50),
	PRIMARY KEY (ID)
);

-- ESQUEMA ALUMNOS
CREATE TABLE formar_alumnos (
	ID INT(4) AUTO_INCREMENT,
	DNI VARCHAR(20) NOT NULL UNIQUE,
	nombre VARCHAR(50) NOT NULL,
	apellido VARCHAR(50) NOT NULL,
	telefono VARCHAR(50),
	email VARCHAR(50) NOT NULL,
	activo BOOLEAN NOT NULL,
	bloqueado BOOLEAN NOT NULL,
	PRIMARY KEY (ID)
);

-- ESQUEMA DE AREAS DE INTERES
CREATE TABLE formar_areas (
	ID INT(4) AUTO_INCREMENT,
	nombre VARCHAR(50),
	descripcion VARCHAR(200),
	activo BOOLEAN NOT NULL,
	PRIMARY KEY (ID)
);

-- ESQUEMA ESTADO DE CURSO
CREATE TABLE formar_estados_curso (
	ID INT(4) AUTO_INCREMENT,
	descripcion VARCHAR(20),
	PRIMARY KEY (ID)
);

-- ESQUEMA DE PROGRAMAS
CREATE TABLE formar_programas (
	ID INT(4) AUTO_INCREMENT,
	area INT(11),
	nombre VARCHAR(50) NOT NULL,
	fecha_aprobacion DATE DEFAULT NULL,
	descripcion VARCHAR(500) NOT NULL,
	horas INT(4) NOT NULL,
	activo BOOLEAN NOT NULL,
	codigo VARCHAR(20) NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (area) REFERENCES formar_areas(ID)
);

-- ESQUEMAS HORARIOS
CREATE TABLE formar_horarios (
	ID INT(4) AUTO_INCREMENT,
	dia INT(1) NOT NULL,
	hora_inicio INT(2) NOT NULL,
	hora_fin INT(2) NOT NULL,
    minuto_inicio INT(2) NOT NULL,
    minuto_fin INT(2) NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (dia) REFERENCES formar_dias(ID)
);

-- ESQUEMA DE EMPLEADOS
CREATE TABLE formar_empleados (
	ID INT(4) AUTO_INCREMENT,
	rol INT(1) NOT NULL,
	DNI VARCHAR(20) NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	apellido VARCHAR(50) NOT NULL,
	telefono VARCHAR(20),
	email VARCHAR(50) NOT NULL,
	fecha_ingreso DATE NOT NULL,
	fecha_egreso DATE NULL,
	activo BOOLEAN NOT NULL,
	usuario VARCHAR(50) NOT NULL UNIQUE,
	password VARCHAR(50) NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (rol) REFERENCES formar_roles(ID)
);

-- ESQUEMA PARA GUARDAR LOS PDF
CREATE TABLE formar_archivos (
	ID INT(4) AUTO_INCREMENT,
	nombre VARCHAR(50) NOT NULL,
	archivo mediumblob DEFAULT NULL,
	PRIMARY KEY (ID)
);

-- ESQUEMA CURSOS
CREATE TABLE formar_cursos (
	ID INT(4) AUTO_INCREMENT,
	cupo_minimo INT(3),
	cupo_maximo INT(3),
	precio INT(5),
	fecha_inicio DATE,
	fecha_fin DATE,
	fecha_cierre DATE,
    contenido INT(4) NULL,
    comision VARCHAR(10),
	horas INT(5) NOT NULL,
	instructor INT(4) NULL,
	responsable INT(4) NULL,
	programa INT(4) NOT NULL,
	estado INT(4) NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (instructor) REFERENCES formar_empleados(ID),
	FOREIGN KEY (responsable) REFERENCES formar_empleados(ID),
	FOREIGN KEY (programa) REFERENCES formar_programas(ID),
	FOREIGN KEY (estado) REFERENCES formar_estados_curso(ID),
	FOREIGN KEY (contenido) REFERENCES formar_archivos(ID)
);

-- ESQUEMA DE ASISTENCIAS
CREATE TABLE formar_asistencias (
	ID INT(10) AUTO_INCREMENT,
	curso INT(4) NOT NULL,
	alumno INT(4) NOT NULL,
	fecha DATE NOT NULL,
	presente BOOLEAN NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (curso) REFERENCES formar_cursos(ID),
	FOREIGN KEY (alumno) REFERENCES formar_alumnos(ID)
);

-- ESQUEMA EXAMENES
CREATE TABLE formar_examenes (
	ID INT(4) AUTO_INCREMENT,
	alumno INT(4) DEFAULT NULL,
	curso INT(4) DEFAULT NULL,
	fecha DATE NOT NULL,
	descripcion VARCHAR(50) NOT NULL,
	nota DOUBLE NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (curso) REFERENCES formar_cursos(ID),
	FOREIGN KEY (alumno) REFERENCES formar_alumnos(ID)
);

-- ESQUEMA INSCRIPCIONES
CREATE TABLE formar_inscripciones (
	ID INT(4) AUTO_INCREMENT,
	alumno INT(4) NOT NULL,
	empleado INT(4) NOT NULL,
	curso INT(4) NOT  NULL,
	fecha DATE NOT NULL,
	nota DOUBLE DEFAULT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (alumno) REFERENCES formar_alumnos(ID),
	FOREIGN KEY (empleado) REFERENCES formar_empleados(ID),
	FOREIGN KEY (curso) REFERENCES formar_cursos(ID)
);

-- ESQUEMA HORARIOS DE CURSADA CON SALA
CREATE TABLE formar_horarios_cursada (
	ID INT(4) AUTO_INCREMENT,
	curso INT(4) NOT NULL,
	horario INT(4) NOT NULL,
    sala INT(4),
	activo BOOLEAN NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (curso) REFERENCES formar_cursos(ID),
	FOREIGN KEY (horario) REFERENCES formar_horarios(ID),
	FOREIGN KEY (sala) REFERENCES formar_salas(ID)
);

-- ESQUEMA INTERESADOS
CREATE TABLE formar_interesados (
	ID INT(4) AUTO_INCREMENT,
	DNI VARCHAR(20) NOT NULL UNIQUE,
	nombre VARCHAR(50) NOT NULL,
	apellido VARCHAR(50) NOT NULL,
	telefono VARCHAR(50),
	email VARCHAR(50),
	PRIMARY KEY (ID)
);

-- ESQUEMA INTERACCIONES
CREATE TABLE formar_interacciones (
	ID INT(4) AUTO_INCREMENT,
	empleado INT(4) NOT NULL,
	interesado INT(4) NOT NULL,
	area INT(4) NULL,
	curso INT(4) NULL,
	fecha DATE NOT NULL,
	descripcion VARCHAR(500) NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (empleado) REFERENCES formar_empleados(ID),
	FOREIGN KEY (interesado) REFERENCES formar_interesados(ID),
	FOREIGN KEY (area) REFERENCES formar_areas(ID),
	FOREIGN KEY (curso) REFERENCES formar_programas(ID)
);

-- ESQUEMA RECADOS
CREATE TABLE formar_recados (
	ID INT(4) AUTO_INCREMENT,
	emisor INT(4) NOT NULL,
	receptor INT(4) NOT NULL,
    empleado INT(4) NOT NULL,
    titulo VARCHAR(20) NOT NULL,
    contenido VARCHAR(1000) NOT NULL,
	leido BOOLEAN NOT NULL,
	archivado BOOLEAN NOT NULL,
	fecha DATE NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (emisor) REFERENCES formar_empleados(ID),
	FOREIGN KEY (receptor) REFERENCES formar_empleados(ID),
	FOREIGN KEY (empleado) REFERENCES formar_empleados(ID)
);

-- ESQUEMA MESES
CREATE TABLE formar_meses(
	ID INT(4) AUTO_INCREMENT,
	descripcion VARCHAR(20),
	PRIMARY KEY (ID)
);

-- ESQUEMA PAGOS
CREATE TABLE formar_pagos (
	ID INT(4) AUTO_INCREMENT,
	alumno INT(4) NOT NULL,
	cursada INT(4) NOT NULL,
    empleado INT(4) ,
    monto INT(10) NOT NULL,
	mes INT(2) NOT NULL,
	pago_en_termino BOOLEAN ,
	pago_completo BOOLEAN NOT NULL,
	fecha DATE,
	PRIMARY KEY (ID),
	FOREIGN KEY (alumno) REFERENCES formar_alumnos(ID),
	FOREIGN KEY (cursada) REFERENCES formar_cursos(ID),
	FOREIGN KEY (empleado) REFERENCES formar_empleados(ID)
);

-- ESQUEMA TAREAS
CREATE TABLE formar_tareas (
	ID INT(4) AUTO_INCREMENT,
	empleado INT(4) NOT NULL,
	contenido VARCHAR(100) NOT NULL,
	pendiente BOOLEAN NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (empleado) REFERENCES formar_empleados(ID)
);

-- ESQUEMA NOTIFICACIONES
CREATE TABLE formar_notificaciones (
	ID INT(4) AUTO_INCREMENT,
	tipo INT(1) NOT NULL,
	empleado INT(4) NOT NULL,
	contenido VARCHAR(100) NOT NULL,
	mostrado BOOLEAN NOT NULL,
	leido BOOLEAN NOT NULL,
	fecha DATETIME,
	PRIMARY KEY (ID),
	FOREIGN KEY (empleado) REFERENCES formar_empleados(ID)
);





-- INSERT DE DATOS PREDEFINIDOS

-- INSERTAR DIAS DE LA SEMANA
INSERT INTO formar_dias (descripcion) VALUES ("Domingo");
INSERT INTO formar_dias (descripcion) VALUES ("Lunes");
INSERT INTO formar_dias (descripcion) VALUES ("Martes");
INSERT INTO formar_dias (descripcion) VALUES ("Miércoles");
INSERT INTO formar_dias (descripcion) VALUES ("Jueves");
INSERT INTO formar_dias (descripcion) VALUES ("Viernes");
INSERT INTO formar_dias (descripcion) VALUES ("Sábado");

-- INSERTAR MESES DEL AÑO
INSERT INTO formar_meses (descripcion) VALUES ("Enero");
INSERT INTO formar_meses (descripcion) VALUES ("Febrero");
INSERT INTO formar_meses (descripcion) VALUES ("Marzo");
INSERT INTO formar_meses (descripcion) VALUES ("Abril");
INSERT INTO formar_meses (descripcion) VALUES ("Mayo");
INSERT INTO formar_meses (descripcion) VALUES ("Junio");
INSERT INTO formar_meses (descripcion) VALUES ("Julio");
INSERT INTO formar_meses (descripcion) VALUES ("Agosto");
INSERT INTO formar_meses (descripcion) VALUES ("Septiembre");
INSERT INTO formar_meses (descripcion) VALUES ("Octubre");
INSERT INTO formar_meses (descripcion) VALUES ("Noviembre");
INSERT INTO formar_meses (descripcion) VALUES ("Diciembre");

-- INSERTAR ESTADOS DE CURSO
INSERT INTO formar_estados_curso (descripcion) VALUES ("Creado");
INSERT INTO formar_estados_curso (descripcion) VALUES ("Publicado");
INSERT INTO formar_estados_curso (descripcion) VALUES ("Iniciado");
INSERT INTO formar_estados_curso (descripcion) VALUES ("Finalizado");
INSERT INTO formar_estados_curso (descripcion) VALUES ("Cancelado");

-- INSERTAR ROLES DE EMPLEADO
INSERT INTO formar_roles (descripcion) VALUES ('ADMINISTRATIVO');
INSERT INTO formar_roles (descripcion) VALUES ('INSTRUCTOR');
INSERT INTO formar_roles (descripcion) VALUES ('SUPERVISOR');

-- INSERTAR SALA "FANTASMA"
INSERT INTO formar_salas (nombre, numero, capacidad, activo) VALUES ("A DESIGNAR", 0, 9999, true);

-- INSERTAR ADMIN
-- LAS CONTRASENAS ESTA CIFRADA, PERO ES ADMIN
INSERT INTO formar_empleados (dni, rol, nombre, apellido, telefono, email, fecha_ingreso, activo, usuario, password)
VALUES ("00000000", 3, "Admin", "Root", "0000000000", "admin@gmail.com", '1900-01-01', true, "admin", "21232f297a57a5a743894a0e4a801fc3");

-- INSERTAR EMAIL
-- INSERT INTO formar_salas (nombre, numero, capacidad, activo) VALUES ("A DESIGNAR", 0, 9999, true);

