create table juego(
	id int primary key auto_increment,
    nombre varchar(100),
    genero varchar(20),
	plataformas varchar(100),
    precio decimal(5,2),
    descargas bigint,
    fecha_lanzamiento datetime,
    tipo enum('BASE', 'EXPANSION', 'DLC'),
    completado tinyint
);

-- inserts

insert into juego (nombre, genero, plataformas, precio, descargas, fecha_lanzamiento, tipo, completado) values
	('The Witcher 3: Wild Hunt', 'RPG', 'PC, PS4, Xbox One', 59.99, 25000000, '2015-05-19 00:00:00', 'BASE', 1),
	('The Witcher 3: Hearts of Stone', 'RPG', 'PC, PS4, Xbox One', 19.99, 10000000, '2015-10-13 00:00:00', 'EXPANSION', 1),
	('The Elder Scrolls V: Skyrim', 'RPG', 'PC, PS3, Xbox 360', 39.99, 30000000, '2011-11-11 00:00:00', 'BASE', 1),
	('Fallout 4: Far Harbor', 'RPG', 'PC, PS4, Xbox One', 29.99, 5000000, '2016-05-19 00:00:00', 'DLC', 0),
	('Minecraft', 'Aventura', 'PC, PS4, Xbox One', 26.99, 200000000, '2011-11-18 00:00:00', 'BASE', 1);
