import java.awt.*;
import java.io.File;
import java.util.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;


/**
 * This class creates the GUI, it creates the menu panel and the game panel, this class also holds all the variables needed to create the gui.
 * @author Caleb Orhomre
 * @version 1.1
 * Number 41040764
 */
public class Game extends JFrame{

	private static final long serialVersionUID = 1L;

	JPanel panel, boardPanel, controlPanel, topPanel, leftPanel, point, output, time, back_p, PgoodTile, PwrongTile, PmarkTile;
	JButton Help_b, Start_b, back_b, reset_b, ok_b, goodTile, wrongTile, markTile;
	JLabel welcome_label, GameMode_label, Language_label, Load_label, actualPoint, actualTime, pointText, timeText;
	JTextArea outputLabel;
	JCheckBox cBoxMark = new JCheckBox("Mark");
	JComboBox<String> GameMode_cb, Language_cb, Load_cb;
	JTextArea Help_Label;
	JScrollPane scrollPane;
	JButton[][] buttons;
	JScrollPane scrollableTextArea;
	JMenuBar menuBar;
	JMenu game_mb, help_mb;    
	JMenuItem solution, new_m, colorPick, save, new_dim;

	Color good = Color.green;
	Color wrong = Color.red;
	Color mark = Color.yellow;

	String mode[]= {"Mode 1", "Mode 2"};
	String langauage[] = {"English", "Français"};
	String load[] = {"Load"};
	String point1 = "Point";
	String time1 = "time";

	Locale currentLocale;
	String SYSTEMMESSAGES = "resources/texts";
	private ResourceBundle texts;

	int dimCol = 5;
	int dimRow = 5;

	/**
	 * Public constructor for the game class, this constructor calls method to create the different frames in this gui panels.
	 */
	public Game() {

		setTitle("PICROSS");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);

		Image icon = Toolkit.getDefaultToolkit().getImage("piccross.png");
		setIconImage(icon);    

		splashScreen();

		goodTile = new JButton("Good Tile");
		wrongTile = new JButton("Wrong Tile");
		markTile = new JButton("Mark Tile");
		ok_b = new JButton();
		menuBar = new JMenuBar();
		game_mb = new JMenu("Game");
		help_mb = new JMenu("Help");
		new_m = new JMenuItem("New");
		solution = new JMenuItem("Solution");
		save = new JMenuItem("Save");
		solution = new JMenuItem("Solution");
		colorPick = new JMenuItem("Colors");
		new_dim = new JMenuItem("New Dimension");
		PgoodTile = new JPanel();
		PwrongTile = new JPanel();
		PmarkTile = new JPanel();

		create();

