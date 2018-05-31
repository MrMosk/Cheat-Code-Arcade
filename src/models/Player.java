package models;

import java.io.Serializable;

public class Player implements Serializable {

	private String name;
	private int score = 0;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
