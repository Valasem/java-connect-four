package assignment2017;

public class MyGameState extends Connect4GameState {

    public GameBoard board;
    public int firstTurn;
    public int counter;
    public boolean win = false;
    private MyGameState copy;
    public int[] columns;
    public static String[][] console;

    /**
     * Starting the game, resetting every cells empty, and making the player use red colour
     */
    @Override
    public void startGame() {
        board.resetCells();
        firstTurn = RED;
    }

    /**
     * Drops a counter into a slot on the board.  The colour of the counter depends 
     * on whose turn it is
     * @param col the column in which to drop the counter, in the range 0-6
     * @throws ColumnFullException if the column denoted by col is full (i.e. the move cannot be played)
     * @throws IllegalColumnException if col is not in the range 0-6 (i.e. an invalid column)
     */
    @Override
    public void move(int col) throws ColumnFullException, IllegalColumnException {

        if (columns[col] > NUM_ROWS - 1) {
            throw new ColumnFullException(col);
        }

        if (col > NUM_COLS - 1 || col < 0) {
            throw new IllegalColumnException(col);
        }

        board.paintCells(col, columns[col], firstTurn);
        columns[col]++;

        if (this.getWinner() != EMPTY) {
            win = true;
        }

        if (firstTurn == YELLOW) {
            firstTurn = RED;
        } else if (firstTurn == RED) {
            firstTurn = YELLOW;
        }
    }

    /**
     * Showing whose turn at the moment
     * @return the value of current turn's player
     */
    @Override
    public int whoseTurn() {
        return firstTurn;
    }

    /**
     * Returns a constant denoting the status of the slot at the position denoted by the 
     * col and row parameters
     * @param col the column of the position being queried (in the range 0-6)
     * @param row the row of the position being queried (in the range 0-5)
     * @return the EMPTY constant if the slot is empty, the RED constant if the slot is filled by a red counter,
     * the YELLOW constant if is yellow
     * @throws IllegalColumnException if col is not in the range 0-6 (i.e. an invalid column)
     * @throws IllegalRowException if col is not in the range 0-5 (i.e. an invalid row)
     */
    @Override
    public int getCounterAt(int col, int row) throws IllegalColumnException, IllegalRowException {

        if (row > NUM_ROWS || row < 0) {
            throw new IllegalRowException(row);
        }
        if (col > NUM_COLS - 1 || col < 0) {
            throw new IllegalColumnException(col);
        }

        return board.reportCells(col, row);
    }

