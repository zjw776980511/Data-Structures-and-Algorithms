/**
 * The BoardGame class implements all the support methods to run the game. 
 * @author roykim
 * @date October 17, 2018
 * Student Number: 250 967 821
 */
public class BoardGame {

	/**
	 * These instance variables are used later in the code so they are declared here.
	 * @gameBoard The gameBoard is a 2d array that stores characters this is the board.
	 * @boards_size This is the size of the board.
	 * @empty_positions This is the number of empty positions that must remain on the board.
	 * @max_levels This is the mex level of the computer.
	 */
	private char[][] gameBoard;
	private int boards_size;
	private int empty_positions;
	private int max_levels;

	/** 
	 * Constructor of the class BoardGame, It creates the board and fills all the positions with empty tiles.
	 * @param boards_size The size of the board.
	 * @param empty_positions The number of empty positions that must remain on the board.
	 * @param max_levels The max level of the computer.
	 */
	public BoardGame(int boards_size, int empty_positions, int max_levels) {
		
		this.boards_size = boards_size;
		this.empty_positions = empty_positions;
		this.max_levels = max_levels;

		// Makes the game board with the specified size.
		gameBoard = new char[boards_size][boards_size];

		// Goes to every index of the game board and makes them all empty tiles to start off with.
		for (int row = 0; row < boards_size; row++) {
			for (int col = 0; col < boards_size; col++) {
				gameBoard[row][col] = 'g';

			}
		}
	}

	/**
	 * Returns an empty HashDictionary with a size that is prime.
	 * @return dict The empty hash dictionary with the specified size.
	 */
	public HashDictionary makeDictionary() {
		HashDictionary dict = new HashDictionary(9887);
		return dict;
	}

	/**
	 * Checks whether the string representation of the game board is in the dictionary. If it is the method returns the corresponding score otherwise returns -1.
	 * @param dict The dictionary to search the string configuration of the game board.
	 * @return dict.getScore(gameBoardString) Will return the associated score if the string configuration of gameBoard exists in the dictionary other wise returns -1.
	 */
	public int isRepeatedConfig(HashDictionary dict) {
		return dict.getScore(gameBoardString());
	}

	/**
	 * Inserts the board games string and its score into the dictionary.
	 * @param dict The dictionary where the new Configuration object needs to be put into.
	 * @param score The score associated with the current game board.
	 */
	public void putConfig(HashDictionary dict, int score) {
		
		// Creates a new Configuration object with the string and the score.
		Configuration newItem = new Configuration(gameBoardString(), score);
		
		// Places the new Configuration object into the dictionary.
		dict.put(newItem);
	}

