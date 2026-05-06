CREATE database PORTAL_DE_COMPRAS_EN_LINEA;


use PORTAL_DE_COMPRAS_EN_LINEA;

-- ==========================================================
-- PROYECTO: PORTAL DE COMPRAS EN LÍNEA - UMG
-- DESTINO: SQL Server 2012 o superior
-- ==========================================================

-- 1. ELIMINACIÓN DE TABLAS (OPCIONAL - Para limpieza en pruebas)
/*
DROP TABLE IF EXISTS MOVIMIENTO_INVENTARIO;
DROP TABLE IF EXISTS PROVEEDOR;
DROP TABLE IF EXISTS PRODUCTO_IMAGEN;
DROP TABLE IF EXISTS TEMPORADA_PRODUCTO;
DROP TABLE IF EXISTS TEMPORADA;
DROP TABLE IF EXISTS TRANSACCION_PAGO;
DROP TABLE IF EXISTS ORDEN_ITEM;
DROP TABLE IF EXISTS ORDEN;
DROP TABLE IF EXISTS METODO_PAGO;
DROP TABLE IF EXISTS DIRECCION;
DROP TABLE IF EXISTS CARRITO_ITEM;
DROP TABLE IF EXISTS CARRITO;
DROP TABLE IF EXISTS PRODUCTO;
DROP TABLE IF EXISTS CATEGORIA;
DROP TABLE IF EXISTS CLIENTE;
DROP TABLE IF EXISTS CUPON_DESCUENTO;
DROP TABLE IF EXISTS SIMULADOR_BANCO;
*/

-- 2. CREACIÓN DE TABLAS BASE

CREATE TABLE CLIENTE (
    id_cliente BIGINT IDENTITY(1,1) NOT NULL,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    email VARCHAR(150),
    password_hash VARCHAR(255),
    telefono VARCHAR(20),
    fecha_nacimiento DATE,
    sexo CHAR(1),
    active CHAR(1),
    fecha_registro DATE DEFAULT GETDATE(),
    CONSTRAINT CLIENTE_PK PRIMARY KEY (id_cliente)
);



CREATE TABLE CATEGORIA (
    id_categoria NUMERIC(28) IDENTITY(1,1) NOT NULL,
    nombre VARCHAR(100),
    descripcion VARCHAR(255),
    orden NUMERIC(28),
    active CHAR(1),
    fecha_creacion DATE DEFAULT GETDATE(),
    CATEGORIA_id_categoria NUMERIC(28), -- Autoreferencia para subcategorías[cite: 2]
    CONSTRAINT CATEGORIA_PK PRIMARY KEY (id_categoria)
) ;


CREATE TABLE PRODUCTO (
    id_producto NUMERIC(28) IDENTITY(1,1) NOT NULL,
    nombre VARCHAR(150),
    descripcion_corta VARCHAR(255),
    descripcion_larga VARCHAR(MAX),
    precio_venta NUMERIC(28,2),
    stock NUMERIC(28),
    stock_minimo NUMERIC(28),
    codigo_barras VARCHAR(50),
    peso_kilogramos NUMERIC(28,2),
    active CHAR(1),
    fecha_creacion DATE DEFAULT GETDATE(),
    CONSTRAINT PRODUCTO_PK PRIMARY KEY (id_producto)
) ;

CREATE TABLE PRODUCTO_CATEGORIA (
    id_producto NUMERIC(28) NOT NULL,
    id_categoria NUMERIC(28) NOT NULL,
    CONSTRAINT PRODUCTO_CATEGORIA_PK PRIMARY KEY (id_producto, id_categoria),
    CONSTRAINT FK_PC_PRODUCTO FOREIGN KEY (id_producto) REFERENCES PRODUCTO(id_producto),
    CONSTRAINT FK_PC_CATEGORIA FOREIGN KEY (id_categoria) REFERENCES CATEGORIA(id_categoria)
);



-- Tabla para cumplir con el requisito de 1 a 10 imágenes por producto[cite: 2]
CREATE TABLE PRODUCTO_IMAGEN (
    id_imagen NUMERIC(28) IDENTITY(1,1) NOT NULL,
    id_producto NUMERIC(28) NOT NULL,
    url_imagen VARCHAR(255) NOT NULL,
    es_principal CHAR(1) DEFAULT 'N',
    orden_visualizacion NUMERIC(2),
    CONSTRAINT PRODUCTO_IMAGEN_PK PRIMARY KEY (id_imagen)
) ;


