import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements ActionListener, KeyListener {

	boolean dontRun = true;

	Snake game;
	Model model;
	Timer timer;
	TimerTask task;

	ArrayList<SnakeBox> sd = new ArrayList<>();

	public Controller(Snake game, Model model) {
		this.game = game;
		this.model = model;
		game.addKeyListener(this);

		sd.add(new SnakeBox(10, 2, "First"));
		sd.add(new SnakeBox(10, 1, ""));
		sd.add(new SnakeBox(10, 0, ""));

		game.point.setText(String.valueOf(model.point));
		run();

	}

	void run() {
		timer = new Timer();
		task = new TimerTask() {
			@Override
			public void run() {

				try {
					for (int i = 0; i < sd.size(); i++) {
						if (sd.get(i).pos.equals("First")) {
							if (model.checkCollison(sd.get(i)))
								System.out.println("Failed");
							game.button[sd.get(i).y2][sd.get(i).x2].setIcon(null);
							game.button[sd.get(i).y][sd.get(i).x].setIcon(game.Head);
							continue;
						}
						game.button[sd.get(i).y2][sd.get(i).x2].setIcon(null);
						game.button[sd.get(i).y][sd.get(i).x].setIcon(game.Tails);
					}
				} catch (Exception e) {
					failed();
				}

				model.tick(sd);

				if (model.checkEaten(sd) == false) {
					game.point.setText(String.valueOf(model.point));
					sd.add(new SnakeBox(sd.get(sd.size() - 1).x2, sd.get(sd.size() - 1).y2, ""));
				}
				if (model.eaten == false) {
					Point foodLocation = model.giveFood(sd);
					game.button[foodLocation.y][foodLocation.x].setIcon(game.Tails);
					model.eaten = true;
				}
			}
		};

		timer.schedule(task, 0, 400);
	}

	void failed() {
		dontRun = true;
		for (int i = 0; i < game.button.length; i++) {
			for (int j = 0; j < game.button[i].length; j++) {
				game.button[i][j].setIcon(null);
			}
		}

		game.label.setVisible(true);

		while (dontRun) {

		}

		game.label.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		switch (keyCode) {
		case KeyEvent.VK_UP:
			model.prevDir = model.direction;
			model.direction = -2;
			break;
		case KeyEvent.VK_DOWN:
			model.prevDir = model.direction;
			model.direction = 0;
			break;
		case KeyEvent.VK_LEFT:
			model.prevDir = model.direction;
			model.direction = -1;
			break;
		case KeyEvent.VK_RIGHT:
			model.prevDir = model.direction;
			model.direction = 1;
			break;
		case KeyEvent.VK_TAB:
			dontRun = false;
			game.label.setVisible(false);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
