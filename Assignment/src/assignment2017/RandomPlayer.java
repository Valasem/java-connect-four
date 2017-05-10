package assignment2017;

public class RandomPlayer extends Connect4Player {

    /**
     * Making random moves created by the computer
     * @param gameStatus the status of the game
     */
    @Override
    public void makeMove(Connect4GameState gameStatus) {

        int computerInput = (int)(Math.random() * Connect4GameState.NUM_COLS);

        try {
            gameStatus.move(computerInput);
            System.out.println("Computer dropped counter in column " + computerInput);
        } catch (IllegalColumnException i) {
            System.out.println(i);
        } catch (ColumnFullException i) {
            System.out.println(i);
            makeMove(gameStatus);
        }
    }
}
