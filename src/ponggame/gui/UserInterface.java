package ponggame.gui;

import java.awt.Container;
import java.awt.Dimension;

import ponggame.gamelogic.PongGame;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class UserInterface implements Runnable{
	
	private JFrame frame;
	private PongGame pg;
	
	public UserInterface(PongGame pg){
		this.pg = pg;
	}
	
	public void run() {
		frame = new JFrame("Pong Game");
		frame.setPreferredSize(new Dimension(pg.getBoardWidth(), pg.getBoardHeight()));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		createComponents(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);
	}
	
	public void createComponents(Container container){
		DrawingBoard db = new DrawingBoard(pg);
		container.add(db);
		frame.addKeyListener(new KeyboardListener(pg.getPaddleLeft(), pg.getPaddleRight(), frame));
	}
	
	public JFrame getFrame(){
		return frame;
	}
}

