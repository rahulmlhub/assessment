package com.assesment.model;

public class MovieDetail {

	private String movieName;
	private String actorName;
	private String language;
	private String category;
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getActorName() {
		return actorName;
	}
	public void setActorName(String actorName) {
		this.actorName = actorName;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "MovieDetail [movieName=" + movieName + ", actorName=" + actorName + ", language=" + language
				+ ", category=" + category + "]";
	}
	
	
	
}
