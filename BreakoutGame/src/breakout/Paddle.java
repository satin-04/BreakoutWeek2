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

	public void performMove(double timeDelta) {
		moveDirection = captureMoveDirection();
		velocity = MOVE_BEHAVIOUR.move(timeDelta, moveDirection, speed);
		nextPosition = position.add(velocity);
	}
	
	//Overloaded method to support redo on paddle movement
	public void performMove(Point2D moveDirection, double timeDelta) {
		velocity = MOVE_BEHAVIOUR.move(timeDelta, moveDirection, speed);
		nextPosition = position.add(velocity);
	}
	
	private Point2D captureMoveDirection() {
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
	
	public MoveBehaviour getMoveBehaviour() {
		return this.MOVE_BEHAVIOUR;
	}
	
	@Override
	public void update(Tick gameTick) {
		PaddleMove move = new PaddleMove(this);
		gameTick.addCommand(move);
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
