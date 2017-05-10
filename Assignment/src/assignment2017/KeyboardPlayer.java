package assignment2017;
import sheffield.EasyReader;

public class KeyboardPlayer extends Connect4Player {
    private EasyReader playerInput;

    /**
     * Making the player to put a token to a column 
     * @param gamestatus the status of the game
     */
    @Override
    public void makeMove(Connect4GameState gameStatus) {

        try {
            playerInput = new EasyReader();

            int col = playerInput.readInt("Please enter a column number, 0 to 6 followed by return.");

            gameStatus.move(col);
        } catch (IllegalColumnException i) {
            System.out.println(i + " please enter a vaild column number again");
        } catch (ColumnFullException i) {
            System.out.println(i);
        }
    }
}
