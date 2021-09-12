package rendering;

import breakout.GameButton;
import game.engine.GameObject;
import game.engine.TextObject;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class DrawButton implements DrawBehaviour {

	@Override
	public void Draw(Object drawMe, GraphicsContext context) {
		// TODO Auto-generated method stub
		GameButton object = (GameButton)drawMe;
		
        Point2D objectPosition = object.getPosition();
        Point2D objectDimensions = object.getDimensions();
        context.setFill(object.getColor());
        context.fillRect(objectPosition.getX(), objectPosition.getY(), objectDimensions.getX(), objectDimensions.getY());
        context.setFill(object.getColor());
        context.fillText(object.getLabel(), objectPosition.getX(), objectPosition.getY());
	}

}
