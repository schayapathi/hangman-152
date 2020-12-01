import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;


public class View extends JPanel implements Observer
{
	private Model model;
	private ArrayList<JTextArea> textAreas;

	public View() {
		String word = WordPicker.chooseWord();
		model = new Model(word);
		System.out.println(word);
		textAreas = new ArrayList<JTextArea>();
		model.addObserver(this);

		//For # characters in model, create JTextAreas in a line
		for(int i = 0; i < model.getLength(); i++)
		{
			JTextArea ta = new JTextArea(5, 5);
			textAreas.add(ta);
			this.add(ta);
		}


		//Create 26 buttons for letters that the user can use to guess
		JButton letter;
		JPanel buttons = new JPanel(new GridLayout(3, 0));
		for(char c = 'A'; c <= 'Z'; c++)
		{
			letter = new JButton(c + "");
			letter.setName(c + "");
			letter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					JButton j = (JButton)e.getSource();
					j.setEnabled(false);
					model.checkGuess(j.getName());
				}
			});
			buttons.add(letter);
		}
		this.add(buttons);
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