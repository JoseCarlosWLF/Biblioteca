DROP DATABASE IF EXISTS db_modelo2;
CREATE DATABASE IF NOT EXISTS db_modelo2;

USE db_modelo2;

CREATE TABLE Usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL,
    apellido_pat VARCHAR(20) NOT NULL,
    apellido_mat VARCHAR(20) NOT NULL,
    correo VARCHAR(150) NOT NULL,
    telefono VARCHAR(50) NOT NULL,
    contrasena VARCHAR(50) NOT NULL,
    tipo_usuario INT NOT NULL
);

CREATE TABLE Autor (
    id_autor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(250) NOT NULL,
    nacionalidad VARCHAR(100) NOT NULL
);




CREATE TABLE Devolucion (
    id_devolucion INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    fecha_devolucion DATE NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE Sucursal (
    id_sucursal INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(200) NOT NULL,
    id_administrador INT NOT NULL,
    FOREIGN KEY (id_administrador) REFERENCES Usuario(id_usuario)
);



CREATE TABLE Libro ( 
    id_libro INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(250) NOT NULL,
    editorial VARCHAR(100) NOT NULL,
    anio VARCHAR(20) NOT NULL,
    id_autor INT NOT NULL,
    cantidad INT NOT NULL,
    id_sucursal INT NOT NULL,
    FOREIGN KEY (id_autor) REFERENCES Autor(id_autor),
    FOREIGN KEY (id_sucursal) REFERENCES Sucursal(id_sucursal)
);

CREATE TABLE Inventario (
    id_inventario INT AUTO_INCREMENT PRIMARY KEY,
    id_sucursal INT NOT NULL,
    id_libro INT NOT NULL,
    cantidad INT NOT NULL DEFAULT 1,
    FOREIGN KEY (id_sucursal) REFERENCES Sucursal(id_sucursal),
    FOREIGN KEY (id_libro) REFERENCES Libro(id_libro)
);

CREATE TABLE Prestamo (
    id_prestamo INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_libro INT NOT NULL,
    fecha_prestamo DATE NOT NULL,
    fecha_limite DATE NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (id_libro) REFERENCES Libro(id_libro)
);
INSERT INTO Usuario (nombre, apellido_pat, apellido_mat, correo, telefono, contrasena, tipo_usuario) VALUES
('Maria', 'Gonzalez', 'Lopez', 'maria.gonzalez@example.com', '5551234567', 'contrasena123',0),
('Juan', 'Perez', 'Martinez', 'juan.perez@example.com', '5559876543', 'segura456',0),
('Ana', 'Ramirez', 'Santos', 'ana.ramirez@example.com', '5557418529', 'clave789',0),
('Carlos', 'Hernandez', 'Diaz', 'carlos.hernandez@example.com', '5553692587', 'pass1234',0),
('Lucia', 'Flores', 'Garcia', 'lucia.flores@example.com', '5556547891', 'password567',0);

-- Insertar registros en la tabla Autor
INSERT INTO Autor (nombre, nacionalidad) VALUES
('Gabriel Garcia Marquez', 'Colombiana'),
('Julio Cortazar', 'Argentina'),
('Isabel Allende', 'Chilena'),
('J.K. Rowling', 'Britanica'),
('Stephen King', 'Estadounidense');

INSERT INTO Sucursal (nombre, direccion, id_administrador) VALUES
('Sucursal 1', 'Calle 1, Colonia 1, Ciudad 1', 1),
('Sucursal 2', 'Calle 2, Colonia 2, Ciudad 2', 2),
('Sucursal 3', 'Calle 3, Colonia 3, Ciudad 3', 3),
('Sucursal 4', 'Calle 4, Colonia 4, Ciudad 4', 4),
('Sucursal 5', 'Calle 5, Colonia 5, Ciudad 5', 5);
-- Insertar registros en la tabla Libro
INSERT INTO Libro (titulo, editorial, anio ,id_autor, cantidad, id_sucursal) VALUES
('Cien anos de soledad', 'Sudamericana', '2004', 1, 5, 1),
('Rayuela', 'Sudamericana', '2004', 2, 3, 1),
('La casa de los espiritus', 'Plaza & Janes', '2004', 3, 4, 1),
('Harry Potter y la piedra filosofal', 'Bloomsbury', '2004', 4, 7, 1),
('El resplandor', 'Doubleday', '2004', 5, 6, 1);



-- Verificar los registros insertados
SELECT * FROM Usuario;
SELECT * FROM Autor;
SELECT * FROM Libro;
SELECT * FROM Sucursal;


