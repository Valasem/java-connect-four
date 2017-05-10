package assignment2017;

public class Connect4 {

    private MyGameState gamestate;
    private Connect4Player user;
    private Connect4Player random;
    private Display display;

    /**
     * Making a new Connect4 object
     * @param gamestate the current game status
     * @param user the human player object
     * @param random the computer player object
     * @param display the Display object
     */
    public Connect4(MyGameState gamestate, Connect4Player user,
        Connect4Player random, Display display) {
        this.gamestate = gamestate;
        this.user = user;
        this.random = random;
        this.display = display;
    }

    public void play() {

        this.gamestate.startGame();

        while (gamestate.gameOver() == false) {

            display.displayBoard();

            if (gamestate.win != false) {
                gamestate.firstTurn = gamestate.turnBefore();
            }

            user.makeMove(gamestate);

            if (gamestate.win == false) {
                random.makeMove(gamestate);
            }
            if (gamestate.win != false) {
                gamestate.firstTurn = gamestate.turnBefore();
            }
        }
        this.display.displayBoard();
    }
}