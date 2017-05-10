package assignment2017;

public class GameBoard {
	
	public int[][] status;
		
	/**
	 * Making a game board
	 * @param colNumber the column's number
	 * @param rowNumber the row's number
	 */
	public GameBoard(int colNumber, int rowNumber){
		status = new int[MyGameState.NUM_COLS][MyGameState.NUM_ROWS]; 
	}
	
	public int[][] report(){
		return this.status;
	}
	
	/**
	 * Showing specific contents of each array cells (i.e. colours or EMPTY)
	 * @param a represents the columns
	 * @param b represents the rows
	 */ 
	public int reportCells(int a, int b){
		return status[a][b];
	}
	
	/**
	 * Inputing the colour data (i.e. RED, YELLOW, or EMPTY) depending on situations
	 * @param a represents the columns
	 * @param b represents the rows
	 * @param t represents the colour data
	 */ 
	public void paintCells(int a, int b, int t){
		this.status[a][b] = t;
	}

	/**
	 * Resetting all board cells 
	 * @return the status of all cells which is EMPTY
	 */ 
	public int[][] resetCells() {
		
		for (int i=0; i<MyGameState.NUM_ROWS; i++){
			for (int j=0; j<MyGameState.NUM_COLS; j++){
				paintCells(j, i, MyGameState.EMPTY);
			}
		}
		return status;
	}
}

