package rendering;

import game.engine.GameObject;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawSquare implements DrawBehaviour {

	@Override
	public void Draw(Object drawMe, GraphicsContext context) {
		GameObject object = (GameObject)drawMe;
		
        Point2D objectPosition = object.getPosition();
        Point2D objectDimensions = object.getDimensions();
        context.setFill(object.getColor());
        context.fillRect(objectPosition.getX(), objectPosition.getY(), objectDimensions.getX(), objectDimensions.getY());
        // debug center calculation
//        context.setFill(Color.MAGENTA);
//        context.fillRect(object.getCenter().getX(), object.getCenter().getY(), 2, 2);
	}
}
