package breakout;

import game.engine.DrawObject;
import game.engine.GameObject;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import rendering.DrawBehaviour;
import rendering.DrawButton;
import rendering.DrawSquare;



public class GameButton extends GameObject{
	protected DrawBehaviour drawBehaviour;
	protected Point2D position;
	protected Point2D dimensions;
	protected Button gameButton;
	protected Color myColor;
	
	public GameButton(Button b, double posX, double posY, double length, double width) {
		super();
		gameButton = b;
		position = new Point2D(posX, posY);
		dimensions = new Point2D(length, width);
		drawBehaviour = new DrawButton();
		myColor  = Color.BLACK;
	}
	
	public Point2D getPosition() {
        return position;
    }
	
	public Point2D getDimensions() {
		return dimensions;
	}
	public String getLabel() {
		return gameButton.getText();
	}

	public void performDraw(Object drawMe, GraphicsContext context) {
		drawBehaviour.Draw(drawMe, context);
	}
	
	public Color getColor() {
		return myColor;
	}

	@Override
	public void update(double timeDelta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleScreenCollision(Point2D newPosition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleObjectCollision(GameObject collider, String collisionDirection) {
		// TODO Auto-generated method stub
		
	}

}
