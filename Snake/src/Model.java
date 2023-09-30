import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Model {
	
	int point = 0;
	int location[][] = new int[12][12];
	int[] bodyx = new int[50];
	int[] bodyy = new int[50];

	int x = 0, y = 0;

	boolean eaten = false;

	// 0 = Down
	// 1 = Up
	int direction = 0;
	int prevDir = 100;

	public Model() {

	}

	void reset(ArrayList<SnakeBox> sd) {
		x = y = 0;
		direction = 0;
		prevDir = 100;
		eaten = false;
		bodyx = new int[50];
		bodyy = new int[50];
		location = new int[12][12];
		
		sd.clear();
		
		sd.add(new SnakeBox(10, 2, "First"));
		sd.add(new SnakeBox(10, 1, ""));
		sd.add(new SnakeBox(10, 0, ""));
	}
	
	
	boolean checkCollison(SnakeBox snakeBox) {
		if (snakeBox.x == 12)
			return true;
		else if (snakeBox.x == 0)
			return true;
		else if (snakeBox.y == 0)
			return true;
		else if (snakeBox.y == 12)
			return true;

		return false;
	}

	void tick(ArrayList<SnakeBox> sd) {
		if (direction == -2 && prevDir == 0)
			direction = prevDir;

		if (direction == 0 && prevDir == -2)
			direction = prevDir;

		if (direction == 1 && prevDir == -1)
			direction = prevDir;

		if (direction == -1 && prevDir == 1)
			direction = prevDir;

		for (int i = 0; i < sd.size(); i++) {
			sd.get(i).y2 = sd.get(i).y;
			sd.get(i).x2 = sd.get(i).x;

			switch (direction) {
			// Down
			case 0:
				if (i > 0) {
					sd.get(i).y = sd.get(i - 1).y2;
					sd.get(i).x = sd.get(i - 1).x2;
					break;
				}

				sd.get(i).y += 1;
				break;
			// Right
			case 1:
				if (i > 0) {
					sd.get(i).x = sd.get(i - 1).x2;
					sd.get(i).y = sd.get(i - 1).y2;
					break;
				}

				sd.get(i).x += 1;
				break;
			// Left
			case -1:
				if (i > 0) {
					sd.get(i).x = sd.get(i - 1).x2;
					sd.get(i).y = sd.get(i - 1).y2;
					break;
				}

				sd.get(i).x += -1;
				break;
			// Up
			case -2:
				if (i > 0) {
					sd.get(i).x = sd.get(i - 1).x2;
					sd.get(i).y = sd.get(i - 1).y2;
					break;
				}

				sd.get(i).y += -1;
				break;

			}
		}
	}

	boolean checkEaten(ArrayList<SnakeBox> sd) {

		if ((sd.get(2).x == x) && (sd.get(2).y == y)) {
			eaten = false;
			point++;
			return false;
		}

		return true;
	}

	Point giveFood(ArrayList<SnakeBox> sd) {
		Random random = new Random();

		boolean validCoordinates = false;

		while (!validCoordinates) {
			x = random.nextInt(11) + 1;
			y = random.nextInt(11) + 1;

			validCoordinates = true;

			for (SnakeBox box : sd) {
				int dx = Math.abs(box.x - x);
				int dy = Math.abs(box.y - y);

				if (dx < 2 || dy < 2) {
					validCoordinates = false;
					break;
				}
			}
		}

		return new Point(x, y);
	}
//	void tick(SnakeBox sd ) {
//		Scanner myObj = new Scanner(System.in);
//		System.out.println("1 or 0");
//		
//		if(myObj.nextInt() == 1) {
//
//		}
//			
//		for (int i = 0; i < bodyx.length; i++) {
//			switch (direction) {
//			case 0:
//				if (bodyy[i] != -1)
//					bodyy[i] += 1;
//				break;
//			case 1:
//				if (bodyx[i] != -1)
//					bodyx[i] += 1;
//				break;
//			case -1:
//				if (bodyx[i] != -1)
//					bodyx[i] += -1;
//				break;
//			case -2:
//				if (bodyy[i] != -1)
//					bodyy[i] += -1;
//				break;
//
//			}
//		}
//
//	}
}
