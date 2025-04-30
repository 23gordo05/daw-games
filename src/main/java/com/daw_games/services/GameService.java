package com.daw_games.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw_games.persistence.entities.Game;
import com.daw_games.persistence.repositories.GameRepository;
import com.daw_games.services.exceptions.GameException;
import com.daw_games.services.exceptions.GameNotFoundException;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;
	
	// obtener todos
	public List<Game> findAll(){
		return this.gameRepository.findAll();
	}
	
	// obtener por id
	public Game findById(int id) {
		if (!this.gameRepository.existsById(id)) {
			throw new GameNotFoundException("No existe la tarea con ID: " + id);
		}
		return this.gameRepository.findById(id).get();
	}
	
	// crear
	public Game create(Game game) {
		game.setId(0);
		game.setFechaLanzamiento(LocalDate.now());
		
		return this.gameRepository.save(game);
	}
	
	// modificar juego
	public Game update(int id, Game game) {
		if(id != game.getId()) {
			throw new GameException("El ID del path ("+ id +") y el id del body ("+ game.getId() +") no coinciden");
		}
		if(!this.gameRepository.existsById(id)) {
			throw new GameNotFoundException("No existe la juego con ID: " + id);
		}
		if (game.getCompletado() != null) {
			throw new GameException("ni si esta o no completado");
		}
		
		Game gameOne = this.findById(game.getId());
		gameOne.setNombre(game.getNombre());
		gameOne.setGenero(game.getGenero());
		gameOne.setPlataformas(game.getPlataformas());
		gameOne.setPrecio(game.getPrecio());
		gameOne.setTipo(game.getTipo());
		
		return this.gameRepository.save(gameOne);
	}
	
	// borrar un juego
	public boolean delete(int id) {
		return this.gameRepository.findById(id)
					.map(t -> {
						this.gameRepository.deleteById(id);
						return true;
					})
					.orElse(false);
	}
	

	// marcar/desmarcar completado juego
	
	//Buscar juegos por género.
	public List<Game> findByGenero(String genero){
		return this.gameRepository.findByGeneroContaining(genero);
	}
}
