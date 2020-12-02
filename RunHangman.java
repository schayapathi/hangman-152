package hangman;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class RunHangman {
	public static void main(String[]args) throws IOException {
		JFrame frame = new JFrame("Let's Play Hangman!");
		JPanel mainPage = new JPanel();		
		mainPage.setLayout(new BorderLayout());
		
		BufferedImage image;
		image = ImageIO.read(new File("hangman.jpg"));
		JLabel imageLabel = new JLabel(new ImageIcon(image));
		
		JPanel welcomPanel = new JPanel();	
		JLabel welcome = new JLabel("Welcome to Hangman!");
		welcome.setFont(new Font("Serif", Font.BOLD, 40));
		welcomPanel.add(welcome);
		
		JButton start = new JButton("Start Game");
		start.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Model m = new Model(WordPicker.chooseWord());
				GallowView v = new GallowView(m);
				JFrame drawing = new JFrame();
				drawing.setSize(270, 500);
				drawing.setContentPane(v);
				drawing.show();
				drawing.setLocation(900, 0);
				BoardView h = new BoardView(m);
				frame.getContentPane().remove(mainPage);
				frame.setContentPane(h);
				frame.revalidate();
				frame.pack();
				frame.setSize(900, 500);
				frame.setResizable(false);
			}
		});

		
		JPanel bottom = new JPanel();
		bottom.add(start);
		bottom.setVisible(true);
		bottom.setPreferredSize(new Dimension(300, 100));
		
		mainPage.add(welcomPanel, BorderLayout.NORTH);
		mainPage.add(imageLabel, BorderLayout.CENTER);
		mainPage.add(bottom, BorderLayout.SOUTH);
		
		frame.add(mainPage);
		//mainPage.setLayout(new BoxLayout(mainPage, BoxLayout.Y_AXIS));

		frame.pack();
		frame.setVisible(true);
		frame.setSize(900,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}