package ponggame.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;

import ponggame.gamelogic.PongGame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class UserInterface extends JPanel implements Runnable {
	
	private JFrame frame;
	private PongGame pg;
	
	public UserInterface(PongGame pg){
		super.setBackground(Color.BLACK);
		this.pg = pg;
	}
	
	public void run() {
		frame = new JFrame("Pong Game");
		
		int calibration = 40; // To allow size of board to align with size of window
		
		frame.setPreferredSize(new Dimension(pg.getBoardWidth() + calibration, pg.getBoardHeight() + calibration));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		createComponents(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);
	}
	
	public void createComponents(Container container){
		container.add(new UserInterface(pg));
		frame.addKeyListener(new KeyboardListener(pg, frame));
		frame.setFocusable(true);
	}
	
	@Override
	protected void paintComponent(Graphics graphics){
		super.paintComponent(graphics);
		pg.getBall().draw(graphics);
		pg.getPaddleLeft().draw(graphics);
		pg.getPaddleRight().draw(graphics);
	}
	
	public JFrame getFrame(){
		return frame;
	}
}

