package pingController;

import libraries.ConsoleIO;

public class Ping {

	/**
	 * Ping's starting method & menu
	 */
	public void pingMenu() {
		
		// Test ConsoleIO Menu
		String prompt = "Ping Menu";
		String options[] = {"Start Game", "Leaderboard"};
		boolean withQuit = true;
		
		int menuChoice = ConsoleIO.promptForMenuSelection(prompt, options, withQuit); // Takes in user choice
		
		switch (menuChoice) {
		case 0:
			menuChoice = 0;
			break;
		case 1:
			menuChoice = 1;
			//startGame();
			break;
		case 2:
			menuChoice = 2;
			//leaderBoard();
			break;
		}
	}

}
