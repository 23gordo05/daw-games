package com.daw_games.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw_games.persistence.entities.Game;
import com.daw_games.persistence.entities.enums.Tipo;
import com.daw_games.services.GameService;
import com.daw_games.services.exceptions.GameException;
import com.daw_games.services.exceptions.GameNotFoundException;

@RestController
@RequestMapping("/juegos")
public class GameController {

	@Autowired
	private GameService gameService;
	
	// Obtener todos los juegos.
	@GetMapping
	public ResponseEntity<List<Game>> list() {
		return ResponseEntity.status(HttpStatus.OK).body(this.gameService.findAll());
	}
	
	// Obtener un juego mediante su ID.
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable int id) {
		try {
			return ResponseEntity.ok(this.gameService.findById(id));
		} catch (GameNotFoundException g) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(g.getMessage());
		}
	}
	
	//Crear un juego.
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Game game) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.gameService.create(game));
	}
	
	// modificar juego.
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable int id, @RequestBody Game game) {
		try {
			return ResponseEntity.ok(this.gameService.update(id, game));
		} catch (GameNotFoundException g) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(g.getMessage());
		} catch (GameException g) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(g.getMessage());
		}
	}
	
	//Borra un juego.
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		try {
			this.gameService.delete(id);
			return ResponseEntity.ok("La tarea con ID("+ id +") ha sido borrada correctamente. ");
		} catch (GameNotFoundException g) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(g.getMessage());
		}
	}
	
	//Marcar/desmarcar un juego como completado.
	@PutMapping("/{id}/completar")
	public ResponseEntity<?> cambiarCompletado(@PathVariable int id) {
		try {
			this.gameService.cambiarCompletado(id);
			return ResponseEntity.ok("El estado a sido cambiado");
		} catch (GameNotFoundException g) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(g.getMessage());
		}
	}
	
	//Buscar juegos por género.
	@GetMapping("/genero")
	public ResponseEntity<?> findByGenero(@RequestParam String genero) {
		try {
			return ResponseEntity.ok(this.gameService.findByGenero(genero));
		} catch (GameNotFoundException g) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(g.getMessage());
		}
	}
	
	// Buscar juegos por nombre.
	@GetMapping("/name")
	public ResponseEntity<?> findByName(@RequestParam String name) {
		try {
			return ResponseEntity.ok(this.gameService.findByName(name));
		} catch (GameNotFoundException g) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(g.getMessage());
		}
	}
	
	//Buscar juegos por plataforma 
	@GetMapping("/plataformas")
	public ResponseEntity<?> findByPlataforma(@RequestParam String plataforma) {
		try {
			return ResponseEntity.ok(this.gameService.findByPlataformas(plataforma));
		} catch (GameNotFoundException g) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(g.getMessage());
		}
	}
	
	//Obtener las expansiones
	@GetMapping("/expansiones")
	public ResponseEntity<?> findByExpansion() {
		try {
			return ResponseEntity.ok(this.gameService.findByExpansion(Tipo.EXPANSION));
		} catch (GameNotFoundException g) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(g.getMessage());
		}
	}
	
	//Obtener las DLCs
	@GetMapping("/dlcs")
	public ResponseEntity<?> findByDlcs() {
		try {
			return ResponseEntity.ok(this.gameService.findByDlcs(Tipo.DLC));
		} catch (GameNotFoundException g) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(g.getMessage());
		}
	}
	
	//Obtener las juegos base
	@GetMapping("/juegosBase")
	public ResponseEntity<?> findByBase() {
		try {
			return ResponseEntity.ok(this.gameService.findByBase(Tipo.BASE));
		} catch (GameNotFoundException g) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(g.getMessage());
		}
	}
	
	//Buscar juegos en un rango de precios.
	@GetMapping("/precios")
	public ResponseEntity<?> findByPrecios(@RequestParam double start, @RequestParam double end) {
		try {
			return ResponseEntity.ok(this.gameService.findByPrecio(start, end));
		} catch (GameException g) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(g.getMessage());
		}
	}
	
	// Mostrar los juegos que tengan mas de 10000000 descargas.
	@GetMapping("/descargas")
	public ResponseEntity<?> findByDescargas(){
		try {
			return ResponseEntity.ok(this.gameService.findByDescargas());
		} catch (GameException g) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(g.getMessage());
		}
	}
}