CREATE TABLE CARRITO (
    id_carrito NUMERIC(28) IDENTITY(1,1) NOT NULL,
    id_cliente NUMERIC(28),
    sesion_anonima VARCHAR(255),
    fecha_creacion DATE DEFAULT GETDATE(),
    fecha_modificacion DATE,
    CONSTRAINT CARRITO_PK PRIMARY KEY (id_carrito)
) ;


CREATE TABLE CARRITO_ITEM (
    id_item NUMERIC(28) IDENTITY(1,1) NOT NULL,
    id_carrito NUMERIC(28) NOT NULL,
    id_producto NUMERIC(28) NOT NULL,
    cantidad NUMERIC(28),
    precio_unitario NUMERIC(28,2),
    fecha_agregado DATE DEFAULT GETDATE(),
    CONSTRAINT CARRITO_ITEM_PK PRIMARY KEY (id_item)
) ;


CREATE TABLE DIRECCION (
    id_direccion NUMERIC(28) IDENTITY(1,1) NOT NULL,
    id_cliente NUMERIC(28) NOT NULL,
    alias VARCHAR(50), -- Ej: 'Casa', 'Trabajo'
    calle VARCHAR(100),
    numero_casa VARCHAR(20),
    colonia VARCHAR(100),
    ciudad VARCHAR(100),
    departamento VARCHAR(100),
    pais VARCHAR(100),
    codigo_postal VARCHAR(20),
    es_principal CHAR(1),
    CONSTRAINT DIRECCION_PK PRIMARY KEY (id_direccion)
) ;


CREATE TABLE CUPON_DESCUENTO (
    id_cupon NUMERIC(28) IDENTITY(1,1) NOT NULL,
    codigo VARCHAR(50) UNIQUE,
    porcentaje_descuento NUMERIC(28),
    monto_fijo NUMERIC(28,2),
    fecha_expiracion DATE,
    uso_maximo NUMERIC(28),
    activo CHAR(1),
    CONSTRAINT CUPON_DESCUENTO_PK PRIMARY KEY (id_cupon)
) ;


CREATE TABLE ORDEN (
    id_orden NUMERIC(28) IDENTITY(1,1) NOT NULL,
    id_cliente NUMERIC(28) NOT NULL,
    id_direccion NUMERIC(28) NOT NULL,
    fecha_orden DATE DEFAULT GETDATE(),
    subtotal NUMERIC(28,2),
    impuestos NUMERIC(28,2),
    total NUMERIC(28,2),
    estado VARCHAR(50),
    notas VARCHAR(500),
    id_cupon NUMERIC(28),
    CONSTRAINT ORDEN_PK PRIMARY KEY (id_orden)
);

CREATE TABLE ORDEN_ITEM (
    id_orden_item NUMERIC(28) IDENTITY(1,1) NOT NULL,
    id_orden NUMERIC(28) NOT NULL,
    id_producto NUMERIC(28) NOT NULL,
    cantidad NUMERIC(28),
    precio_unitario NUMERIC(28,2),
    subtotal NUMERIC(28,2),
    CONSTRAINT ORDEN_ITEM_PK PRIMARY KEY (id_orden_item)
);


CREATE TABLE METODO_PAGO (
    id_metodo_pago NUMERIC(28) IDENTITY(1,1) NOT NULL,
    id_cliente NUMERIC(28) NOT NULL,
    tipo_metodo VARCHAR(50),
    numero_enmascarado VARCHAR(50),
    titular VARCHAR(150),
    mes_vencimiento NUMERIC(2),
    anio_vencimiento NUMERIC(4),
    token_banco VARCHAR(255),
    es_principal CHAR(1),
    CONSTRAINT METODO_PAGO_PK PRIMARY KEY (id_metodo_pago)
);

-- Tabla para el Servlet de autorización (Base de datos paralela simulada)[cite: 2]
CREATE TABLE SIMULADOR_BANCO (
    id_usuario INT IDENTITY(1,1) NOT NULL,
    numero_tarjeta VARCHAR(16) NOT NULL,
    nombre_titular VARCHAR(150) NOT NULL,
    mes_vencimiento INT NOT NULL,
    anio_vencimiento INT NOT NULL,
    cvv VARCHAR(4) NOT NULL,
    saldo_disponible NUMERIC(28,2),
    CONSTRAINT PK_BANCO PRIMARY KEY (id_usuario)
);

