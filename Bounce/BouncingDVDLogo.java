import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BouncingDVDLogo extends JPanel {
    private int logoX, logoY;
    private int logoWidth, logoHeight;
    private int xSpeed, ySpeed;
    
    final int FRAME_HEIGHT = 600;
    final int DECAY_RATE = 1;
    final int FRAME_WIDTH = 800;
    private BufferedImage dvdLogo;
    
    public BouncingDVDLogo() {
        // Initial position of the logo
    	
        logoY = 0;
        logoX = 50;

        // Dimensions of the logo
        logoWidth = 80;
        logoHeight = 40;

        // Initial speed of the logo
        xSpeed = 3;
        ySpeed = 20;

        // Create a timer to update the logo position
        Timer timer = new Timer(10, e -> updateLogoPosition());
        timer.start();
        
        try {
			dvdLogo = ImageIO.read(new File("C:\\Users\\Caleb Orhomre\\CST8116 Homework\\CST8116 Eclipse Workspace\\Game\\src\\dvd.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

    private void updateLogoPosition() {


        if (logoY > 0 && logoY < FRAME_HEIGHT) {
        	ySpeed += 1;
        }
		
    	
        // Check if the logo hits the bottom of the panel
        if (logoY >= FRAME_HEIGHT && ySpeed > 0) {
        	ySpeed = -ySpeed + DECAY_RATE;
        }
        
        // Check for bounce at the top wall
        if (logoY <= 0 && ySpeed < 0) {
            ySpeed = -ySpeed - DECAY_RATE;
        }
        
        logoY += ySpeed;

        // Check if the logo hits the top of the panel
        if (logoY <= 0) {
            logoY = 0;
            ySpeed = -ySpeed;
        } else if(logoY >= FRAME_HEIGHT) {
        	logoY = FRAME_HEIGHT;
        }
        	
        System.out.println(logoY);
        
        // Repaint the panel to show the updated logo position
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Set the color for the logo
        g.setColor(Color.RED);

        // Draw the logo rectangle at the current position
        g.drawImage(dvdLogo, logoX, logoY, logoWidth, logoHeight, this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600); // Set your preferred panel size here
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bouncing DVD Logo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new BouncingDVDLogo());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
