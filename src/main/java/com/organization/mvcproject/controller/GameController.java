package com.organization.mvcproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.organization.mvcproject.MGL_Task1.service.Game_Service;
import com.organization.mvcproject.dao.GameDao;
import com.organization.mvcproject.dao.GameDaoImpl;
import com.organization.mvcproject.models.Game;
import com.organization.mvcproject.models.Review;


@Controller
public class GameController {

	@Autowired
	private Game_Service javaGameService;
	private static GameDaoImpl dao = new GameDaoImpl();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "index";
	}

	@RequestMapping(value = "/review", method = RequestMethod.GET)
	public ModelAndView review() {
		System.out.println("this is: review");
		return new ModelAndView("review", "command", new Review());
	}

	@RequestMapping(value = "/addReview", method = RequestMethod.POST)
	public ModelAndView addReview(Review review, ModelMap model) {
		System.out.println("this is: add review");
		if(review.getAuthor().equals("")) {
			review.setAuthor("anonymous");
		}
		return new ModelAndView("result", "submittedReview", review);
	}

	@RequestMapping(value = "/games", method = RequestMethod.GET)
	public ModelAndView game() {
		return new ModelAndView("games", "command", new Game());
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<Game>> fetchAllGames() {
		return new ResponseEntity<List<Game>>(dao.retrieveAllGames(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getFiltered", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Game>> filterGames(@RequestBody String filterTerm) {
		return new ResponseEntity<List<Game>>(dao.findGame(filterTerm), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getFilteredById", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Game>> filterGamesById(@RequestBody Long id) {
		return new ResponseEntity<List<Game>>(dao.findGame(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/createGame", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createGame(@RequestBody Game game) {
		dao.saveGame(game);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/removeGame", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> removeGame(@RequestBody Long id){
		dao.removeGame(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}