CREATE TABLE TRANSACCION_PAGO (
    id_transaccion NUMERIC(28) IDENTITY(1,1) NOT NULL,
    id_orden NUMERIC(28) NOT NULL,
    id_metodo_pago NUMERIC(28) NOT NULL,
    numero_autorizacion VARCHAR(100),
    monto NUMERIC(28,2),
    estado CHAR(1),
    respuesta_banco VARCHAR(255),
    fecha_transaccion DATE DEFAULT GETDATE(),
    CONSTRAINT TRANSACCION_PAGO_PK PRIMARY KEY (id_transaccion)
) ;


CREATE TABLE TEMPORADA (
    id_temporada NUMERIC(28) IDENTITY(1,1) NOT NULL,
    nombre VARCHAR(100), -- Ej: 'Navidad', 'Día de la Madre'[cite: 2]
    descripcion VARCHAR(255),
    fecha_inicio DATE,
    fecha_fin DATE,
    imagen_banner VARCHAR(255),
    active CHAR(1),
    CONSTRAINT TEMPORADA_PK PRIMARY KEY (id_temporada)
);

CREATE TABLE TEMPORADA_PRODUCTO (
    id_temp_prod NUMERIC(28) IDENTITY(1,1) NOT NULL,
    id_temporada NUMERIC(28) NOT NULL,
    id_producto NUMERIC(28) NOT NULL,
    precio_promocional NUMERIC(28,2),
    descuento_pct NUMERIC(28),
    CONSTRAINT TEMPORADA_PRODUCTO_PK PRIMARY KEY (id_temp_prod)
);

CREATE TABLE PROVEEDOR (
    id_proveedor NUMERIC(28) IDENTITY(1,1) NOT NULL,
    nombre_empresa VARCHAR(150),
    contacto_nombre VARCHAR(150),
    email VARCHAR(150),
    CONSTRAINT PROVEEDOR_PK PRIMARY KEY (id_proveedor)
) ;

CREATE TABLE MOVIMIENTO_INVENTARIO (
    id_movimiento NUMERIC(28) IDENTITY(1,1) NOT NULL,
    id_producto NUMERIC(28) NOT NULL,
    id_proveedor NUMERIC(28),
    tipo_movimiento VARCHAR(50), -- 'ENTRADA', 'SALIDA'
    cantidad NUMERIC(28),
    fecha_movimiento DATE DEFAULT GETDATE(),
    CONSTRAINT MOVIMIENTO_INVENTARIO_PK PRIMARY KEY (id_movimiento)
);

-- Se puede comentar, solo verifica que las tablas se hayan creado correctamente.
--SELECT TABLE_SCHEMA, TABLE_NAME
--FROM INFORMATION_SCHEMA.TABLES
--WHERE TABLE_TYPE = 'BASE TABLE';

-- 3. DEFINICIÓN DE RELACIONES (FOREIGN KEYS)

GO
ALTER TABLE CATEGORIA
ADD CONSTRAINT FK_CAT_SUB FOREIGN KEY (CATEGORIA_id_categoria) REFERENCES CATEGORIA (id_categoria);
GO

ALTER TABLE PRODUCTO_IMAGEN
ADD CONSTRAINT FK_IMG_PROD FOREIGN KEY (id_producto) REFERENCES PRODUCTO (id_producto);
GO

ALTER TABLE CARRITO
ADD CONSTRAINT FK_CARRITO_CLI FOREIGN KEY (id_cliente) REFERENCES CLIENTE (id_cliente);
GO

ALTER TABLE CARRITO_ITEM
ADD CONSTRAINT FK_ITEM_CARR FOREIGN KEY (id_carrito) REFERENCES CARRITO (id_carrito);
GO

ALTER TABLE CARRITO_ITEM
ADD CONSTRAINT FK_ITEM_PROD FOREIGN KEY (id_producto) REFERENCES PRODUCTO (id_producto);
GO

ALTER TABLE DIRECCION
ADD CONSTRAINT FK_DIR_CLI FOREIGN KEY (id_cliente) REFERENCES CLIENTE (id_cliente);
GO

ALTER TABLE ORDEN
ADD CONSTRAINT FK_ORD_CLI FOREIGN KEY (id_cliente) REFERENCES CLIENTE (id_cliente);
GO

ALTER TABLE ORDEN
ADD CONSTRAINT FK_ORD_DIR FOREIGN KEY (id_direccion) REFERENCES DIRECCION (id_direccion);
GO

ALTER TABLE ORDEN
ADD CONSTRAINT FK_ORD_CUP FOREIGN KEY (id_cupon) REFERENCES CUPON_DESCUENTO (id_cupon);
GO

