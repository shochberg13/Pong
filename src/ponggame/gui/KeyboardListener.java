package ponggame.gui;


import ponggame.domain.Paddle;
import ponggame.gamelogic.PongGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class KeyboardListener implements KeyListener{

	private Paddle paddleLeft;
	private Paddle paddleRight;
	private int boardHeight;
	private JFrame frame;
	
	public KeyboardListener(PongGame pg, JFrame frame){
		this.paddleLeft = pg.getPaddleLeft();
		this.paddleRight = pg.getPaddleRight();
		this.boardHeight = pg.getBoardHeight();
		this.frame = frame;
	}
	
	
	public void keyPressed(KeyEvent e) {
		// If up or down is pressed, check if not hitting the wall, and move the right paddle.
		if (e.getKeyCode() == KeyEvent.VK_UP){
			if (this.paddleRight.getY() > 0) this.paddleRight.move(-40);
		}else if (e.getKeyCode() == KeyEvent.VK_DOWN){
			if (this.paddleRight.getY() + this.paddleRight.getPaddleHeight() < this.boardHeight) this.paddleRight.move(40);
		}
		
		// If 'a' or 'z' are pressed, check if not hitting the wall, and move the left paddle.
		if (e.getKeyCode() == KeyEvent.VK_A){
			if (this.paddleLeft.getY() > 0)this.paddleLeft.move(-40);
		}else if (e.getKeyCode() == KeyEvent.VK_Z){
			if (this.paddleLeft.getY() + this.paddleLeft.getPaddleHeight() < this.boardHeight)this.paddleLeft.move(40);
		}
		frame.repaint();
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

}

