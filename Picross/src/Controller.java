import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * This class implements action listener. it handles all the action performed in the game class.
 * @author Caleb Orhomre
 * @version 1.1
 * Number 41040764
 */
public class Controller implements ActionListener {

	Game GameView;
	GameModel Model;
	String text;
	String Lang = "English";
	String line;
	int count;
	ControllableTimer timeRun;

	/**
	 * Public constructor for the Controller class, this constructor just initializes the Game object called pa with the object passed in.
	 * @param game This varible will be used to initialize the Game class object. 
	 */
	public Controller(Game game, GameModel model) {
		GameView = game;
		Model = model;
		timeRun = new ControllableTimer(game);
		init(0);
		timeRun.start();
	}

	/**
	 * This method is used to set the action listeners for the game components. 
	 * @param num Token to decide if an action listener should be added to a components. 
	 */
	public void init(int num) {

		switch(num) {
		case 0:
			GameView.goodTile.addActionListener(this);
			GameView.wrongTile.addActionListener(this);
			GameView.markTile.addActionListener(this);

			GameView.ok_b.addActionListener(this);

			// Creeate Mneu
			GameView.save.addActionListener(this);
			GameView.new_m.addActionListener(this);
			GameView.solution.addActionListener(this);
			GameView.colorPick.addActionListener(this);
			GameView.new_dim.addActionListener(this);

			GameView.back_b.addActionListener(this);
			GameView.reset_b.addActionListener(this);

			GameView.cBoxMark.addActionListener(this);

			// Create Menu Window
			GameView.GameMode_cb.addActionListener(this);
			GameView.Language_cb.addActionListener(this);
			GameView.Load_cb.addActionListener(this);
			GameView.Start_b.addActionListener(this);
			GameView.Help_b.addActionListener(this);

			for (int row = 0; row < GameView.buttons.length; row++) 
				for (int col = 0; col < GameView.buttons[row].length; col++) 
					GameView.buttons[row][col].addActionListener(this);

			break;
		case 1:
//			pa.save.addActionListener(this);
//			pa.new_m.addActionListener(this);
//			pa.solution.addActionListener(this);
//			pa.colorPick.addActionListener(this);
//			pa.new_dim.addActionListener(this);

			GameView.back_b.addActionListener(this);
			GameView.reset_b.addActionListener(this);

			GameView.cBoxMark.addActionListener(this);

			for (int row = 0; row < GameView.buttons.length; row++) 
				for (int col = 0; col < GameView.buttons[row].length; col++) 
					GameView.buttons[row][col].addActionListener(this);
			break;
		case 2:
			GameView.GameMode_cb.addActionListener(this);
			GameView.Language_cb.addActionListener(this);
			GameView.Load_cb.addActionListener(this);
			GameView.Start_b.addActionListener(this);
			GameView.Help_b.addActionListener(this);
			break;
		}
	}

