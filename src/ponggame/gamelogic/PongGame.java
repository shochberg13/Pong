package ponggame.gamelogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import ponggame.gui.UserInterface;
import ponggame.domain.Ball;
import ponggame.domain.Direction;
import ponggame.domain.Paddle;

public class PongGame extends Timer implements ActionListener {
	private int boardHeight;
	private int boardWidth;
	private int spacing;
	private Paddle paddleLeft;
	private Paddle paddleRight;
	private Ball ball;
	private boolean gameContinues;
	private UserInterface ui;
	
	public PongGame(int boardHeight, int boardWidth){
		super(1000, null);
		this.boardHeight = boardHeight;
		this.boardWidth = boardWidth;
		this.spacing = 15; // Space between paddle and edge of board
		
		this.paddleLeft = new Paddle();
		this.paddleLeft.setX(spacing);
		this.paddleLeft.setY((boardHeight / 2) - (this.paddleLeft.getPaddleHeight() / 2));
		
		this.paddleRight = new Paddle();
		this.paddleRight.setX(boardWidth - (20 + spacing + this.paddleRight.getPaddleWidth()) );
		this.paddleRight.setY((boardHeight/2) - (this.paddleRight.getPaddleHeight() / 2));

		this.ball = new Ball(boardWidth / 2, boardHeight / 2, Direction.SOUTHWEST);

		addActionListener(this);
		setInitialDelay(2000);
		this.gameContinues = true;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
//		while(gameContinues){
//
//			ballMoves();
//			this.ui.getFrame().repaint();
//			
//			if(ballHitsPaddle()){
//				changeXDirection();
//			}
//			
//			if (ballHitsNorthOrSouthWall()){
//				changeYDirection();
//			}
//			
//			if(ballGoesOutOfBounds()){
//				gameContinues = false;
//			}
//			
//			delay(50);
//		}
		
		// Code for point scoring goes here!
	}
	
	public void start(){
		while(gameContinues){

			ballMoves();
			this.ui.getFrame().repaint();
			
			if(ballHitsPaddle()){
				changeXDirection();
			}
			
			if (ballHitsNorthOrSouthWall()){
				changeYDirection();
			}
			
			if(ballGoesOutOfBounds()){
				gameContinues = false;
			}
			
			delay(50);
		}
		
		// Code for point scoring goes here!
	}
	
	public void ballMoves(){
		int dx = 10;
		int dy = 10;
		if (this.ball.getDirection() == Direction.NORTHEAST){
			this.ball.move(dx, -dy);
		}else if(this.ball.getDirection() == Direction.NORTHWEST){
			this.ball.move(-dx, -dy);
		}else if (this.ball.getDirection() == Direction.SOUTHEAST){
			this.ball.move(dx, dy);
		}else if (this.ball.getDirection() == Direction.SOUTHWEST){
			this.ball.move(-dx, dy);
		}
		// REPAINT
	}
	
	public boolean ballHitsPaddle(){
		
		// If it hits the right paddle
		if (ball.getDirection() == Direction.NORTHEAST || ball.getDirection() == Direction.SOUTHEAST){
			if (ball.getX() + ball.getBallDiameter() >= (boardWidth - (paddleRight.getPaddleWidth() + spacing))){
				if (ball.getY() + ball.getBallDiameter() > paddleRight.getY() && 
						ball.getY() < paddleRight.getY() + paddleRight.getPaddleHeight()){
					return true;
				}
			}
		}
		// If it hits the left paddle
		if(ball.getDirection() == Direction.NORTHWEST || ball.getDirection() == Direction.SOUTHWEST){
			if (ball.getX()  <= this.spacing + paddleLeft.getPaddleWidth()){
				if (ball.getY() + ball.getBallDiameter() > paddleLeft.getY() && 
						ball.getY() < paddleLeft.getY() + paddleLeft.getPaddleHeight()){
					return true;
				}
			}
		}
		return false;
	
	}
	
	public boolean ballHitsNorthOrSouthWall(){
		if (this.ball.getY() >= this.boardHeight || this.ball.getY() <= 0){
			return true;
		}
		return false;
	}
	
	public boolean ballGoesOutOfBounds(){
		if (this.ball.getX() <= 0 || this.ball.getX() >= this.boardWidth){
			return true;
		}
		return false;
	}
	
	public void changeXDirection(){
		if (this.ball.getDirection() == Direction.NORTHEAST){
			this.ball.setDirection(Direction.NORTHWEST);
			
		}else if (this.ball.getDirection() == Direction.NORTHWEST){
			this.ball.setDirection(Direction.NORTHEAST);
				
		}else if (this.ball.getDirection() == Direction.SOUTHEAST){
			this.ball.setDirection(Direction.SOUTHWEST);
		
		}else if (this.ball.getDirection() == Direction.SOUTHWEST){
			this.ball.setDirection(Direction.SOUTHEAST);
			
		}else if (this.ball.getDirection() == Direction.FLATWEST){
			this.ball.setDirection(Direction.FLATEAST);
		
		}else if (this.ball.getDirection() == Direction.FLATEAST){
			this.ball.setDirection(Direction.FLATWEST);
		}
	}
	
	public void changeYDirection(){
		if (this.ball.getDirection() == Direction.NORTHEAST){
			this.ball.setDirection(Direction.SOUTHEAST);
			
		}else if (this.ball.getDirection() == Direction.NORTHWEST){
			this.ball.setDirection(Direction.SOUTHWEST);
				
		}else if (this.ball.getDirection() == Direction.SOUTHEAST){
			this.ball.setDirection(Direction.NORTHEAST);
		
		}else if (this.ball.getDirection() == Direction.SOUTHWEST){
			this.ball.setDirection(Direction.NORTHWEST);
		}
	}
	
	public void delay(int x){
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Paddle getPaddleLeft(){
		return this.paddleLeft;
	}
	
	public Paddle getPaddleRight(){
		return this.paddleRight;
	}
	
	public Ball getBall(){
		return this.ball;
	}
	
	public int getBoardHeight(){
		return this.boardHeight;
	}
	
	public int getBoardWidth(){
		return this.boardWidth;
	}
	
	public void setPaddleLeft(Paddle paddleLeft){
		this.paddleLeft = paddleLeft;
	}
	
	public void setPaddleRight(Paddle paddleRight){
		this.paddleRight = paddleRight;
	}
	
	public void setBall(Ball ball){
		this.ball = ball;
	}

	public void setUI(UserInterface ui){
		this.ui = ui;
	}

}