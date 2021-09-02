package rendering;

import game.engine.TextObject;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class DrawText implements DrawBehaviour {
	
	@Override
	public void Draw(Object drawMe, GraphicsContext context) {
		TextObject object = (TextObject)drawMe;
		
        Point2D objectPosition = object.getPosition();
        context.setFill(object.getColor());
        context.fillText(object.getLabel(), objectPosition.getX(), objectPosition.getY());
	}
}
