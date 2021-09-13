package breakout;

import commands.PaddleMove;
import commands.Tick;
import game.engine.GameObject;
import input.KeyPolling;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import movement.behaviors.MoveBehaviour;
import movement.behaviors.SimpleMovement;
import observer.pattern.Observable;
import rendering.DrawSquare;

public class Paddle extends GameObject {
	
	private final static MoveBehaviour MOVE_BEHAVIOUR = new SimpleMovement();
	private final static double speed = 550f;
	
	public Paddle() {
		super();
	}
	
	public Paddle(Observable gameLoop, int locationX, int locationY, int width, int height, Color color) {
		super(gameLoop, locationX, locationY, width, height, color);		
		drawBehaviour = new DrawSquare();
	}

	
	
	public Point2D captureMoveDirection() {
		if (KeyPolling.getInstance().isDown(KeyCode.D)) 
	    {
	    	return new Point2D(1, 0);
	    } 
	    else if (KeyPolling.getInstance().isDown(KeyCode.A)) 
	    {
	    	return new Point2D(-1, 0);
	    }
		
		return new Point2D(0,0);
	}
	
	public static MoveBehaviour getMoveBehaviour() {
		return MOVE_BEHAVIOUR;
	}
	
	public static double getSpeed() {
		return speed;
	}
	
	public void setVelocity(Point2D velocity) {
		this.velocity = velocity;
	}
	
	@Override
	public void update(Tick gameTick) {
		if(gameTick.getTimeDelta() != 0) {
			PaddleMove move = new PaddleMove(this);
			gameTick.addCommand(move);
		}
	}

	@Override
	public void handleScreenCollision(Point2D newPosition) {
		position = newPosition;
	}

	@Override
	public void handleObjectCollision(GameObject collider, String collisionDirection) {
		// Shouldn't have to do anything.
	}
}
