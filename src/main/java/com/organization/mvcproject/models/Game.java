package com.organization.mvcproject.models;

import org.springframework.stereotype.Component;

@Component
public class Game {

	private Long id;
	private String name;
	private String genre;
	
	public Long getId() {
		return id;
	}

	public void setId(Long gameId) {
		this.id = gameId;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", genre=" + genre + "]";
	}

	public void setName(String gameName) {
		this.name = gameName;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String gameGenre) {
		this.genre = gameGenre;
	}

}
