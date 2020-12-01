import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;

public class HangmanGUI extends JPanel implements Observer {
	private Model model;

	private HangmanDisplay display;
	private ArrayList<JTextArea> textAreas;
	private JButton nextButton;
	private JButton newGameButton;
	
	private  String message; 
	//private String word;
	private String guesses; 
	private int incorrectGuesses;
	private boolean gameOver;
	
	//Lower panel that contains game controller buttons
	private class Buttons implements ActionListener {
		public void actionPerformed( ActionEvent event ) {
			JButton whichButton = (JButton)event.getSource();  
			String command = event.getActionCommand();
			if (command.equals("Quit")) {
				System.exit(0);
			}
			else if (command.equals("New Game")) {
				startGame();
			}
			display.repaint();
		}
	}
	
	//Top panel that contains game message, JButton and JTextArea for guessing
	private class HangmanDisplay extends JPanel {
		HangmanDisplay() {
			setSize(new Dimension(620,420));
			setBackground( new Color(250, 230, 180) );
			setFont( new Font("Monospaced", Font.BOLD, 20));
			JTextArea input = new JTextArea(1, 10);
			add(input);
			JButton guess = new JButton("Guess Word");
			guess.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameOver = model.wordIsComplete(input.getText());
					if(gameOver) {
						message = "You Win!";
						repaint();
					}
				}
			});
			add(guess);
		}
		protected void paintComponent(Graphics x) {
			super.paintComponent(x);
			System.out.println("in paint component");
			((Graphics2D)x).setStroke(new BasicStroke(3));
			if (message != null) {
				x.setColor(Color.BLUE);
				x.drawString(message, 20, 30);
			}
			System.out.println("drew message");
			
		}
	}
	
	//Create bottom panel to hold game controller buttons, grid panel to hold
	//textareas and jbuttons.
	public HangmanGUI() {
		model = new Model(WordPicker.chooseWord());
		System.out.println(model.getWord());
		textAreas = new ArrayList<JTextArea>();
		model.addObserver(this);		
		
		Buttons buttonActivate = new Buttons();
		display = new HangmanDisplay();
		JPanel bottom = new JPanel();
		
		setLayout(new BorderLayout(5,5));  
		
		nextButton = new JButton("Next word");
		nextButton.addActionListener(buttonActivate);
		bottom.add(nextButton);
		
		newGameButton = new JButton("New Game");
		newGameButton.addActionListener(buttonActivate);
		bottom.add(newGameButton);
		
		JButton quit = new JButton("Quit");
		quit.addActionListener(buttonActivate);
		bottom.add(quit);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(250, 230, 180));
		JPanel guessPanel = new JPanel();
		guessPanel.setBackground(new Color(250, 230, 180));

		//For # characters in model, create JTextAreas in a line
		for(int i = 0; i < model.getLength(); i++)
		{
			JTextArea ta = new JTextArea(4, 4);
			textAreas.add(ta);
			guessPanel.add(ta);
		}

		//Create 26 buttons for each letter available for user guesses
		JButton letter;
		JPanel buttons = new JPanel(new GridLayout(3, 0));

		for(char c = 'A'; c <= 'Z'; c++)
		{
			letter = new JButton(c + "");
			letter.setPreferredSize(new Dimension(70, 70));
			letter.setName(c + "");
			letter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					JButton j = (JButton)e.getSource();
					j.setEnabled(false);
					model.checkGuess(j.getName());
					System.out.println("checking guess");
					if(model.wordIsComplete(model.getInProgress())) {
						System.out.println("word is complete");
						gameOver = true;
						display.repaint();
					}
					else
						drawHangman();	
				}
			});
			buttons.add(letter);
		}
		centerPanel.add(guessPanel);
		centerPanel.add(buttons);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(display, BorderLayout.NORTH); 
		this.add(bottom, BorderLayout.SOUTH);  
		
		setBackground( new Color(100,0,0) );
		setBorder(BorderFactory.createLineBorder(new Color(100,0,0), 3));
		
		startGame();
	}

	//Start game is called when the game first starts and whenever user clicks New Game
	private void startGame() {
		gameOver = false;
		guesses = "";
		incorrectGuesses = 0;
		nextButton.setEnabled(false);
		
		newGameButton.setEnabled(true);
		message = "This word has " + model.getWord().length() + " letters. Can you guess the word?";
	}
	

	//Update the JTextAreas if there was progress on the word
	public void update (Observable o, Object arg)
	{
		for(int i = 0; i < model.getWord().length(); i++)
		{
			if(model.getInProgress().charAt(i) != '_')
			{
				textAreas.get(i).setText(model.getInProgress().charAt(i) + "");
			}
		}
	}

	public void drawHangman()
	{

	}

}