package com.daw_games.persistence.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.daw_games.persistence.entities.Game;
import com.daw_games.persistence.entities.enums.Tipo;

public interface GameRepository extends ListCrudRepository<Game, Integer>{

	
	//Buscar juegos por género.
		List<Game> findByGeneroContaining(String genero);
	//Buscar juegos por nombre.
		List<Game> findByNombreContaining(String name);
	//Buscar juegos por plataforma.
		List<Game> findByPlataformasContaining(String plataforma);
	//Obtener las expansiones.
	//Obtener los DLCs.
	//Obtener los juegos base.
		List<Game> findByTipo(Tipo tipo);
	//Buscar juegos en un rango de precios.
		List<Game> findByPrecioBetween(double start, double end);
	// Mostrar los juegos que tengan mas de 10000000 descargas.
		List<Game> findByDescargasGreaterThan(long descargas);
}
