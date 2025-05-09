package com.daw_games.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw_games.persistence.entities.Game;
import com.daw_games.persistence.entities.enums.Tipo;
import com.daw_games.persistence.repositories.GameRepository;
import com.daw_games.services.exceptions.GameException;
import com.daw_games.services.exceptions.GameNotFoundException;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;
	
	// obtener todos
	public List<Game> findAll() {
		return this.gameRepository.findAll();
	}
	
	// obtener por id
	public Game findById(int id) {
		if (!this.gameRepository.existsById(id)) {
			throw new GameNotFoundException("No existe la tarea con ID: " + id);
		}
		return this.gameRepository.findById(id).get();
	}
	
	// crear un juego
	public Game create(Game game) {
		game.setId(0);
		game.setFechaLanzamiento(LocalDate.now());
		game.setCompletado(false);
		
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
	public Game cambiarCompletado(int id) {
		
		Game g = this.findById(id);
		
		if(g.getCompletado()== true) {
			g.setCompletado(false);
		}
		else {
			g.setCompletado(true);
		}
		
		return this.gameRepository.save(g);
		
	}
	
	//Buscar juegos por género.
	public List<Game> findByGenero(String genero) {
		return this.gameRepository.findByGeneroContaining(genero);
	}
	
	//Buscar juegos por nombre.
	public List<Game> findByName(String name) {
		return this.gameRepository.findByNombreContaining(name);
	}
	
	//Buscar juegos por plataforma.
	public List<Game> findByPlataformas(String plataforma) {
		return this.gameRepository.findByPlataformasContaining(plataforma);
	}
	
	//Obtener las expansiones.
	public List<Game> findByExpansion(Tipo tipo) {
		return this.gameRepository.findByTipo(Tipo.EXPANSION);
	}
	
	//Obtener los DLCs.
	public List<Game> findByDlcs(Tipo tipo) {
		return this.gameRepository.findByTipo(Tipo.DLC);
	}
	
	//Obtener los juegos base.
	public List<Game> findByBase(Tipo tipo) {
		return this.gameRepository.findByTipo(Tipo.BASE);
	}
	
	//Buscar juegos en un rango de precios.
	public List<Game> findByPrecio(double start, double end) {
		return this.gameRepository.findByPrecioBetween(start, end);
	}
	
	//Mostrar los juegos que tengan más de 10000000 descargas.
	public List<Game> findByDescargas() {
			return this.gameRepository.findByDescargasGreaterThan(10000000);
	}

}