    /**
     * Returns whether the board is full and the game has ended in a tie
     * @return true if the board is full, else false
     */
    @Override
    public boolean isBoardFull() {

        for (int x = 0; x < NUM_COLS; x++) {
            for (int y = 0; y < NUM_ROWS; y++) {
                if (board.reportCells(x, y) == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns whether the column denoted by the col parameter is full or not
     * @param col the column being queried (in the range 0-6)
     * @return true if the column denoted by col is full of counters, else false
     * @throws IllegalColumnException if col is not in the range 0-6 (i.e. an invalid column)
     */
    @Override
    public boolean isColumnFull(int col) throws IllegalColumnException {
        if (columns[col] >= NUM_ROWS) {
            return true;
        }

        if (col > NUM_COLS - 1 || col < 0) {
            throw new IllegalColumnException(col);
        } else return false;
    }

    /**
     * Showing if the game has finished
     * @return false if there is no winner yet or the board is not full yet. And vice versa. 
     */
    @Override
    public boolean gameOver() {
        String gameWinner = "NONE!";
        if (getWinner() != EMPTY || isBoardFull() == true) {

            if (getWinner() == RED) {
                gameWinner = "RED ";
            } else if (getWinner() == YELLOW) {
                gameWinner = "YELLOW ";
            }

            System.out.println(gameWinner + "wins");
            return true;
        } else return false;
    }

    /**
     * Copying and passing the current Connect4GameState object
     * @return a new Connect4GameState instance
     */
    @Override
    public Connect4GameState copy() {
        copy = new MyGameState();

        System.arraycopy(this.board.report(), 0, this.board.status,
            0, this.board.status.length);
        copy.columns = this.columns;
        copy.counter = this.counter;
        copy.firstTurn = this.firstTurn;
        copy.win = this.win;

        return copy;
    }

    /**
     * MyGameState object
     */
    public MyGameState() {
        board = new GameBoard(NUM_COLS, NUM_ROWS);
        columns = new int[NUM_COLS + 10];
    }

    /**
     * Showing whose turn just before was
     * @return YELLOW if it is RED's turn, and vice versa 
     */
    public int turnBefore() {
        if (firstTurn == RED) {
            return YELLOW;
        } else if (firstTurn == YELLOW) {
            return RED;
        } else return EMPTY;
    }

    /**
     * Checking if the player has won (i.e. has made 4 same colours in a row)
     * @param a column no.
     * @param b row no.
     * @return true if a player managed to fill 4 same colour cells in a row
     */
    public boolean didWin(int a, int b) {

        //Horizontal lines?// 
        counter = 0;
        lookLeft(a, b);
        if (counter >= 3) {
            return true;
        }

        counter = 0;
        lookRight(a, b);
        if (counter >= 3) {
            return true;
        }

        counter = 0;
        lookAbove(a, b);
        if (counter >= 3) {
            return true;
        }

        counter = 0;
        lookBelow(a, b);
        if (counter >= 3) {
            return true;
        }

        //Diagonal lines?//
        counter = 0;
        lookTopLeft(a, b);
        if (counter >= 3) {
            return true;
        }

        counter = 0;
        lookTopRight(a, b);
        if (counter >= 3) {
            return true;
        }

        counter = 0;
        lookBottomLeft(a, b);
        if (counter >= 3) {
            return true;
        }

        counter = 0;
        lookBottomRight(a, b);
        if (counter >= 3) {
            return true;
        }

        return false;
    }

    /**
     * Figuring out colours of cells that are located left of a specific cell
     * @param a column no.
     * @param b row no.
     * @return the counter of the number of same coloured blocks
     */
    public int lookLeft(int a, int b) {

        if (a > 0 && getCounterAt(a - 1, b) == whoseTurn() &&
            getCounterAt(a, b) == whoseTurn()) {

            lookLeft(a - 1, b);
            counter++;
        }
        return counter;
    }

    /**
     * Figuring out colours of cells that are located right of a specific cell
     * @param a column no.
     * @param b row no.
     * @return the counter of the number of same coloured blocks
     */
    public int lookRight(int a, int b) {

        if (a < NUM_COLS - 1 && getCounterAt(a + 1, b) == whoseTurn() &&
            getCounterAt(a, b) == whoseTurn()) {

            lookRight(a + 1, b);
            counter++;
        }
        return counter;
    }

    /**
     * Figuring out colours of cells that are located above of a specific cell
     * @param a column no.
     * @param b row no.
     * @return the counter of the number of same coloured blocks
     */
    public int lookAbove(int a, int b) {
        if (b < NUM_ROWS - 1 && getCounterAt(a, b + 1) == whoseTurn() &&
            getCounterAt(a, b) == whoseTurn()) {

            lookAbove(a, b + 1);
            counter++;

        }
        return counter;
    }

    /**
     * Figuring out colours of cells that are located below of a specific cell
     * @param a column no.
     * @param b row no.
     * @return the counter of the number of same coloured blocks
     */
    public int lookBelow(int a, int b) {
        if (b > 0 && getCounterAt(a, b - 1) == whoseTurn() &&
            getCounterAt(a, b) == whoseTurn()) {

            lookBelow(a, b - 1);
            counter++;
        }
        return counter;
    }

    /**
     * Figuring out colours of cells that are located top left of a specific cell
     * @param a column no.
     * @param b row no.
     * @return the counter of the number of same coloured blocks
     */
    public int lookTopLeft(int a, int b) {

        if (b < NUM_ROWS - 1 && a > 0 && getCounterAt(a - 1, b + 1) == whoseTurn() &&
            getCounterAt(a, b) == whoseTurn()) {

            lookTopLeft(a - 1, b + 1);
            counter++;
        }
        return counter;
    }

    /**
     * Figuring out colours of cells that are located top right of a specific cell
     * @param a column no.
     * @param b row no.
     * @return the counter of the number of same coloured blocks
     */
    public int lookTopRight(int a, int b) {
        if (b < NUM_ROWS - 1 && a < NUM_COLS - 1 && getCounterAt(a + 1, b + 1) == whoseTurn() &&
            getCounterAt(a, b) == whoseTurn()) {

            lookTopRight(a + 1, b + 1);
            counter++;
        }
        return counter;
    }

    /**
     * Figuring out colours of cells that are located bottom left of a specific cell
     * @param a column no.
     * @param b row no.
     * @return the counter of the number of same coloured blocks
     */
    public int lookBottomLeft(int a, int b) {
        if (b > 0 && a > 0 && getCounterAt(a - 1, b - 1) == whoseTurn() &&
            getCounterAt(a, b) == whoseTurn()) {

            lookBottomLeft(a - 1, b - 1);
            counter++;
        }
        return counter;
    }

    /**
     * Figuring out colours of cells that are located bottom right of a specific cell
     * @param a column no.
     * @param b row no.
     * @return the counter of the number of same coloured blocks
     */
    public int lookBottomRight(int a, int b) {
        if (b > 0 && a < NUM_COLS - 1 && getCounterAt(a + 1, b - 1) == whoseTurn() &&
            getCounterAt(a, b) == whoseTurn()) {

            lookBottomRight(a + 1, b - 1);
            counter++;
        }
        return counter;
    }

    /**
     * Figuring out if the game has finished and determining the winner
     * @return the winning player's colour or otherwise, if there are no winning players yet, returns EMPTY
     */
    @Override
    public int getWinner() {
        for (int x = 0; x < NUM_ROWS; x++) {
            for (int y = 0; y < NUM_COLS; y++) {
                if (didWin(y, x)) {
                    return whoseTurn();
                }
            }
        }
        return EMPTY;
    }
}