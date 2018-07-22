create database inventario;

use inventario;

CREATE TABLE clientes(
	dni_cli VARCHAR(50) PRIMARY KEY,
    nombre_cli VARCHAR(50) NOT NULL,
    apellidos_cli VARCHAR(100) NOT NULL,
    movil_cli VARCHAR(50) NOT NULL,
    telefono_cli VARCHAR(50) NOT NULL,
    direccion_cli VARCHAR(50) NOT NULL
);

INSERT INTO clientes VALUES("66666666G", "Bruno", "Díaz Martín", "111111111", "222222222", "C/Inventada Nº22");
SELECT * from clientes;

CREATE TABLE productos(
		id_pro INTEGER PRIMARY KEY AUTO_INCREMENT,
        descripcion_pro VARCHAR(255) NOT NULL,
		precio_pro DOUBLE NOT NULL
);

INSERT INTO productos(descripcion_pro, precio_pro) VALUES("Gofres de caramelo", 2.79);
INSERT INTO productos(descripcion_pro, precio_pro) VALUES("Pringels BBQ", 1);
SELECT * FROM productos;

CREATE TABLE facturas(
	id_fac INTEGER PRIMARY KEY AUTO_INCREMENT,
    dni_cli_fac VARCHAR(9) NOT NULL,
    subtotal_fac DOUBLE NOT NULL,
    descuento_fac DOUBLE NOT NULL,
    impuesto_fac DOUBLE NOT NULL,
    total_fac DOUBLE NOT NULL,
    CONSTRAINT cli_fk FOREIGN KEY (dni_cli_fac) REFERENCES clientes(dni_cli)
);

INSERT INTO facturas(dni_cli_fac, subtotal_fac, descuento_fac, impuesto_fac, total_fac) VALUES("66666666G", 10.37, 2, 7, 10.87);
SELECT * FROM facturas;

CREATE TABLE facturas_productos(
	id_fac_pro INTEGER NOT NULL,
    id_pro_fac INTEGER NOT NULL,
    cantidad_facpro INTEGER NOT NULL,
    total_facpro DOUBLE NOT NULL,
    CONSTRAINT fac_fk FOREIGN KEY (id_fac_pro) REFERENCES facturas(id_fac),
    CONSTRAINT pro_fk FOREIGN KEY (id_pro_fac) REFERENCES productos(id_pro)
);

INSERT INTO facturas_productos VALUES(1, 1, 3, 8.37);
INSERT INTO facturas_productos VALUES(1, 2, 2, 2);
SELECT * FROM facturas_productos;

CREATE TABLE usuarios(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    pass VARCHAR(100) NOT NULL
);

INSERT INTO usuarios(username, pass) VALUES('brunodm99', 'dayamon15');
SELECT * FROM usuarios;

/*Obtiene la factura x*/
SELECT 
	id_fac,
    dni_cli,
    nombre_cli,
    subtotal_fac,
    descuento_fac,
    impuesto_fac,
    total_fac,
    id_pro,
    descripcion_pro,
    precio_pro,
    cantidad_facpro,
    total_facpro
FROM clientes, facturas INNER JOIN facturas_productos ON facturas.id_fac=facturas_productos.id_fac_pro
INNER JOIN productos ON facturas_productos.id_pro_fac=productos.id_pro
WHERE id_fac=1 AND facturas.dni_cli_fac=dni_cli
ORDER BY id_fac;
