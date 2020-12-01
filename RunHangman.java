import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RunHangman {
	public static void main(String[]args) {
		JFrame frame = new JFrame("Let's Play Hangman");
		JPanel mainPage = new JPanel();
		//this.setLayout(new BorderLayout());

		JButton start = new JButton("Start Game");
		start.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				View v = new View();
				HangmanGUI h = new HangmanGUI();
				frame.getContentPane().remove(mainPage);
				frame.setContentPane(h);
				frame.revalidate();
				frame.pack();
				frame.setSize(900, 500);
				frame.setResizable(false);
			}
		});

		JPanel bottom = new JPanel(new GridLayout(3, 4, 1, 1));
		bottom.add(start);
		bottom.setVisible(true);
		bottom.setPreferredSize(new Dimension(300, 100));
		mainPage.add(bottom, BorderLayout.SOUTH);
		frame.add(mainPage);
		//mainPage.setLayout(new BoxLayout(mainPage, BoxLayout.Y_AXIS));

		frame.pack();
		frame.setVisible(true);
		frame.setSize(900,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setFont(new Font("Serif", Font.PLAIN, 20));
		g2.drawString("Welcome to Hangman!", 10, 10);
	}
}