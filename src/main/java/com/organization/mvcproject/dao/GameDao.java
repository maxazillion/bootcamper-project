package com.organization.mvcproject.dao;

import java.util.List;

import com.organization.mvcproject.models.Game;

public interface GameDao {
	List<Game> retrieveAllGames();

	Game saveGame(Game game);
	
	List <Game> findGame(Long id);
	
	List <Game> findGame(String genre);
	
	void removeGame(Long id);
	
}
