CREATE DATABASE IF NOT EXISTS `usuario_bd`;
USE `usuario_bd`;

CREATE TABLE IF NOT EXISTS `usuario` (
    `documento` varchar(11) NOT NULL PRIMARY KEY,
    `nombre` varchar(80) NOT NULL,
    `profesion` varchar(200) NOT NULL,
    `edad` int(3) NOT NULL,
    `direccion` varchar(200) NOT NULL,
    `telefono` varchar(10) NOT NULL,
    `tipo` int(2) NOT NULL,
    `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `producto` (
    `idProducto` VARCHAR(25) NOT NULL PRIMARY KEY UNIQUE,
    `nombre` VARCHAR(25) NOT NULL UNIQUE,
    `precio` INT NOT NULL,
    `cantidad` INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `usuario_tiene_producto` (
    `idProducto` VARCHAR(25) NOT NULL,
    `documento` VARCHAR(11) NOT NULL,
    FOREIGN KEY (documento) REFERENCES usuario(documento),
    FOREIGN KEY (idProducto) REFERENCES producto(idProducto)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO usuario (documento,nombre,profesion,edad,direccion,telefono,tipo,estado) VALUES ("admin","Administrador","",0,"","",1,1)