package ponggame.gui;

import java.awt.Color;
import java.awt.Graphics;

import ponggame.gamelogic.PongGame;

import javax.swing.JPanel;

public class DrawingBoard extends JPanel {
	private PongGame pg;
	
	public DrawingBoard(PongGame pg){
		super.setBackground(Color.BLACK);
		this.pg = pg;
	}
	
	@Override
	protected void paintComponent(Graphics graphics){
		super.paintComponent(graphics);
		pg.getBall().draw(graphics);
		pg.getPaddleLeft().draw(graphics);
		pg.getPaddleRight().draw(graphics);
	}
	
}
