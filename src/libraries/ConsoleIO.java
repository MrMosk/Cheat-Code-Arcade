package libraries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleIO {

	// PRO TIP: Make sure to create a new BufferedReader in each method
	// where a BufferedReader is required. Do NOT close the reader as that will
	// cause
	// other issues. Also, catch ALL IOExceptions and NumberFormatExceptions.
	// Do not simply mark the method with a "throws" statement

	/**
	 * Generates a console-based menu using the Strings in options as the menu
	 * items. Reserves the number 0 for the "quit" option when withQuit is true.
	 * 
	 * @param options
	 *            - Strings representing the menu options
	 * @param withQuit
	 *            - adds option 0 for "quit" when true
	 * @return the int of the selection made by the user
	 */
	public static int promptForMenuSelection(String prompt, String[] options, boolean withQuit) {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println(prompt);

		boolean menuSelectCondition = true;
		String input;
		int num = 1;

		while (menuSelectCondition == true) {
			// Prints Menu & withQuit if true
			for (int i = 0; i < options.length; i++) {
				System.out.println(i + 1 + ") " + options[i]);
			}
			if (withQuit == true) {
				System.out.println("0) Quit");
			}

			boolean nullCheck = false;

			// User input checker
			try {
				input = in.readLine();

				nullCheck = nullInputChecker(nullCheck, input);

				if (nullCheck == true) {
					input = " ";
				} else {
					num = Integer.parseInt(input);
				}

				// Out of bounds check
				// If withQuit is true
				if (withQuit == true) {
					if (num < 0 || num > options.length) {
						System.out.println("ERROR: Number out of bounds");
					} else {
						menuSelectCondition = false;
					}
					// If withQuit is false
				} else if (withQuit == false) {
					if (num <= 0 || num > options.length) {
						System.out.println("ERROR: Number out of bounds");
					} else {
						menuSelectCondition = false;
					}
				}
			} catch (IOException ioE) {
				System.out.println("ERROR: IO exception");
			} catch (NumberFormatException eE) {
				System.out.println("ERROR: Invalid int");
			}
		}
		return num;
	}

	public static boolean nullInputChecker(boolean nullCheck, String input) {
		if (input == null) {
			System.out.println("ERROR: Cannot be null");
			nullCheck = true;
		}
		return nullCheck;
	}

	/**
	 * Generates a prompt that expects the user to enter one of two responses that
	 * will equate to a boolean value. The trueString represents the case
	 * insensitive response that will equate to true. The falseString acts
	 * similarly, but for a false boolean value. Example: Assume this method is
	 * called with a trueString argument of "yes" and a falseString argument of
	 * "no". If the enters "YES", the method returns true. If the user enters "no",
	 * the method returns false. All other inputs are considered invalid, the user
	 * will be informed, and the prompt will repeat.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param trueString
	 *            - the case insensitive value that will evaluate to true
	 * @param falseString
	 *            - the case insensitive value that will evaluate to false
	 * @return the boolean value
	 */
	public static boolean promptForBool(String prompt, String trueString, String falseString) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		boolean returnPromptBoolVal = true;
		boolean promptForBoolCondition = true;
		String input;

		do {
			System.out.println(prompt);
			try {
				input = in.readLine();
				if (input.equalsIgnoreCase(trueString)) {
					returnPromptBoolVal = true;
					break;
				} else if (input.equalsIgnoreCase(falseString)) {
					returnPromptBoolVal = false;
					break;
				} else {
					System.out.println("Error: Invalid input");
				}
			} catch (IOException ioe) {
				System.out.println("ERROR: IO exception");
			} catch (NullPointerException npe) {
				System.out.println("ERROR: Null Pointer Exception");
			}
		} while (promptForBoolCondition == true);

		return returnPromptBoolVal;
	}

	/**
	 * Generates a prompt that expects a numeric input representing a byte value.
	 * This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the byte value
	 */
	public static byte promptForByte(String prompt, byte min, byte max) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		byte byteVal = 0;
		String input = "DEFAULT";
		boolean promptForByteCondition = true;

		do {
			System.out.println(prompt);
			try {
				input = in.readLine();
				byteVal = Byte.parseByte(input);
				if (byteVal >= min && byteVal <= max) {
					promptForByteCondition = true;
					break;
				} else {
					System.out.println("ERROR: Out of Bounds");
				}
			} catch (IOException ioe) {
				System.out.println("ERROR: IO Exception");
			} catch (NumberFormatException nfe) {
				System.out.println("ERROR: Number Format Exception");
			}
		} while (promptForByteCondition == true);

		return byteVal;
	}

	/**
	 * Generates a prompt that expects a numeric input representing a short value.
	 * This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the short value
	 */
	public static short promptForShort(String prompt, short min, short max) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		short shortVal = 0;
		String input = "DEFAULT";
		boolean promptForShortCondition = true;

		do {
			System.out.println(prompt);
			try {
				input = in.readLine();
				shortVal = Short.parseShort(input);
				if (shortVal >= min && shortVal <= max) {
					promptForShortCondition = true;
					break;
				} else {
					System.out.println("ERROR: Out of Bounds");
				}
			} catch (IOException ioe) {
				System.out.println("ERROR: IO Exception");
			} catch (NumberFormatException nfe) {
				System.out.println("ERROR: Number Format Exception");
			}
		} while (promptForShortCondition == true);

		return shortVal;
	}

	/**
	 * Generates a prompt that expects a numeric input representing an int value.
	 * This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the int value
	 */
	public static int promptForInt(String prompt, int min, int max) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int intVal = 0;
		String inputStr = "DEFAULT";
		boolean promptForIntCondition = true;

		do {
			System.out.println(prompt);
			try {
				inputStr = in.readLine();
				intVal = Integer.parseInt(inputStr);
				if (intVal >= min && intVal <= max) {
					promptForIntCondition = false;
					break;
				} else {
					System.out.println("ERROR: Out of Bounds");
				}
			} catch (IOException ioe) {
				System.out.println("ERROR: IO Exception");
			} catch (NumberFormatException nfe) {
				System.out.println("ERROR: Invalid integer");
			}
		} while (promptForIntCondition = true);

		return intVal;
	}

	/**
	 * Generates a prompt that expects a numeric input representing a long value.
	 * This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the long value
	 */
	public static long promptForLong(String prompt, long min, long max) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		long longVal = 0;
		String inputStr = "DEFAULT";
		boolean promptForLongCondition = true;

		do {
			System.out.println(prompt);
			try {
				inputStr = in.readLine();
				longVal = Long.parseLong(inputStr);
				if (longVal >= min && longVal <= max) {
					promptForLongCondition = true;
					break;
				} else {
					System.out.println("ERROR: Long Out of Bounds");
				}
			} catch (IOException ioe) {
				System.out.println("ERROR: IO Exception");
			} catch (NumberFormatException nfe) {
				System.out.println("ERROR: Invalid Long");
			}
		} while (promptForLongCondition = true);

		return longVal;
	}

	/**
	 * Generates a prompt that expects a numeric input representing a float value.
	 * This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the float value
	 */
	public static float promptForFloat(String prompt, float min, float max) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		float floatVal = 0;
		String inputStr = "DEFAULT";
		boolean promptForFloatCondition = true;

		do {
			System.out.println(prompt);
			try {
				inputStr = in.readLine();
				floatVal = Float.parseFloat(inputStr);

				if (floatVal >= min && floatVal <= max) {
					promptForFloatCondition = true;
					break;
				} else {
					System.out.println("ERROR: Float Out of Bounds");
				}
			} catch (IOException ioe) {
				System.out.println("ERROR: IO Exception");
			} catch (NumberFormatException nfe) {
				System.out.println("ERROR: Invalid Float");
			}
		} while (promptForFloatCondition = true);

		return floatVal;
	}

	/**
	 * Generates a prompt that expects a numeric input representing a double value.
	 * This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the double value
	 */
	public static double promptForDouble(String prompt, double min, double max) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		double doubleVal = 0;
		boolean promptForDoubleCondition = true;
		String inputStr;

		do {
			System.out.println(prompt);
			try {
				inputStr = in.readLine();
				doubleVal = Double.parseDouble(inputStr);
				if (doubleVal >= min && doubleVal <= max) {
					promptForDoubleCondition = false;
					break;
				} else {
					System.out.println("ERROR: Out of bounds");
				}
			} catch (IOException io) {
				System.out.println("ERROR: IO exception");
			} catch (NumberFormatException nfe) {
				System.out.println("ERROR: Number format exception");
			}
		} while (promptForDoubleCondition = true);

		return doubleVal;
	}

	/**
	 * Generates a prompt that allows the user to enter any response and returns the
	 * String. When allowEmpty is true, empty responses are valid. When false,
	 * responses must contain at least one character (including whitespace).
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user.
	 * @param allowEmpty
	 *            - when true, makes empty responses valid
	 */
	public static String promptForInput(String prompt, boolean allowEmpty) {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		boolean promptForInputCondition = true;
		String returnStr = "[DEFAULT STRING]";

		do {
			System.out.println(prompt);

			try {
				returnStr = in.readLine();

				if (allowEmpty == false) {
					returnStr.trim();
					returnStr.replace("\t", "");
					if (returnStr == null) {
						System.out.println("ERROR: Cannot be null");
					} else if (returnStr.isEmpty()) {
						System.out.println("ERROR: Cannot be empty");
					} else if (returnStr.startsWith(" ")) {
						System.out.println("ERROR: Cannot start with space");
					} else {
						promptForInputCondition = false;
					}

				} else if (allowEmpty == true) {
					if (returnStr == null) {
						System.out.println("ERROR: Cannot be null");
					} else {
						promptForInputCondition = false;
					}
				}
			} catch (IOException e) {
				System.out.println("ERROR: IO exception");
				;
			}
		} while (promptForInputCondition == true);

		return returnStr;
	}

	/**
	 * Generates a prompt that expects a single character input representing a char
	 * value. This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the char value
	 */
	public static char promptForChar(String prompt, char min, char max) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char character = 0;
		String inputStr;
		boolean promptForCharCondition = true;

		// Main loop
		while (promptForCharCondition = true) { //
			System.out.println(prompt);
			try {
				inputStr = in.readLine();

				if (inputStr.isEmpty()) {
					System.out.println("ERROR: Must not be empty");
				} else if (inputStr.contains(" ")) {
					System.out.println("ERROR: Must have no spaces");
				} else if (inputStr.length() >= 2) {
					System.out.println("ERROR: Too many chars");
				} else if (inputStr.length() == 1) {
					character = inputStr.charAt(0);
					if (character < min || character > max) {
						System.out.println("ERROR: Char out of bounds");
					} else if (character >= min && character <= max) {
						promptForCharCondition = false;
						break;
					}
				}
			} catch (IOException io) {
				System.out.println("ERROR: IO exception");
			} catch (StringIndexOutOfBoundsException oob) {
				System.out.println("ERROR: Invalid character");
			}
		}
		return character;
	}

}