ALTER TABLE ORDEN_ITEM
ADD CONSTRAINT FK_OITEM_ORD FOREIGN KEY (id_orden) REFERENCES ORDEN (id_orden);
GO

ALTER TABLE ORDEN_ITEM
ADD CONSTRAINT FK_OITEM_PROD FOREIGN KEY (id_producto) REFERENCES PRODUCTO (id_producto);
GO

ALTER TABLE METODO_PAGO
ADD CONSTRAINT FK_MET_CLI FOREIGN KEY (id_cliente) REFERENCES CLIENTE (id_cliente);
GO

ALTER TABLE TRANSACCION_PAGO
ADD CONSTRAINT FK_TRA_ORD FOREIGN KEY (id_orden) REFERENCES ORDEN (id_orden);
GO

ALTER TABLE TRANSACCION_PAGO
ADD CONSTRAINT FK_TRA_MET FOREIGN KEY (id_metodo_pago) REFERENCES METODO_PAGO (id_metodo_pago);
GO

ALTER TABLE TEMPORADA_PRODUCTO
ADD CONSTRAINT FK_TP_TEMP FOREIGN KEY (id_temporada) REFERENCES TEMPORADA (id_temporada);
GO

ALTER TABLE TEMPORADA_PRODUCTO
ADD CONSTRAINT FK_TP_PROD FOREIGN KEY (id_producto) REFERENCES PRODUCTO (id_producto);
GO

ALTER TABLE MOVIMIENTO_INVENTARIO
ADD CONSTRAINT FK_MOV_PROD FOREIGN KEY (id_producto) REFERENCES PRODUCTO (id_producto);
GO

ALTER TABLE MOVIMIENTO_INVENTARIO
ADD CONSTRAINT FK_MOV_PROV FOREIGN KEY (id_proveedor) REFERENCES PROVEEDOR (id_proveedor);
GO



--SELECT TABLE_NAME, CONSTRAINT_NAME, CONSTRAINT_TYPE
--FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS
--WHERE CONSTRAINT_TYPE = 'FOREIGN KEY';


SELECT * FROM CATEGORIA

-- 4. CARGA DE DATOS MAESTROS (25 CATEGORÍAS)[cite: 2]
INSERT INTO CATEGORIA (nombre, descripcion, orden, active, CATEGORIA_id_categoria) VALUES
('Ropa', 'Moda general', 1, 'Y', NULL),
('Electrónicos', 'Gadgets y equipos', 2, 'Y', NULL),
('Hogar', 'Muebles y más', 3, 'Y', NULL),
('Hombre', 'Ropa masculina', 1, 'Y', 1), -- Subcategoría de Ropa[cite: 2]
('Mujer', 'Ropa femenina', 2, 'Y', 1),
('Smartphones', 'Celulares actuales', 1, 'Y', 2),
('Laptops', 'Computadoras de alto rendimiento', 2, 'Y', 2),
('Cocina', 'Accesorios de cocina', 1, 'Y', 3),
('Decoración', 'Artículos decorativos para el hogar', 2, 'Y', 3),
('Niños', 'Ropa y accesorios infantiles', 3, 'Y', 1),
('Deportes', 'Artículos deportivos y fitness', 4, 'Y', NULL),
('Zapatos', 'Calzado variado', 5, 'Y', 1),
('Accesorios', 'Bolsos, cinturones y más', 6, 'Y', 1),
('Audio', 'Parlantes, audífonos y equipos de sonido', 3, 'Y', 2),
('Televisores', 'Pantallas y Smart TV', 4, 'Y', 2),
('Oficina', 'Muebles y suministros de oficina', 5, 'Y', 3),
('Jardín', 'Herramientas y decoración exterior', 6, 'Y', 3),
('Belleza', 'Cosméticos y cuidado personal', 7, 'Y', NULL),
('Salud', 'Productos médicos y bienestar', 8, 'Y', NULL),
('Videojuegos', 'Consolas y accesorios gamer', 9, 'Y', 2),
('Fotografía', 'Cámaras y accesorios', 10, 'Y', 2),
('Automotriz', 'Accesorios para vehículos', 11, 'Y', NULL),
('Mascotas', 'Alimentos y accesorios para mascotas', 12, 'Y', NULL),
('Papelería', 'Útiles escolares y de oficina', 13, 'Y', 16),
('Instrumentos Musicales', 'Guitarras, teclados y más', 14, 'Y', NULL);
GO


