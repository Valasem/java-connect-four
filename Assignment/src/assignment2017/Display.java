package assignment2017;

// Imports variables from Connect4GameState class //
import static assignment2017.Connect4GameState.NUM_COLS;
import static assignment2017.Connect4GameState.NUM_ROWS;
import static assignment2017.Connect4GameState.EMPTY;
import static assignment2017.Connect4GameState.RED;
import static assignment2017.Connect4GameState.YELLOW;

public class Display implements Connect4Displayable {

    public static String[][] cells;
    private MyGameState gameStatus;
    public final static int COLS = NUM_COLS + 2;
    public final static int ROWS = NUM_ROWS + 2;

    /**
     * Making a Display object
     * @param status the status of the game
     */
    
    public Display(MyGameState status) {
        this.gameStatus = status;
        cells = new String[COLS][ROWS + 1];
    }

    /**
     * Painting the cells depending on inputs and printing the shape of the game board
     */
    
    public void displayBoard() {
        for (int z = 0; z < COLS - 1; z++) {
            cells[z + 1][0] = " " + String.valueOf(z) + " ";
          }
        
        for (int a = 0; a < COLS - 1; a++) {
          cells[a][1] = "---";
        }

        for (int b = 2; b < ROWS; b++) {
          cells[0][b] = "|";
          cells[COLS - 1][b] = "คำ";
        }
        
        cells[0][0] = " ";
        cells[0][1] = " ";
        cells[COLS - 1][0] = " ";
        cells[COLS - 1][1] = " ";

        for (int x = ROWS - 3; x >= 0; x--) {
            for (int y = 0; y < COLS - 2; y++) {
            	if (this.gameStatus.getCounterAt(y, x) == RED) {
                    cells[y + 1][x + 2] = " R ";              
                } else if (this.gameStatus.getCounterAt(y, x) == YELLOW) {
                    cells[y + 1][x + 2] = " Y ";
                } else if (this.gameStatus.getCounterAt(y, x) == EMPTY) {
                    cells[y + 1][x + 2] = "   ";
                }
           
            }
        }

        for (int x = ROWS - 1; x >= 0; x--) {
            for (int y = 0; y < COLS; y++) {
                System.out.print(cells[y][x]);
            }
            System.out.println(" ");
        }
    }
}