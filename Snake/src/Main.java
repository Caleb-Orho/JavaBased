
public class Main {

	public static void main(String[] args) {
		Model model = new Model();
		Snake game = new Snake(); 

		new Controller(game, model);
		
		game.setLocationRelativeTo(null);
		game.setResizable(false);
		game.setVisible(true);
	}

}
