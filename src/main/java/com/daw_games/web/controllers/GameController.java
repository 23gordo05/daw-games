package com.daw_games.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw_games.persistence.entities.Game;
import com.daw_games.services.GameService;
import com.daw_games.services.exceptions.GameNotFoundException;

@RestController
@RequestMapping("/juegos")
public class GameController {

	@Autowired
	private GameService gameService;
	
	// Obtener todos los juegos.
	@GetMapping
	public ResponseEntity<List<Game>> list(){
		return ResponseEntity.status(HttpStatus.OK).body(this.gameService.findAll());
		}
	// Obtener un juego mediante su ID.
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable int id){
		try {
			return ResponseEntity.ok(this.gameService.findById(id));
		} catch (GameNotFoundException g) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(g.getMessage());
		}
	}
	@GetMapping("/genero")
	public ResponseEntity<?> findByGenero(@RequestParam String genero){
		try {
			return ResponseEntity.ok(this.gameService.findByGenero(genero));
		} catch (GameNotFoundException g) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(g.getMessage());
		}
	}
	
	//buscar y obtener con query methods
}
