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
import com.organization.mvcproject.models.Game;
import com.organization.mvcproject.models.Review;


@Controller
public class GameController {

	@Autowired
	private Game_Service javaGameService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		System.out.println("this is: home");
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
		return new ResponseEntity<List<Game>>(javaGameService.retrieveAllGames(), HttpStatus.OK);
	}

	@RequestMapping(value = "/createGame", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createGame(@RequestBody Game game) {
		javaGameService.saveGame(game);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
//	@RequestMapping(value = "/remove", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Void> deleteGame(@RequestBody Long id){
//		javaGameService.deleteGame(id);
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}
}