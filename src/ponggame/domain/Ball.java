package ponggame.domain;

import java.awt.Color;
import java.awt.Graphics;


public class Ball {
	private int x;
	private int y;
	private Direction direction;
	private final int ballDiameter;
	
	
	public Ball() {
		this.ballDiameter = 20;
	}

	public void move(int dx, int dy){
		this.x += dx;
		this.y += dy;
	}
	
	
	public void draw(Graphics graphics){
		graphics.setColor(Color.GRAY);
		graphics.fillOval(this.x, this.y, this.ballDiameter, this.ballDiameter);
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public Direction getDirection(){
		return this.direction;
	}
	
	public int getBallDiameter(){
		return this.ballDiameter;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setDirection(Direction direction){
		this.direction = direction;
	}
}
