package commands;

import breakout.Ball;
import javafx.geometry.Point2D;

public class Move implements Command{
	double previousX;
	double previousY;
	Ball ball;
	
	public Move (Ball ball) {
		this.ball = ball;
		Point2D position = ball.getPosition();
		previousX = position.getX();
		previousY = position.getY();	
	}
	
	public Point2D getPositionBeforeCommand() {
		return new Point2D(previousX, previousY);
	}
	
	public double getX() {
		return previousX;
	}
	
	public double getY() {
		return previousY;
	}
	
	public void execute(double timeDelta) {
		//Makes call to move
		ball.update(timeDelta);
	}
	
	public void unexecute() {
		//sets the ball's position back
		ball.setPosition(getPositionBeforeCommand());
	}
}
