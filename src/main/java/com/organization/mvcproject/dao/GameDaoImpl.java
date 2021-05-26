package com.organization.mvcproject.dao;

import java.util.ArrayList;
import java.util.List;

import com.organization.mvcproject.models.Game;

public class GameDaoImpl implements GameDao {
	private static Long gameId = new Long(0);
	private static Long companyId = new Long(0);
	private static List<Game> games = new ArrayList<Game>();

	static {
		games = populateGames();
	}

	private static List<Game> populateGames() {

		Game game1 = new Game();
		game1.setId(++gameId);
		game1.setGenre("Sport");
		game1.setName("Rocket League");

		Game game2 = new Game();
		game2.setId(++gameId);
		game2.setGenre("Shooter");
		game2.setName("Halo 3");

		Game game3 = new Game();
		game3.setId(++gameId);
		game3.setGenre("MMORPG");
		game3.setName("Runescape");

		games.add(game1);
		games.add(game2);
		games.add(game3);

		return games;
	}

	@Override
	public List<Game> retrieveAllGames() {
		return games;
	}

	@Override
	public Game saveGame(Game game) {
		game.setId(++gameId);
		games.add(game);
		return game;
	}

	@Override
	public List<Game> findGame(Long id) {
		for(Game game: games) {
			if(id.equals(game.getId())) {
				List <Game> retList = new ArrayList<Game>();
				retList.add(game);
				return retList;
			}
		}
		return null;
	}
	
	@Override
	public List<Game>findGame(String genre){
		List<Game> returnList = new ArrayList<Game>();
		for(Game game : games) {
			if(genre.equals(game.getGenre())){
				returnList.add(game);
			}
		}
		return returnList;
		
	}

	@Override
	public void removeGame(Long id) {
		List <Game> gameToDeleteList = findGame(id);
		Game gameToDelete = gameToDeleteList.get(0);
		if(!games.contains(gameToDelete)) {
			System.out.println("game doesnt exist");
		}
		else {
			games.remove(games.indexOf(gameToDelete));
		}
	}

}
