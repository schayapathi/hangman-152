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
	}

	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
	}

	public void update (Observable o, Object arg)
	{
		repaint();
	}
}