package appController;

import libraries.*;
import snekController.*;
import pingController.*;

public class Menu {
	
	Snek snek = new Snek();
	Ping ping = new Ping();

	/**
	 * Program's start & main menu
	 */
	public void mainMenu() {
		
		// Basic test menu
		consoleTestMenu();
	}

	/**
	 * Console Test Menu
	 */
	private void consoleTestMenu() {
		
		// ConsoleIO menu
		String prompt = "Main menu";
		String options[] = { "Snek", "Ping" };
		boolean withQuit = true;
		int menuChoice = ConsoleIO.promptForMenuSelection(prompt, options, withQuit);
		
		switch (menuChoice) {
		case 0:
			menuChoice = 0;
			System.out.println("Goodbye");
			break;
		case 1:
			menuChoice = 1;
			snek.sneckMenu();
			break;
		case 2:
			menuChoice = 2;
			ping.pingMenu();
			break;
		}
	}

}
