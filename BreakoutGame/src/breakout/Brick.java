package breakout;

import commands.BrickBreak;
import commands.Tick;
import game.engine.GameObject;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import observer.pattern.Observable;
import rendering.DrawSquare;;

public class Brick extends GameObject {
	
	private Point2D startingPosition;
	private Point2D startingDimensions;
		
	public Brick() {
		super();
	}
	
	public Brick(Observable gameLoop, int locationX, int locationY, int width, int height, Color color) {
		super(gameLoop, locationX, locationY, width, height, color);		
		drawBehaviour = new DrawSquare();
	}
	
	@Override
	public void update(Tick gameTick) {
		// nothing
		if(dimensions.equals(new Point2D(-1, -1)) && position.equals(new Point2D(-1, -1))) {
			BrickBreak breakCommand = new BrickBreak(this, startingPosition, startingDimensions);
			gameTick.addCommand(breakCommand);
			dimensions = new Point2D(-2, -2);
			position = new Point2D(-2, -2);
		}
	}

	@Override
	public void handleScreenCollision(Point2D newPosition) {
		// nothing
	}

	@Override
	public void handleObjectCollision(GameObject collider, String collisionDirection) {
		myColor = Color.TRANSPARENT;
		startingPosition = position;
		startingDimensions = dimensions;
		dimensions = new Point2D(-1, -1);
		position = new Point2D(-1, -1);
	}
}
