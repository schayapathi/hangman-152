import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Observable;
import java.util.Observer;


public class GallowView extends JPanel implements Observer
{
	private Model model;

	public GallowView(Model m) {
		model = m;
		model.addObserver(this);
		setBackground(new Color(250, 230, 180));
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//draw gallows
		g.setColor(Color.BLACK);
        g.drawLine(20,220,220,220);
        g.drawLine(170,220,170,20);
        g.drawLine(120,20,170,20);
        g.drawLine(130,20,130,50);

      //drawHead()
        if(model.guesses <= 5)
        	g.drawOval(115,50,30,30);
      
      //drawBody()
      	if(model.guesses <= 4)
        	g.drawLine(130,80,130,150);
      
      //drawLeftArm()
        if(model.guesses <= 3)
        	g.drawLine(130,120,120,85);
      
      //drawRightArm()
        if(model.guesses <= 2)
        	g.drawLine(130,120,140,85);

      //drawLeftLeg()
     	if(model.guesses <= 1)
        	g.drawLine(130,150,120,195);
      
      //drawRightLeg()
      	if(model.guesses <= 0)
        	g.drawLine(130,150,140,195);

        if(model.getWord().equals(model.getInProgress())) {
        	setFont( new Font("Monospaced", Font.BOLD, 20));
        	g.setColor(Color.GREEN);
        	g.drawString("You Win!", 100, 400);
        }
        else if(model.guesses >= 0)
        	g.drawString(model.guesses + " incorrect guesses left", 50, 450);
        else if(model.guesses < 0) {
        	setFont( new Font("Monospaced", Font.BOLD, 20));
        	g.setColor(Color.RED);
        	g.drawString("You Lose!", 100, 400);
        }
    }
	public void update (Observable o, Object arg)
	{
		repaint();
	}
	
}