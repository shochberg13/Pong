package ponggame.domain;

import java.awt.Color;
import java.awt.Graphics;


public class Paddle {
	// Refers to coordinate of top left corner of the paddle
	private int x; 
	private int y;
	
	private int paddleWidth;
	private int paddleHeight;
	
	public Paddle(){
		this.paddleWidth = 30;
		this.paddleHeight = 150;
	}
	
	// Only Y because paddle will never move in X-direction
	public void move(int dy){
		this.y += dy; 
	}
	
	public void draw(Graphics graphics){
		graphics.setColor(Color.WHITE);
		graphics.fill3DRect(this.x, this.y, this.paddleWidth, this.paddleHeight, true);
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
		
	public int getPaddleHeight(){
		return this.paddleHeight;
	}
	
	public int getPaddleWidth(){
		return this.paddleWidth;
	}

	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
}
