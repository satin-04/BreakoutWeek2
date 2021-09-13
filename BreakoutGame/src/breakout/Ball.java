package breakout;

import commands.Move;
import commands.Tick;
import game.engine.GameObject;
import game.engine.TimelineGameLoop;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import movement.behaviors.MoveBehaviour;
import movement.behaviors.SimpleMovement;
import observer.pattern.Observable;
import rendering.DrawSquare;

public class Ball extends GameObject {
	
	private final static MoveBehaviour MOVE_BEHAVIOUR = new SimpleMovement();
	private final static int ZERO_BOUND = 0;
	private final static int ONE = 1;
	private final static int FLIP_DIRECTION = -1;
	//private final static int SPEED_INCREMENT = 15;
	private double speed = -200f;
	private Point2D moveDirection = new Point2D(1, -1);

	public Ball() {
		super();
	}
	
	public Ball(Observable gameLoop, int locationX, int locationY, int width, int height, Color color) {
		super(gameLoop, locationX, locationY, width, height, color);
		drawBehaviour = new DrawSquare();
	}
	
	public MoveBehaviour getMoveBehaviour() {
		return MOVE_BEHAVIOUR;
	}

	@Override
	public void update(Tick currentTick) {
		Move move = new Move(this);
		currentTick.addCommand(move);
	}
	
	
	public Point2D getMoveDirection() {
		return moveDirection;
	}
	
	public void setMoveDirection(Point2D md) {
		moveDirection = md;
	}
	public double getSpeed() {
		return this.speed;
	}
	
	public void setVelocity(Point2D v) {
		velocity = v;
	}

	@Override
	public void handleObjectCollision(GameObject collider, String collisionDirection) {
		
//		System.out.println(moveDirection);
		
		double horizontalMoveDirection = moveDirection.getX();
		double verticalMoveDirection = moveDirection.getY();
		
		if (collisionDirection.contains("TOP")) {
			verticalMoveDirection *= FLIP_DIRECTION;
			nextPosition = new Point2D(nextPosition.getX(), (collider.getPosition().getY() - dimensions.getY() - ONE));
		}
		
		if (collisionDirection.contains("BOTTOM") ) {
			verticalMoveDirection *= FLIP_DIRECTION;
			nextPosition = new Point2D(nextPosition.getX(), (collider.getPosition().getY() - collider.getDimensions().getY() - ONE));
		}
		
		if (collisionDirection.contains("RIGHT")) {
			horizontalMoveDirection *= FLIP_DIRECTION;
			nextPosition = new Point2D(collider.getPosition().getX() + collider.getDimensions().getX() - ONE, nextPosition.getY());
		}
		
		if(collisionDirection.contains("LEFT")) {
			horizontalMoveDirection *= FLIP_DIRECTION;
			nextPosition = new Point2D(collider.getPosition().getX() - dimensions.getX() - ONE, nextPosition.getY());
		}
		
//		System.out.println(moveDirection);
		
		moveDirection = new Point2D(horizontalMoveDirection, verticalMoveDirection);
	}
	
	@Override
	public void handleScreenCollision(Point2D newPosition) {
		previousPosition = position;
		position = newPosition;
		bounceCheck();
	}
	
	private void bounceCheck() {
		Canvas gameCanvas = TimelineGameLoop.getInstance().getGameCanvas();

		double horizontalDirection = moveDirection.getX();
		double verticalDirection = moveDirection.getY();
		
		if (position.getX() <= ZERO_BOUND || position.getX() >= (gameCanvas.getWidth() - dimensions.getX())) {
			horizontalDirection *= FLIP_DIRECTION;
			increaseSpeed();
		}
		
		if (position.getY() <= ZERO_BOUND || position.getY() >= (gameCanvas.getHeight() - dimensions.getY())) {
			verticalDirection *= FLIP_DIRECTION;
			increaseSpeed();
		}
			
		moveDirection = new Point2D(horizontalDirection, verticalDirection);
	}
	
	private void increaseSpeed() {
		// speed += SPEED_INCREMENT;
	}
}