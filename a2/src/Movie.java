// -------------------------------------------------------------------- 
// Assignment 2
// Question: Part 1-2-3
// Written by: Mingming Zhang 
//
// For COMP 249 Section (S) – Winter 2024
// --------------------------------------------------------------------

import java.io.Serializable;

/**
 * Name: Mingming Zhang
 * 
 * ID: 40258080
 * 
 * COMP249 Section (S)
 * 
 * Assignment # 2
 * 
 * Due Date: 03/27/2024
 * 
 * @author Mingming Zhang
 * @version 03/13/2024
 */
public class Movie implements Serializable {
	// --------- Attributes ---------//
	/**
	 * attribute int year
	 */
	private int year;
	
	/**
	 * attribute String title
	 */
	private String title;
	
	/**
	 * attribute int duration
	 */
	private int duration;
	
	/**
	 * attribute String genre
	 */
	private String genre;
	
	/**
	 * attribute  String rating
	 */
	private String rating;
	
	/**
	 * attribute double score
	 */
	private double score;
	
	/**
	 * attribute String director
	 */
	private String director;
	
	/**
	 * attribute  String actor1
	 */
	private String actor1;
	
	/**
	 * attribute  String actor2
	 */
	private String actor2;
	
	/**
	 * attribute  String actor3
	 */
	private String actor3;

	// --------- Constructors ---------//
	/**
	 * Default Constructor
	 */
	public Movie() {
		super();
	}

	/**
	 * Parameterized Constructor
	 * 
	 * @param year     an integer from 1990 through 1999.
	 * @param title    String
	 * @param duration an integer from 30 through 300 minutes.
	 * @param genre    musical, comedy, animation, adventure, drama, crime,
	 *                 biography, horror, action, documentary, fantasy, mystery,
	 *                 sci-fi, family, romance, thriller, western
	 * @param rating   valid ratings are PG, Unrated, G, R, PG-13, NC-17
	 * @param score    a positive double value less than or equal 10.
	 * @param director String
	 * @param actor1   String
	 * @param actor2   String
	 * @param actor3   String
	 */
	public Movie(int year, String title, int duration, String genre, String rating, double score, String director,
			String actor1, String actor2, String actor3) {
		super();
		this.year = year;
		this.title = title;
		this.duration = duration;
		this.genre = genre;
		this.rating = rating;
		this.score = score;
		this.director = director;
		this.actor1 = actor1;
		this.actor2 = actor2;
		this.actor3 = actor3;
	}

	/**
	 * Copy Constructor
	 * 
	 * @param anotherMovie Movie object to copy
	 */
	public Movie(Movie anotherMovie) {
		super();
		this.year = anotherMovie.year;
		this.title = anotherMovie.title;
		this.duration = anotherMovie.duration;
		this.genre = anotherMovie.genre;
		this.rating = anotherMovie.rating;
		this.score = anotherMovie.score;
		this.director = anotherMovie.director;
		this.actor1 = anotherMovie.actor1;
		this.actor2 = anotherMovie.actor2;
		this.actor3 = anotherMovie.actor3;
	}

	// --------- Accessor and Mutator ---------//
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/**
	 * @return the score
	 */
	public double getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(double score) {
		this.score = score;
	}

	/**
	 * @return the director
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * @param director the director to set
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * @return the actor1
	 */
	public String getActor1() {
		return actor1;
	}

	/**
	 * @param actor1 the actor1 to set
	 */
	public void setActor1(String actor1) {
		this.actor1 = actor1;
	}

	/**
	 * @return the actor2
	 */
	public String getActor2() {
		return actor2;
	}

	/**
	 * @param actor2 the actor2 to set
	 */
	public void setActor2(String actor2) {
		this.actor2 = actor2;
	}

	/**
	 * @return the actor3
	 */
	public String getActor3() {
		return actor3;
	}

	/**
	 * @param actor3 the actor3 to set
	 */
	public void setActor3(String actor3) {
		this.actor3 = actor3;
	}

	/**
	 * Overriding method toString()
	 */
	@Override
	public String toString() {
		return "Movie [year=" + year + ", title=" + title + ", duration=" + duration + ", genre=" + genre + ", rating="
				+ rating + ", score=" + score + ", director=" + director + ", actor1=" + actor1 + ", actor2=" + actor2
				+ ", actor3=" + actor3 + "]";
	}

	/**
	 * Overriding method equals()
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (this == null || obj == null || this.getClass() != obj.getClass())
			return false;
		else {
			Movie anotherMovie = (Movie) obj;
			/*
			 * new Double(d1).compareTo(new Double(d2)) the value 0 if d1 is numerically
			 * equal to d2; a value less than 0 if d1 is numerically less than d2; and a
			 * value greater than 0 if d1 is numerically greater than d2.
			 */
			boolean b = this.year == anotherMovie.year && this.title.equalsIgnoreCase(anotherMovie.title)
					&& this.duration == anotherMovie.duration && this.genre.equalsIgnoreCase(anotherMovie.genre)
					&& this.rating.equalsIgnoreCase(anotherMovie.rating)
					&& (Double.compare(score, anotherMovie.score) == 0)
					&& this.director.equalsIgnoreCase(anotherMovie.director)
					&& this.actor1.equalsIgnoreCase(anotherMovie.actor1)
					&& this.actor2.equalsIgnoreCase(anotherMovie.actor2)
					&& this.actor3.equalsIgnoreCase(anotherMovie.actor3);
			return b;
		}
	}

}
