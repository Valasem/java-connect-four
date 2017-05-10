package assignment2017;

public class PlayConnect4{

	public static void main (String args[]) {
		MyGameState gameState = new MyGameState();
		Connect4Player red = new KeyboardPlayer();
		Connect4Player yellow = new RandomPlayer();
		Display display = new Display(gameState);
		
		Connect4 game = new Connect4(gameState, red, yellow, display);
		game.play();
	}
}
