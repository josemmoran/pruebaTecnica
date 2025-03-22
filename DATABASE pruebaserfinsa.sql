CREATE DATABASE pruebaserfinsa;
use pruebaserfinsa;

CREATE TABLE producto (
  id_prod int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  precio float NOT NULL,
  descripcion varchar(100) ,
  nombre_prod varchar(50) NOT NULL,
  stock varchar(50) NOT NULL,
  tipo_producto varchar(50) NOT NULL,
  PRIMARY KEY (id_prod)
);


CREATE TABLE rol (
  id int NOT NULL  PRIMARY KEY AUTO_INCREMENT,
  rol_nombre enum('ROLE_ADMIN','ROLE_USER') NOT NULL,
 
);

CREATE TABLE usuario (
  id int NOT NULL AUTO_INCREMENT,
  intentos_ingreso int DEFAULT NULL,
  email varchar(255) NOT NULL,
  estado varchar(255) DEFAULT NULL,
  nombre varchar(255) NOT NULL,
  nombre_usuario varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UKpuhr3k3l7bj71hb7hk7ktpxn0 (nombre_usuario)
);

CREATE TABLE usuario_rol (
  rol_id int NOT NULL,
  usuario_id int NOT NULL,
  PRIMARY KEY (rol_id,usuario_id)
);