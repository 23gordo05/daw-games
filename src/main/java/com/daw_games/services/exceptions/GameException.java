package com.daw_games.services.exceptions;

public class GameException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public GameException(String message) {
		super(message);
	}
}
