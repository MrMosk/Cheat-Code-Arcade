package pingModels;

public class Player {

	private String name = "[Default Name]";
	
	// Derived data, always starts at zero upon game start
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
	
}
