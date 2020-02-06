package ponggame.domain;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	private int x;
	private int y;
	private Direction direction;
	private final int ballDiameter;
	
	
	public Ball(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.ballDiameter = 20;
		this.direction = direction;
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
	
	public String toString(){
		return "{" + this.x + ", " + this.y + ")";
	}
	
}
