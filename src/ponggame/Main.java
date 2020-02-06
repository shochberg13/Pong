package ponggame;

import javax.swing.SwingUtilities;

import ponggame.gamelogic.PongGame;
import ponggame.gui.UserInterface;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		PongGame pg = new PongGame(800, 800);
		UserInterface ui = new UserInterface(pg);

		SwingUtilities.invokeLater(ui);
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("Waiting for game to start");
		}
		pg.setUI(ui);
		pg.start();
	}
}
