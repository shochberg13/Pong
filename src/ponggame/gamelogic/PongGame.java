package ponggame.gamelogic;

import java.util.Random;

import ponggame.gui.UserInterface;
import ponggame.domain.Ball;
import ponggame.domain.Direction;
import ponggame.domain.Paddle;

public class PongGame  {
	private int boardHeight;
	private int boardWidth;
	private int spacing;
	private Paddle paddleLeft;
	private Paddle paddleRight;
	private Ball ball;
	private boolean gameContinues;
	private UserInterface ui;
	private int playerLeftScore;
	private int playerRightScore;
	private int pointsToWinTheGame;
	
	public PongGame(int boardHeight, int boardWidth, int pointsToWinTheGame){
		this.boardHeight = boardHeight;
		this.boardWidth = boardWidth;
		
		this.spacing = 20; // Space between paddle and edge of board
		
		this.paddleLeft = new Paddle();
		this.paddleRight = new Paddle();
		this.ball = new Ball();
		setInitialPositions();
		
		this.playerLeftScore = 0;
		this.playerRightScore = 0;
		this.pointsToWinTheGame = pointsToWinTheGame;
	}
	
	public void start(){
		// Initialize Board and wait
		setInitialPositions();
		this.ui.getFrame().repaint();
		delayMilliseconds(2000);
		
		// A counter to help speed up the ball as the game progresses
		int delayCounter = 10; 
		
		// Each loop the ball moves one increment (~1 pixel)
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
				if (this.ball.getX() <= 0) playerRightScore ++;
				if (this.ball.getX() >= this.boardWidth) playerLeftScore ++;
				
				gameContinues = false;
			}
			
			// Allows the ball to speed up logarithmically
			delayCounter += 3;
			delayMilliseconds(200 / (int) Math.log(delayCounter));
		}
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
	}
	
	public boolean ballHitsPaddle(){
		
		// If it hits the right paddle
		if (ball.getDirection() == Direction.NORTHEAST || ball.getDirection() == Direction.SOUTHEAST){
			if (ball.getX() + ball.getBallDiameter() == (boardWidth - (paddleRight.getPaddleWidth() + spacing))){
				if (ball.getY() + ball.getBallDiameter() > paddleRight.getY() && 
						ball.getY() < paddleRight.getY() + paddleRight.getPaddleHeight()){
					return true;
				}
			}
		}
		// If it hits the left paddle
		if(ball.getDirection() == Direction.NORTHWEST || ball.getDirection() == Direction.SOUTHWEST){
			if (ball.getX()  == this.spacing + paddleLeft.getPaddleWidth()){
				if (ball.getY() + ball.getBallDiameter() > paddleLeft.getY() && 
						ball.getY() < paddleLeft.getY() + paddleLeft.getPaddleHeight()){
					return true;
				}
			}
		}
		return false;
	
	}
	
	public boolean ballHitsNorthOrSouthWall(){
		if (this.ball.getY() + this.ball.getBallDiameter() >= this.boardHeight || this.ball.getY() <= 0){
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
	
	public void delayMilliseconds(int x){
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void setInitialPositions(){
		this.paddleLeft.setX(spacing);
		this.paddleLeft.setY((boardHeight / 2) - (this.paddleLeft.getPaddleHeight() / 2));
		
		this.paddleRight.setX(boardWidth - (spacing + this.paddleRight.getPaddleWidth()));
		this.paddleRight.setY((boardHeight/2) - (this.paddleRight.getPaddleHeight() / 2));
		
		this.ball.setX(boardWidth / 2);
		this.ball.setY(boardHeight/2);
		this.ball.setDirection(randomInitialDirection());
		
		this.gameContinues = true;
	}
	
	public Direction randomInitialDirection(){
		Random random = new Random();
		int dir = random.nextInt(4);
		switch (dir) {
		case 0:
			return Direction.NORTHEAST; 
		case 1:
			return Direction.NORTHWEST; 
		case 2:
			return Direction.SOUTHEAST;
		case 3:
			return Direction.SOUTHWEST;
		default:
			System.out.println("Error with random direction");
			return Direction.NORTHEAST;
		}
	}
	
	public boolean gameIsOver(){
		return this.playerLeftScore == pointsToWinTheGame || this.playerRightScore == pointsToWinTheGame;
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
	
	public int getPlayerLeftScore(){
		return this.playerLeftScore;
	}
	
	public int getPlayerRightScore(){
		return this.playerRightScore;
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