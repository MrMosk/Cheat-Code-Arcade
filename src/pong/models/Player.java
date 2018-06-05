package pong.models;

import java.io.Serializable;

public class Player implements Serializable {

	private String name;
	private int p1Score = 0;
	private int p2Score = 0;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getP1Score() {
		return p1Score;
	}

	public void setP1Score(int p1Score) {
		this.p1Score = p1Score;
	}

	public int getP2Score() {
		return p2Score;
	}

	public void setP2Score(int p2Score) {
		this.p2Score = p2Score;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
