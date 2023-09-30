import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Snake extends JFrame {

	boolean failed = false;
	
	JPanel board, cardPanel, sidePanel;
	JLabel button[][] = new JLabel[12][12];
	JPanel gridPanel = new JPanel(new GridLayout(12, 12, 2, 2)); // Create a grid layout panel
	
	JLabel label = new JLabel("<html>Failed. <br>Press tab key to start again.</html>");
	
	JLabel point = new JLabel("0");
	
	ImageIcon originalIcon = new ImageIcon("C:\\Users\\Caleb Orhomre\\Desktop\\circle.png");
	Image originalImage = originalIcon.getImage();
	Image scaledImage = originalImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	ImageIcon Tails = new ImageIcon(scaledImage);
	
	ImageIcon head = new ImageIcon("C:\\Users\\Caleb Orhomre\\Desktop\\snake.png");
	Image originalImage1 = head.getImage();
	Image scaledImage1 = originalImage1.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	ImageIcon Head = new ImageIcon(scaledImage1);

	Image icon2 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Caleb Orhomre\\Desktop\\circle.png");

	public Snake() {
		setIconImage(icon2);
		setTitle("PICROSS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		
		create(); // Call the method to create grids
	}

	void create() {
		board = new JPanel(new BorderLayout());
		
		createSidePanel();
		createGrids();

		board.add(sidePanel, BorderLayout.LINE_END);
		board.add(cardPanel, BorderLayout.CENTER);

		add(board);
		
		label.setOpaque(true);
		label.setBackground(Color.red);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setVisible(false);
		
		point.setOpaque(true);
		point.setBackground(Color.white);
		point.setHorizontalAlignment(JLabel.CENTER);
		point.setVerticalAlignment(JLabel.CENTER);
		point.setPreferredSize(new Dimension(250, 100));
	}

	void createSidePanel() {
		sidePanel = new JPanel(new BorderLayout());
		sidePanel.setBackground(Color.black);
		
		sidePanel.add(point, BorderLayout.NORTH);
		sidePanel.add(label, BorderLayout.CENTER);
		
		sidePanel.setPreferredSize(new Dimension(250, 600));
	}

	void createGrids() {
		cardPanel = new JPanel(new BorderLayout());

		cardPanel.setBackground(Color.darkGray);

		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				button[i][j] = new JLabel();
				button[i][j].setOpaque(true);
				button[i][j].setBackground(Color.black);

				button[i][j].setHorizontalAlignment(JLabel.CENTER);
				button[i][j].setVerticalAlignment(JLabel.CENTER);

				gridPanel.add(button[i][j]);
			}
		}

		cardPanel.add(gridPanel, BorderLayout.CENTER);
	}
}
