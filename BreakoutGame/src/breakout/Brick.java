package breakout;

import game.engine.GameObject;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import observer.pattern.Observable;
import rendering.DrawSquare;;

public class Brick extends GameObject {
		
	public Brick() {
		super();
	}
	
	public Brick(Observable gameLoop, int locationX, int locationY, int width, int height, Color color) {
		super(gameLoop, locationX, locationY, width, height, color);		
		drawBehaviour = new DrawSquare();
	}
	
	@Override
	public void update(double timeDelta) {
		// nothing
	}

	@Override
	public void handleScreenCollision(Point2D newPosition) {
		// nothing
	}

	@Override
	public void handleObjectCollision(GameObject collider, String collisionDirection) {
		myColor = Color.TRANSPARENT;
		dimensions = new Point2D(-1, -1);
		position = new Point2D(-1, -1);
	}
}