GO
DECLARE @i INT = 1;
DECLARE @cat_id INT;
DECLARE @barcode VARCHAR(50);
DECLARE @peso_rand NUMERIC(28,2);

WHILE @i <= 2000
BEGIN
    -- Seleccionar categoría aleatoria
    SET @cat_id = (SELECT TOP 1 id_categoria FROM CATEGORIA ORDER BY NEWID());

    -- Generar un código de barras aleatorio de 13 dígitos (estilo EAN-13)
    SET @barcode = CAST(CAST(ABS(CHECKSUM(NEWID())) AS BIGINT) AS VARCHAR) + CAST(CAST(ABS(CHECKSUM(NEWID())) AS BIGINT) AS VARCHAR);
    SET @barcode = LEFT(@barcode, 13);

    -- Generar peso aleatorio entre 0.30 y 3.00 kg
    -- Fórmula: (Random entre 0 y 270) / 100 + 0.3
    SET @peso_rand = (CAST(ABS(CHECKSUM(NEWID())) % 271 AS NUMERIC(28,2)) / 100.0) + 0.30;

    INSERT INTO PRODUCTO (
        nombre,
        descripcion_corta,
        descripcion_larga, -- Agregado: Requisito del proyecto
        precio_venta,
        stock,
        stock_minimo,
        codigo_barras,    -- Agregado: Para el buscador
        peso_kilogramos,  -- Agregado: Para logística
        active
    )
    VALUES (
        'Producto UMG ' + CAST(@i AS VARCHAR),
        'Descripción breve del producto ' + CAST(@i AS VARCHAR),
        'Esta es la descripción larga detallada para el producto número ' + CAST(@i AS VARCHAR) + '. Ideal para mostrar en la vista de detalle del portal.',
        (ABS(CHECKSUM(NEWID())) % 1000) + 50.00,
        100,
        10,
        @barcode,
        @peso_rand,
        'Y'
    );

    -- Insertar imagen aleatoria (Cumple requisito de 1 a 10 imágenes)
    INSERT INTO PRODUCTO_IMAGEN (id_imagen, id_producto, url_imagen, es_principal, orden_visualizacion)
    VALUES (@i, @i, 'https://picsum.photos/400?random=' + CAST(@i AS VARCHAR), 'Y', 1);

    SET @i = @i + 1;
END
GO


-- 7. VERIFICACIÓN DE CARGA (OPCIONAL)
-- Ejecuta estas líneas para confirmar que los datos se generaron correctamente
SELECT COUNT(*) AS Total_Productos FROM PRODUCTO;
SELECT COUNT(*) AS Total_Categorias FROM CATEGORIA;
SELECT TOP 5 * FROM PRODUCTO_IMAGEN;
GO


GO
ALTER PROCEDURE sp_insert_cliente_encrypt
    @nombre VARCHAR(100),
    @apellido VARCHAR(100),
    @email VARCHAR(150),
    @password VARCHAR(255),
    @telefono VARCHAR(20),
    @fecha_nacimiento DATE,
    @sexo CHAR(1)
AS
BEGIN
    IF LEN(LTRIM(RTRIM(@nombre))) <= 2
        THROW 50001, 'El nombre debe tener una longitud mayor a 2 caracteres.', 1;

    IF LEN(LTRIM(RTRIM(@apellido))) <= 2
        THROW 50002, 'El apellido debe tener una longitud mayor a 2 caracteres.', 1;

    IF @email NOT LIKE '%@%' OR LEN(@email) <= 10
        THROW 50003, 'El email debe ser válido y tener una longitud mayor a 10 caracteres.', 1;

    IF LEN(@password) <= 8
        THROW 50004, 'La contraseña debe tener una longitud mayor a 8 caracteres.', 1;

    IF LEN(LTRIM(RTRIM(@telefono))) < 8
        THROW 50005, 'El teléfono debe tener un mínimo de 8 caracteres.', 1;

    IF @sexo NOT IN ('M', 'F')
        THROW 50006, 'El campo sexo solo acepta valores M o F.', 1;

    IF @fecha_nacimiento >= GETDATE()
        THROW 50007, 'El cliente no puede haber nacido en el futuro...', 1;

    INSERT INTO CLIENTE(nombre, apellido, email, password_hash, telefono, fecha_nacimiento, sexo, active, fecha_registro)
    VALUES(
        @nombre, @apellido, @email, CONVERT( VARCHAR(255), HASHBYTES('SHA2_256', @password), 2),
        @telefono, @fecha_nacimiento, @sexo, 'Y', GETDATE()
    )
END;
GO