	/**
	 * This method stores the symbol in the correct tile, this method tracks the moves.
	 * @param row The row where the symbol needs to be placed.
	 * @param col The col where the symbol needs to be placed.
	 * @param symbol The symbol that needs to be assigned to the game board in a 2d array at the index [row][col].
	 */
	public void savePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	}

	/**
	 * Checks whether the position is empty or not.
	 * @param row The index of the row that needs to be checked in the 2d array.
	 * @param col The index of the col that needs to be checked in the 2d array.
	 * @return True if gameBoard[row][col] is == 'g' meaning that the index is empty or false if it is not empty.
	 */
	public boolean positionIsEmpty(int row, int col) {
		return gameBoard[row][col] == 'g';
	}

	/**
	 * Checks if the position is a tile occupied by the computer 'o'.
	 * @param row The index of the row that needs to be checked in the 2d array.
	 * @param col The index of the col that needs to be checked in the 2d array.
	 * @return True if gameBoard[row][col] is == 'o' meaning that the index is occupied by the computer otherwise return false.
	 */
	public boolean tileOfComputer(int row, int col) {
		return gameBoard[row][col] == 'o';
	}

	/**
	 * Checks if the position is a tile occupied by the human 'b'.
	 * @param row The index of the row that needs to be checked in the 2d array.
	 * @param col The index of the col that needs to be checked in the 2d array.
	 * @return True if gameBoard[row][col] is == 'b' meaning that the index is occupied by the human otherwise return false.
	 */
	public boolean tileOfHuman(int row, int col) {
		return gameBoard[row][col] == 'b';
	}

	/**
	 * Returns true if n(the size of the game board) adjacent tiles of type symbol in the same row, column, or diagonal.
	 * @param symbol The symbol to compare all other tiles to determine a win.
	 * @return True if n(the size of the game board) adjacent tiles of type symbol in the same row, column, or diagonal otherwise returns false.
	 */
	public boolean wins(char symbol) {

		// Declares variables to determine wins later in the method.
		boolean win = false;
		boolean leftwin = true;
		boolean rightwin = true;
		int count;
		
		// Checks if each column contains the same symbol.
		for (int col = 0; col < boards_size; col++) {
			
			// Initializes count to 0 and resets for every new column.
			count = 0;
			
			for (int row = 0; row < gameBoard[col].length; row++) {
				
				// If the tile is the same symbol then increment the count.
				if (gameBoard[col][row] == symbol) {
					count++;

					// When the count is the same as the board size then we know it is a win.
					if (count == boards_size) {
						
						// Set win to true.
						win = true;
						
						// break because nothing else needs to be checked if a win is determined.
						break;
					} 
					
					// If count is not the same as the boards size the continue to search the next column for a win.
					else {
						continue;
					}
				}

			}
		}

		// checks if each row contains the same symbol.
		for (int row = 0; row < boards_size; row++) {
			
			// Initializes count to 0 and resets for every new row.
			count = 0;
			
			for (int col = 0; col < gameBoard[row].length; col++) {
				
				// If the tile is the same symbol then increment the count.
				if (gameBoard[col][row] == symbol) {
					count++;

					// When the count is the same as the board size then we know it is a win.
					if (count == boards_size) {
						
						// Set win to true.
						win = true;
						
						// break because nothing else needs to be tested if it is a win.
						break;
					} 
					
					// If count is not the same value as board size then continue to search the next row for a win.
					else {
						continue;
					}
				}

			}
		}

		// Checks if the left diagonal contains the same symbol.
		for (int i = 0; i < boards_size; i++) {

			// If the symbol doesn't match going diagonally from the left. Then it is not a win.
			if (gameBoard[i][i] != symbol) {

				// Set leftwin to false because it is not a win.
				leftwin = false;
				
				// break because the diagonal is not a win so we don't need to check any other index's on the diagonal.
				break;
			} 
			
			// Otherwise continue to check each index on the diagonal.
			else {
				continue;
			}

		}
		// Checks if the right diagonal contains the same symbol.
		for (int i = 0; i < boards_size; i++) {
			
			// If the symbol doesn't match going from the right. The it is not a win.
			if (gameBoard[boards_size - i - 1][i] != symbol) {
				
				// Set rightwin to false because it is not a win.
				rightwin = false;
				
				// break because the diagonal is not a win so we dont't need to check any other index's on the diagonal.
				break;
			} 
			
			// Otherwise continue to check if each index on the diagonal.
			else {
				continue;
			}
		}

		// If either the leftwin or the rightwin is true that means it is a win.
		if (leftwin || rightwin) {
			
			// Set win equal to true.
			win = true;
		}

		// Return whether the game is a win or not.
		return win;

	}

	/**
	 * This method is to determine whether or not the game is a draw or not.
	 * @param symbol The symbol is the next players move.
	 * @param empty_positions The number of positions on the game board must remain empty.
	 * @return True is the game is a draw and false if is not a draw.
	 */
	public boolean isDraw(char symbol, int empty_positions) {

		// Declared variables for use later in the method.
		boolean draw = false;
		boolean empty = false;

		// Checks whether the computer or human has won. If one has won then it can't be a draw.
		if (wins('o')||wins('b')) {
			
			// Returns false because if there is a win then there cannot be a draw.
			return false;
		}
		
		// Checks to see if there are empty positions on the game board. If there is an empty space empty is set to true.
		for (int col = 0; col < boards_size; col++) {
			for (int row = 0; row < boards_size; row++) {
				if (gameBoard[col][row] == 'g') {
					empty = true;
					break;
				}
			}
		}

		// One of the draw cases where the number of empty positions is zero, game boards is empty, and there is no win. 
		//If these statements are true then it is draw because there are no more positions to move on the board and there is no win.
		if (empty_positions == 0 && !wins(symbol) && !empty) {
			draw = true;
		}
		
		// Declaration and Initialization of a variable named emptyLeft which tracks the number of empty variables on the game board.
		int emptyLeft = 0;

		// Counts the number of tiles that are empty in the game board.
		for (int i = 0; i < boards_size; i++) {
			for (int j = 0; j < boards_size; j++) {
				
				// If the position is empty ('g') then increment the number of empty tiles by one.
				if (positionIsEmpty(i, j)) {
					emptyLeft++;
				}
			}
		}

		// An integer called tracker that will determine if the game is a draw or not.
		int tracker = 0;
		
		// If the number of empty positions needed on the game board is the same as the current number of empty positions on the game board then check to see if it's draw.
		if (empty_positions == emptyLeft) {
			
			// If the number of needed empty positions is greater than zero than check if the game is a draw.
			if (empty_positions > 0) {
				
				// Go through every tile on the game board.
				for (int row = 0; row < boards_size; row++) {
					for (int col = 0; col < boards_size; col++) {

						// If the position is empty (equal to 'g') then run the following if statements to see if there is an available move around the tile.
						// Checks every possible tile around the empty position if it is out of bound the if statement will not run. If there is a possible move
						// then increment the tracker by one.
						if (positionIsEmpty(row, col)) {
							
							if (withinBoard(row - 1, col) && gameBoard[row - 1][col] == symbol) {
								tracker++;
							}
							
							if (withinBoard(row - 1, col - 1) && gameBoard[row - 1][col - 1] == symbol) {
								tracker++;
							}
							
							if (withinBoard(row + 1, col) && gameBoard[row + 1][col] == symbol) {
								tracker++;
							}
							
							if (withinBoard(row + 1, col - 1) && gameBoard[row + 1][col - 1] == symbol) {
								tracker++;
							}
							
							if (withinBoard(row, col + 1) && gameBoard[row][col + 1] == symbol) {
								tracker++;
							}
							
							if (withinBoard(row, col - 1) && gameBoard[row][col - 1] == symbol) {
								tracker++;
							}
							
							if (withinBoard(row + 1, col + 1) && gameBoard[row + 1][col + 1] == symbol) {
								tracker++;
							}
							
							if (withinBoard(row - 1, col + 1) && gameBoard[row - 1][col + 1] == symbol) {
								tracker++;
							}
						}
					}
				}
				
				// If there wasn't a possible move then the game is a draw.
				if (tracker == 0) {
					draw = true;
				}
			}
		}
		
		// Returns if the game is a draw or not.
		return draw;
	}

	/**
	 * This method is to evaluate the board and check for a win.
	 * @param symbol The symbol to check for a win.
	 * @param empty_positions The number of empty positions required on the game board.
	 * @return 0 If the human player has won. 
	 * @return 1 If the game is still undecided.
	 * @return 2 If the game has ended as a draw.
	 * @return 3 If the computer has won.
	 */
	public int evalBoard(char symbol, int empty_positions) {
		
		// Checks if the computer has won.
		if (wins('o')) {
			return 3;
		}
		
		// Checks if the human has won.
		if (wins('b')) {
			return 0;
		}
		
		// Checks if the game is a draw or not.
		if (isDraw(symbol, empty_positions)) {
			return 2;
		}

		// Returns 1 if the game is still undecided.
		return 1;

	}

	/**
	 * This method is to represent the game board as a String.
	 * @return boardConfig The board represented as a string.
	 */
	private String gameBoardString() {
		
		// Initializes and empty string.
		String boardConfig = "";

		// Goes through each tile and add the symbol of the tile to the string boardConfig.
		for (int col = 0; col < boards_size; col++) {
			for (int row = 0; row < boards_size; row++) {
				boardConfig += gameBoard[col][row];
			}
		}
		
		// Returns the board as a string.
		return boardConfig;

	}

	/**
	 * This method is to check whether or not the tile is within bounds of the game board.
	 * @param colNum The column index to check.
	 * @param rowNum The row index to check.
	 * @return True if the tile is within bound otherwise returns false.
	 */
	private boolean withinBoard(int colNum, int rowNum) {
		
		if ((colNum < 0) || (rowNum < 0)) {
			return false;
		}
		
		if ((colNum >= boards_size) || (rowNum >= boards_size)) {
			return false;
		}

		return true;
	}

}
