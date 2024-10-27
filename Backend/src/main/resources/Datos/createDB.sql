CREATE DATABASE Ecommerce;

DROP TABLE IF EXISTS Detalle_Orden;
DROP TABLE IF EXISTS Orden;
DROP TABLE IF EXISTS Producto;
DROP TABLE IF EXISTS Categoria;
DROP TABLE IF EXISTS Cliente;

CREATE TABLE Categoria(
	id_categoria SERIAL NOT NULL,
	nombre VARCHAR(100),
	PRIMARY KEY (id_categoria)
);

CREATE TABLE Cliente(
	id_cliente SERIAL NOT NULL,
	nombre VARCHAR(255),
	direccion VARCHAR(255),
	email VARCHAR(100),
	telefono VARCHAR(20),
	PRIMARY KEY (id_cliente)
);

CREATE TABLE Producto(
	id_producto SERIAL NOT NULL,
	nombre VARCHAR(255),
	descripcion TEXT,
	precio DECIMAL(10, 2),
	stock INT,
	estado VARCHAR(50),
	id_categoria INT,
	PRIMARY KEY (id_producto),
	FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria)
);

CREATE TABLE Orden(
	id_orden SERIAL NOT NULL,
	fecha_orden TIMESTAMP,
	estado VARCHAR(50),
	id_cliente INT,
	total DECIMAL(10, 2),
	PRIMARY KEY (id_orden),
	FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente)
);

CREATE TABLE Detalle_Orden(
	id_detalle SERIAL NOT NULL,
	id_orden INT,
	id_producto INT,
	cantidad INT,
	precio_unitario DECIMAL (10, 2),
	PRIMARY KEY (id_detalle),
	FOREIGN KEY (id_orden) REFERENCES Orden(id_orden),
	FOREIGN KEY (id_producto) REFERENCES Producto(id_producto)
);