package com.daw_games.persistence.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.daw_games.persistence.entities.Game;

public interface GameRepository extends ListCrudRepository<Game, Integer>{

	
//Buscar juegos por género.
	List<Game> findByGeneroContaining(String genero);
}
