package ponggame.gui;


import ponggame.domain.Paddle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class KeyboardListener implements KeyListener{

	private Paddle paddleLeft;
	private Paddle paddleRight;
	private JFrame frame;
	
	public KeyboardListener(Paddle paddleLeft, Paddle paddleRight, JFrame frame){
		this.paddleLeft = paddleLeft;
		this.paddleRight = paddleRight;
		this.frame = frame;
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP){
			this.paddleRight.move(-40);
		}else if (e.getKeyCode() == KeyEvent.VK_DOWN){
			this.paddleRight.move(40);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_A){
			this.paddleLeft.move(-40);
		}else if (e.getKeyCode() == KeyEvent.VK_Z){
			this.paddleLeft.move(40);
		}
		frame.repaint();
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

}

