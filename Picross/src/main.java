
/**
 * This is the driver class that calls the game class to create the main GUI.
 * @author Caleb Orhomre
 * @version 1.1
 * Number 41040764
 */
public class main { 
    
	/**
	 * This is the main method that creates a game object, after creating the object, it sets the frame to visible and set resize-able to false.
	 * @param args Basic argument.
	 */
	public static void main(String[] args) {
	      
		GameModel model = new GameModel();
		Game game = new Game();
		new Controller(game, model);
			
		game.setLocationRelativeTo(null);
		game.setResizable(false);
		game.setVisible(true);
	}

}