		setSize(900, 700);
		CreateMenuWindow();
	}

	/**
	 * This method basically creates each part of the game. 
	 */
	public void create() {
		createMenu();
		boardPanel();
		controlPanel();
		topPanel();
		leftPanel();
	}

	/**
	 * This method gets a new dimension from user.
	 * @return The dimension.
	 */
	public int setDim() {
		String dim = JOptionPane.showInputDialog(this,"Enter Dimentions: 45x23"); 
		if(dim == null) 
			return 0;
		try {

			String[] arrOfStr = dim.split("x", 2);
			dimRow = Integer.parseInt(arrOfStr[0]);
			dimCol = Integer.parseInt(arrOfStr[1]);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Invalid Dimensions!",
					"Error", JOptionPane.ERROR_MESSAGE);
			setDim();
			// return 0;
		}

		return 1;
	}

	/**
	 * Supplement method used by the controller.
	 * @param token This determines if a specific part of this method should be ran or not.
	 */
	public void gameWindow(int token) {
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		if(token == 1)
			create();

		if(token == 2) {
			CreateMenuWindow();
			return;
		}

		CreateGameWindow();
	}

	/**
	 * This is used by the timer to set the time of game.
	 * @param setTime The time to be set.
	 */
	public void setTime(int setTime) {
		actualTime.setText(Integer.toString(setTime));
	}

	/**
	 * This method creates the game menu.
	 */
	public void createMenu() {
		//		menuBar = new JMenuBar();
		//		game_mb = new JMenu("Game");
		//		help_mb = new JMenu("Help");

		menuBar.add(game_mb);
		menuBar.add(help_mb);
		menuBar.setBackground(Color.white);

		//		new_m = new JMenuItem("New");
		//		save = new JMenuItem("Save");
		//		solution = new JMenuItem("Solution");
		//		colorPick = new JMenuItem("Colors");
		//		new_dim = new JMenuItem("New Dimension");

		//		new_m.addActionListener(c);
		// solution.addActionListener(c);
		//		colorPick.addActionListener(c);
		//		new_dim.addActionListener(c);
		//		save.addActionListener(c);

		new_m.setIcon(new ImageIcon("piciconnew.gif"));
		solution.setIcon(new ImageIcon("piciconsol.gif"));
		colorPick.setIcon(new ImageIcon("piciconcol.gif"));

		game_mb.add(save);
		game_mb.add(new_m);
		game_mb.add(solution);
		help_mb.add(colorPick);
		game_mb.add(new_dim);
	}

	/**
	 * This method enables users to select colors.
	 */
	public void setColor() {

		JDialog dialog = new JDialog(this, "Color Selector", true);

		//		PgoodTile = new JPanel();
		//		PwrongTile = new JPanel();
		//		PmarkTile = new JPanel();

		PgoodTile.setBackground(good);
		PwrongTile.setBackground(wrong);
		PmarkTile.setBackground(mark);

		//		goodTile = new JButton("Good Tile");
		//		wrongTile = new JButton("Wrong Tile");
		//		markTile = new JButton("Mark Tile");

		//		goodTile.addActionListener(c);
		//		wrongTile.addActionListener(c);
		//		markTile.addActionListener(c);

		dialog.add(PgoodTile);
		dialog.add(PwrongTile);
		dialog.add(PmarkTile);

		dialog.add(goodTile);
		dialog.add(wrongTile);
		dialog.add(markTile);

		dialog.setLayout(new GridLayout(2,3));

		dialog.setSize(400, 100);

		dialog.setResizable(false);
		dialog.setVisible(true);
	}

	/**
	 * Used to set the size of the panel.
	 */
	public void setSize() {
		setSize(900, 700);
	}

	/**
	 * Creates a dialog for users to pick a color.
	 * @param col Color of dialog
	 * @return The selected color
	 */
	public Color colorPicker(Color col) {

		return JColorChooser.showDialog(this,"Select a color", col);
	}

	/**
	 * This is used to change the color of a tile.
	 * @param c Holds tile to be changed.
	 */
	public void tiles(String c) {
		// This is going to be used by the color picker.

		Color recent;
		switch(c) {
		case "Good":
			recent = good;
			good = colorPicker(good);
			PgoodTile.setBackground(good);
			for (int row = 0; row < buttons.length; row++) 
				for (int col = 0; col < buttons[row].length; col++) 
					if(buttons[row][col].getBackground() == recent)
						buttons[row][col].setBackground(good);
			break;

		case "Wrong":
			recent = wrong;
			wrong = colorPicker(wrong);
			PwrongTile.setBackground(wrong);
			for (int row = 0; row < buttons.length; row++) 
				for (int col = 0; col < buttons[row].length; col++) 
					if(buttons[row][col].getBackground() == recent)
						buttons[row][col].setBackground(wrong);
			break;

		case "Mark":
			recent = mark;
			mark = colorPicker(mark);
			PmarkTile.setBackground(mark);
			for (int row = 0; row < buttons.length; row++) 
				for (int col = 0; col < buttons[row].length; col++) 
					if(buttons[row][col].getBackground() == recent)
						buttons[row][col].setBackground(mark);
			break;

		}
	}

	/**
	 * This is used to set the text of our message label.
	 * @param message The text to be set.
	 */
	public void updateLabel(String message) {
		outputLabel.append(message + "\n");
	}

	/**
	 * Handles the splash screen.
	 */
	public void splashScreen() {
		JPanel p = new JPanel(new BorderLayout());
		p.setBackground(new Color(2, 24, 43));
		setContentPane(p);
		setLocationRelativeTo(null);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBackground(Color.white);
		progressBar.setForeground(new Color(2, 24, 43));
		progressBar.setValue(0);
		progressBar.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel image=new JLabel(new ImageIcon("piccross.png"));
		image.setSize(600,200);

		p.add(image, BorderLayout.CENTER);
		p.add(progressBar, BorderLayout.SOUTH);

		this.setVisible(true);

		int i = 0;

		while( i<=100)
		{
			try{
				Thread.sleep(50);
				progressBar.setString("LOADING " + Integer.toString(i)+"%");
				progressBar.setValue(i);

				if(i==100)
					Thread.sleep(1000);  
				i++;

			}catch(Exception e){
				e.printStackTrace();
			}

		}

	}

	/**
	 * This method creates the menu window of the game.
	 */
	public void CreateMenuWindow() {

		// Creating a panel to hold the content of the menu window
		panel = new JPanel(new BorderLayout());
		setContentPane(panel);

		GridBagConstraints gbc = new GridBagConstraints();
		JPanel[] DummyPanel = new JPanel[5];

		// This for loop is not very important, I am just creating dummy panels using gridbag layouts. This helps me align elements properly
		for (int i = 0; i < DummyPanel.length; i++) {
			DummyPanel[i] = new JPanel(new GridBagLayout());
			DummyPanel[i].setPreferredSize(new Dimension(120, 100));
			DummyPanel[i].setBackground(new Color(2, 24, 43));
		}

		panel.add(DummyPanel[0], BorderLayout.SOUTH);
		panel.add(DummyPanel[1], BorderLayout.NORTH);
		panel.add(DummyPanel[2], BorderLayout.WEST);
		panel.add(DummyPanel[3], BorderLayout.EAST);

		JPanel MenuPanel = new JPanel();
		MenuPanel.setBackground(new Color(2, 24, 43));
		panel.add(MenuPanel, BorderLayout.CENTER);
		MenuPanel.setLayout(new GridLayout(5, 0, 0, 0));

		welcome_label = new JLabel("Welcome");
		welcome_label.setFont(new Font("Tahoma", Font.BOLD, 20));
		welcome_label.setPreferredSize(new Dimension(130, 36));
		welcome_label.setHorizontalAlignment(SwingConstants.CENTER);
		welcome_label.setForeground(new Color(215, 38, 61));

		panel.add(DummyPanel[1], BorderLayout.NORTH);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(10, 10, 10, 10);

		DummyPanel[1].add(welcome_label, gbc);

		JPanel GameModeLabelPanel = new JPanel();
		GameModeLabelPanel.setBackground(new Color(2, 24, 43));
		MenuPanel.add(GameModeLabelPanel);
		GameModeLabelPanel.setLayout(new GridLayout(2, 0, 0, 0));

		GameMode_label = new JLabel("Game Mode");
		GameMode_label.setFont(new Font("Tahoma", Font.BOLD, 20));
		GameMode_label.setPreferredSize(new Dimension(130, 25));
		GameMode_label.setHorizontalAlignment(SwingConstants.CENTER);
		GameMode_label.setForeground(new Color(215, 38, 61));
		GameModeLabelPanel.add(GameMode_label);

		JPanel GameModeCBPanel = new JPanel();
		GameModeCBPanel.setBackground(new Color(2, 24, 43));
		GameModeLabelPanel.add(GameModeCBPanel);
		GameModeCBPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		GameMode_cb = new JComboBox<String>(mode);
		GameMode_cb.setPreferredSize(new Dimension(100, 20));
		//		GameMode_cb.addActionListener(c);
		GameModeCBPanel.add(GameMode_cb);

		JPanel languagepanel = new JPanel();
		languagepanel.setBackground(new Color(2, 24, 43));
		MenuPanel.add(languagepanel);
		languagepanel.setLayout(new GridLayout(2, 0, 0, 0));

		Language_label = new JLabel("Language");
		Language_label.setFont(new Font("Tahoma", Font.BOLD, 20));
		Language_label.setPreferredSize(new Dimension(99, 25));
		Language_label.setHorizontalAlignment(SwingConstants.CENTER);
		Language_label.setForeground(new Color(215, 38, 61));
		languagepanel.add(Language_label);

		JPanel LanguageCBLabel = new JPanel();
		LanguageCBLabel.setBackground(new Color(2, 24, 43));
		languagepanel.add(LanguageCBLabel);
		LanguageCBLabel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		Language_cb = new JComboBox<String>(langauage);
		Language_cb.setPreferredSize(new Dimension(100, 20));
		//		Language_cb.addActionListener(c);
		LanguageCBLabel.add(Language_cb);

		JPanel LoadPanel = new JPanel();
		LoadPanel.setBackground(new Color(2, 24, 43));
		MenuPanel.add(LoadPanel);
		LoadPanel.setLayout(new GridLayout(2, 0, 0, 0));

		Load_label = new JLabel("Load");
		Load_label.setFont(new Font("Tahoma", Font.BOLD, 20));
		Load_label.setPreferredSize(new Dimension(130, 25));
		Load_label.setHorizontalAlignment(SwingConstants.CENTER);
		Load_label.setForeground(new Color(215, 38, 61));
		LoadPanel.add(Load_label);

		JPanel LoadCBPanel = new JPanel();
		LoadCBPanel.setBackground(new Color(2, 24, 43));
		LoadPanel.add(LoadCBPanel);
		LoadCBPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		Load_cb = new JComboBox<String>(load);
		Load_cb.setPreferredSize(new Dimension(100, 20));
		//		Load_cb.addActionListener(c);
		LoadCBPanel.add(Load_cb);

		JPanel DummyPannel = new JPanel();
		DummyPannel.setBackground(new Color(2, 24, 43));
		MenuPanel.add(DummyPannel);
		DummyPannel.setLayout(new BorderLayout(0, 0));

		JPanel StartButtonPanel = new JPanel(new GridBagLayout());
		StartButtonPanel.setBackground(new Color(2, 24, 43));
		MenuPanel.add(StartButtonPanel);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(10, 10, 10, 10);

		Start_b = new JButton("Start");
		Start_b.setPreferredSize(new Dimension(140, 22));
		Start_b.setBackground(Color.white);
		Start_b.setForeground(new Color(215, 38, 61));
		Start_b.setBorderPainted(false);
		//		Start_b.addActionListener(c);
		StartButtonPanel.add(Start_b, gbc);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.CENTER;

		JLabel author = new JLabel("Author: Caleb Orhomre");
		author.setForeground(new Color(215, 38, 61));
		DummyPanel[0].add(author, gbc);

		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.SOUTHWEST;

		Help_b = new JButton("Help");
		Help_b.setPreferredSize(new Dimension(140, 22));
		Help_b.setBackground(Color.white);
		Help_b.setForeground(new Color(215, 38, 61));
		Help_b.setBorderPainted(false);
		//		Help_b.addActionListener(c);
		DummyPanel[0].add(Help_b, gbc);

		Help_Label = new JTextArea("Here to help you\n"
				+ "Here to help you\n"
				+ "Here to help you\n"
				+ "Here to help you\n"
				+ "Here to help you\n"
				+ "Here to help you\n");
		Help_Label.setEditable(false);
		Help_Label.setSize(144, 148);
		Help_Label.setBackground(new Color(2, 24, 43));
		Help_Label.setForeground(Color.white);
		Help_Label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Help_Label.setVisible(false);
		DummyPanel[2].add(Help_Label);
	}

	/**
	 * This method creates the board panel.
	 */
	public void boardPanel() {

		// Creating a board panel with a gridlayout.
		boardPanel = new JPanel(new GridLayout(dimRow, dimCol, 0, 0));
		boardPanel.setBackground(Color.white);
		buttons = new JButton[dimRow][dimCol];   

		for (int row = 0; row < buttons.length; row++) {
			for (int col = 0; col < buttons[row].length; col++) {
				// String text = String.format("[%d, %d]", row, col);
				buttons[row][col] = new JButton();	 
				buttons[row][col].setBackground(Color.white);
				// buttons[row][col].addActionListener(c);
				boardPanel.add(buttons[row][col]);
			}
		}
	}

	/**
	 * This method creates the control panel.
	 */
	public void controlPanel() {

		controlPanel = new JPanel();
		controlPanel.setPreferredSize(new Dimension(200, 600));
		controlPanel.setBackground(Color.white);

		point = new JPanel();
		point.setBackground(Color.white);

		pointText = new JLabel("Point: ");
		actualPoint = new JLabel("0");

		point.add(pointText);
		point.add(actualPoint);

		output = new JPanel(new BorderLayout());
		output.setPreferredSize(new Dimension(190, 400));
		outputLabel = new JTextArea();
		outputLabel.setFont(new Font("Monospaced", Font.BOLD, 11));
		outputLabel.setEditable(false);
		output.setBorder(new BevelBorder(BevelBorder.RAISED));
		output.add(outputLabel);

		JScrollPane scrollableTextArea = new JScrollPane(outputLabel);  
		scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		output.add(scrollableTextArea);

		time = new JPanel();
		time.setBackground(Color.white);

		timeText = new JLabel("Time: ");
		actualTime = new JLabel("0");

		time.add(timeText);
		time.add(actualTime);

		back_p = new JPanel();
		back_p.setBackground(Color.white);
		back_b = new JButton("Back");
		back_p.add(back_b);
		back_b.setBackground(Color.white);
		back_p.setPreferredSize(new Dimension(140, 50));

		reset_b = new JButton("Reset");
		reset_b.setBackground(Color.white);

		controlPanel.add(point);
		controlPanel.add(output);
		controlPanel.add(time);
		controlPanel.add(back_p);
		controlPanel.add(reset_b);

	}

	/**
	 * The method creates the top panel.
	 */
	public void topPanel() {
		BorderLayout layout1 = new BorderLayout();
		layout1.setHgap(10);

		topPanel = new JPanel();
		topPanel.setLayout(layout1);
		topPanel.setPreferredSize(new Dimension(80, 90));
		topPanel.setBackground(Color.darkGray);

		JPanel tp1 = new JPanel();
		tp1.setBackground(Color.white);
		tp1.setPreferredSize(new Dimension(100, 700));

		JLabel l = new JLabel("PICROSS");
		l.setFont(new Font("Monospaced", Font.BOLD, 30));
		l.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel tp2 = new JPanel(new GridLayout(1, 0));
		tp2.setBackground(Color.white);
		tp2.setPreferredSize(new Dimension(200, 700));
		tp2.add(l);

		JPanel tp3 = new JPanel(new GridLayout(Math.round((float) dimCol/2), dimCol));
		tp3.setBackground(Color.white);
		tp3.setPreferredSize(new Dimension(100, 700));

		JPanel tp4 = new JPanel();
		tp4.setBackground(Color.white);
		tp4.setPreferredSize(new Dimension(50, 25));
		tp4.add(menuBar);

		topPanel.add(tp1, BorderLayout.WEST);
		topPanel.add(tp2, BorderLayout.EAST);
		topPanel.add(tp3, BorderLayout.CENTER);
		topPanel.add(tp4, BorderLayout.NORTH);

		cBoxMark.setBackground(Color.white);

		tp1.add(cBoxMark);

		JLabel[] label = new JLabel[dimCol];    

		int val = Math.round((float) dimCol/2);

		for(int j = 0; j < val; j++) {
			for(int i = 0; i < GameModel.colVal[0].length; i++) {
				if(GameModel.colVal[j][i] == 0)
					label[i] = new JLabel(".");
				else
					label[i] = new JLabel(Integer.toString(GameModel.colVal[j][i]));

				label[i].setHorizontalAlignment(SwingConstants.CENTER);
				tp3.add(label[i]);
			}
		}
	}

	/**
	 * This method is used to adjust the color and state of the button. i.e when a user click a correct tile, this method changes the button to the correct color.
	 * @param col This variable holds the color that the button is to be changed to.
	 * @param button This holds the button that is to be changed.
	 */
	public void adjustButton(Color col, JButton button) {
		button.setBackground(col);

		button.setEnabled(false);
	}

	/**
	 * This method updates the score of the game depending on the value passed.
	 * @param pass This variable indicates if the a point is added or deducted. 1 = pass, 0 = fail.
	 */
	public void updateScore(int pass) {
		int score = Integer.parseInt(actualPoint.getText());

		switch(pass) {
		case 1:

			actualPoint.setText(String.valueOf(score + 1));
			break;
		case 0:
			if(score != 0)
				actualPoint.setText(String.valueOf(score - 1));
			break;
		}
	}

	/**
	 * This method creates the left panel in the game window.
	 */
	public void leftPanel() {

		leftPanel = new JPanel(new GridLayout(dimRow, Math.round((float) dimCol/2)));
		JLabel[] label1 = new JLabel[30];  

		for (int i = 0; i < GameModel.rowVal.length; i++) { 
			for (int j = 0; j < Math.round((float) dimCol/2) && j < GameModel.rowVal[0].length; j++) { 

				if(GameModel.rowVal[i][j] == 0)
					label1[j] = new JLabel(".");
				else
					label1[j] = new JLabel(Integer.toString(GameModel.rowVal[i][j]));

				label1[j].setHorizontalAlignment(SwingConstants.CENTER);
				leftPanel.add(label1[j]);
			}

		}

		leftPanel.setBackground(Color.white);
		leftPanel.setPreferredSize(new Dimension(100, 600));
	}

	/**
	 * This method creates the game window, it does this by calling several methods needed to create the window. 
	 */
	public void CreateGameWindow() {

		panel.setBackground(Color.darkGray);
		panel.setSize(600, 500);

		BorderLayout layout = new BorderLayout();
		layout.setHgap(8);
		layout.setVgap(8);
		panel.setLayout(layout);

		panel.add(boardPanel, BorderLayout.CENTER);
		panel.add(controlPanel, BorderLayout.EAST); 
		panel.add(topPanel, BorderLayout.NORTH);
		panel.add(leftPanel, BorderLayout.WEST); 

	}

	/**
	 * This method returns a selected file name.
	 * @return The file a user selected.
	 */
	public File fileName(int token) {
		JFileChooser j = new JFileChooser("f:");
		j.setBackground(Color.red);
		int r = 0;
		if(token == 1)
			r = j.showSaveDialog(this);
		else
			r = j.showOpenDialog(this);

		if (r == JFileChooser.APPROVE_OPTION) {
			File file = new File(j.getSelectedFile().getAbsolutePath());
			return file;
		}

		return null;	
	}

	/**
	 * This method just creates a window for winners message.
	 */
	public void over(boolean test) {

		setSize(800, 500);
		ImageIcon icon = null;

		if(test == true) 
			icon = new ImageIcon("gamepicwinner.png");
		else 
			icon = new ImageIcon("gamepicend.png");

		//		ok_b = new JButton();
		//		ok_b.addActionListener(c);
		ok_b.setText("OK");
		ok_b.setBorderPainted(false);
		ok_b.setBackground(Color.white);
		ok_b.setForeground(new Color(215, 38, 61));
		ok_b.setPreferredSize(new Dimension(100, 22));



		JLabel label = new JLabel(icon);
		panel.removeAll();
		panel.revalidate();
		panel.repaint();

		panel.setLayout(new FlowLayout());
		panel.add(label);
		panel.add(ok_b);
	}

	/**
	 * This method is used to implement internalization.
	 * @param langauge This variable holds the language that the function will work with.
	 */
	public void SetLangauge(String langauge) {
		switch(langauge){
		case "Français":
			currentLocale = new Locale.Builder().setLanguage("fr").setRegion("FR").build();
			texts = ResourceBundle.getBundle(SYSTEMMESSAGES, currentLocale);

			welcome_label.setText(texts.getString("WELCOME"));
			GameMode_label.setText(texts.getString("GAME_MODE"));
			Language_label.setText(texts.getString("LANGUAGE"));
			Load_label.setText(texts.getString("LOAD"));
			Help_b.setText(texts.getString("HELP"));
			Start_b.setText(texts.getString("START"));
			back_b.setText(texts.getString("BACK"));
			timeText.setText(texts.getString("TIME"));
			reset_b.setText(texts.getString("RESET"));
			cBoxMark.setText(texts.getString("Mark"));
			game_mb.setText(texts.getString("Game"));
			help_mb.setText(texts.getString("Help"));
			pointText.setText(texts.getString("Point"));
			solution.setText(texts.getString("Solution"));
			new_m.setText(texts.getString("New"));
			colorPick.setText(texts.getString("Color"));
			save.setText(texts.getString("Save"));
			new_dim.setText(texts.getString("Dimention"));
			break;

		case "English":
			currentLocale = new Locale.Builder().setLanguage("en").setRegion("US").build();
			texts = ResourceBundle.getBundle(SYSTEMMESSAGES, currentLocale);

			welcome_label.setText(texts.getString("WELCOME"));
			GameMode_label.setText(texts.getString("GAME_MODE"));
			Language_label.setText(texts.getString("LANGUAGE"));
			Load_label.setText(texts.getString("LOAD"));
			Help_b.setText(texts.getString("HELP"));
			Start_b.setText(texts.getString("START"));
			back_b.setText(texts.getString("BACK"));
			timeText.setText(texts.getString("TIME"));
			reset_b.setText(texts.getString("RESET"));
			cBoxMark.setText(texts.getString("Mark"));
			game_mb.setText(texts.getString("Game"));
			help_mb.setText(texts.getString("Help"));
			pointText.setText(texts.getString("Point"));
			solution.setText(texts.getString("Solution"));
			new_m.setText(texts.getString("New"));
			colorPick.setText(texts.getString("Color"));
			save.setText(texts.getString("Save"));
			new_dim.setText(texts.getString("Dimention"));
			break;
		}
	}
}