	/**
	 * This method deals with the action performed in the gui, i.e button pressed.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object E = e.getSource();

		if(E == GameView.Help_b) {
			if(GameView.Help_Label.isVisible() == false) {
				GameView.Help_Label.setVisible(true);
			} else {
				GameView.Help_Label.setVisible(false);
			}
		} else if(E == GameView.Start_b) {
			GameView.gameWindow(3);
			timeRun.setStatus(1);
			
		} else if(E == GameView.GameMode_cb) {
			// System.out.println(pa.GameMode_cb.getSelectedItem());
		} else if(E == GameView.Language_cb) {
			Lang = GameView.Language_cb.getSelectedItem().toString();
			GameView.SetLangauge(GameView.Language_cb.getSelectedItem().toString());
		} else if(E == GameView.Load_cb) {			

			String dim = Model.load(GameView.fileName(0));
			if(dim == null)
				return;
			String[] arrOfStr = dim.split("x", 2);
			GameView.dimRow = Integer.parseInt(arrOfStr[0]);
			GameView.dimCol = Integer.parseInt(arrOfStr[1]);

			Model.dimRow = GameView.dimRow;
			Model.dimCol = GameView.dimCol;

			Model.solveColNum();
			Model.solveRowNum();
			Model.printAnswer();
			Model.countPass();

			GameView.gameWindow(1);
			init(1);

			for(int i = 0; i < Model.matrix.length; i++) {
				for(int j = 0; j < Model.matrix[i].length; j++) {
					Color color = null;
					if(Model.matrix[i][j] == 10) {
						color = GameView.mark;
						Model.saveArray[i][j] = 10;
					} else if(Model.matrix[i][j] == 11) {
						color = GameView.wrong;
						Model.saveArray[i][j] = 11;
					} else if(Model.matrix[i][j] == 12) {
						color = GameView.good;
						Model.totalPass--;
						Model.saveArray[i][j] = 12;
					} else if(Model.matrix[i][j] == 13) {
						color = GameView.wrong;
						Model.saveArray[i][j] = 13;
					} else
						continue;

					GameView.adjustButton(color, GameView.buttons[i][j]);
				}
			}
		} else if(E == GameView.back_b) {
			GameView.gameWindow(2);
			init(2);
			GameView.SetLangauge(Lang);

		} else if(E == GameView.reset_b) {
			GameView.gameWindow(1);
			init(1);
			timeRun.setStatus(3);
		} else if(E == GameView.ok_b) {
			GameView.gameWindow(1);
			init(1);
			GameView.setSize();

			Model.dimRow = GameView.dimRow;
			Model.dimCol = GameView.dimCol;

			Model.generateBinary();
		} else if(E == GameView.solution) {
			solution();
		} else if(E == GameView.new_m) {
			Model.dimRow = GameView.dimRow;
			Model.dimCol = GameView.dimCol;

			Model.generateBinary();
			GameView.gameWindow(1);

		} else if(E == GameView.colorPick) {
			GameView.setColor();
		} else if(E == GameView.save) { 
			String dim =  Model.binaryArray.length + "x" + Model.binaryArray[0].length;
			Model.save(GameView.fileName(1), dim);
		} else if(E == GameView.cBoxMark) {

		} else if(E == GameView.new_dim) {

			if(GameView.setDim() == 1) {
				Model.dimRow = GameView.dimRow;
				Model.dimCol = GameView.dimCol;

				Model.generateBinary();
				GameView.gameWindow(1);
			}
		} else if(E == GameView.goodTile) {

			GameView.tiles("Good");
		} else if(E == GameView.wrongTile) {

			GameView.tiles("Wrong");
		} else if(E == GameView.markTile) {

			GameView.tiles("Mark");
		}else {
			JButton selectedBtn = (JButton) E;
			for (int row = 0; row < GameView.buttons.length; row++) {
				for (int col = 0; col < GameView.buttons[row].length; col++) {
					if (GameView.buttons[row][col] == selectedBtn) {
						checkSelection(row, col);
					}
				}
			}
		}
	}

	/**
	 * This method is used to display the solution of the game.
	 */
	public void solution() {
		for (int row = 0; row < GameView.buttons.length; row++) 
			for (int col = 0; col < GameView.buttons[row].length; col++) 
				if(Model.getArray(row, col) == 1)
					GameView.adjustButton(GameView.good, GameView.buttons[row][col]);
				else 
					GameView.adjustButton(GameView.mark, GameView.buttons[row][col]);	
	}

	/**
	 * This method is used display the finished screen of the game.
	 * @param test This variable is used to determine if the winner or looser screen should be displayed.
	 */
	public void over(boolean test) {
		try {
			Thread.sleep(1000);
			GameView.over(test);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	/**
	 * This method is used to check the selection of the user.
	 * @param row
	 * @param col
	 */
	public void checkSelection(int row, int col) {

		if(GameView.cBoxMark.isSelected()) {

			if(Model.getArray(row, col) == 0) {
				GameView.adjustButton(GameView.mark, GameView.buttons[row][col]);
				GameView.updateLabel("Correct");
				GameView.updateScore(1);
				Model.saveArray[row][col] = 10;

			} else {
				GameView.adjustButton(GameView.wrong, GameView.buttons[row][col]);
				GameView.updateScore(0);
				Model.totalPass --;
				GameView.updateLabel("Wrong.");

				Model.saveArray[row][col] = 11;
				if(Model.totalPass == 0) {
					over(false);
				}
			}
		} else {

			if(Model.getArray(row, col) == 1) {
				GameView.adjustButton(GameView.good, GameView.buttons[row][col]);

				GameView.updateScore(1);

				GameView.updateLabel("Correct.");

				Model.totalPass --;

				Model.saveArray[row][col] = 12;

				if(Model.totalPass == 0) {
					over(true);
				}

			} else {
				GameView.adjustButton(GameView.wrong, GameView.buttons[row][col]);
				GameView.updateScore(0);

				GameView.updateLabel("Wrong.");

				Model.saveArray[row][col] = 13;
			}
		}
	}
}
