package com.organization.mvcproject.dao;

import java.util.List;

import com.organization.mvcproject.models.Game;

public interface GameDao {
	List<Game> retrieveAllGames();

	Game saveGame(Game game);
	
	Game findGame(Long id);
	
	void removeGame(Long id);
}
