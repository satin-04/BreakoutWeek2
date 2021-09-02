package game.engine;

import javafx.scene.canvas.GraphicsContext;
import rendering.DrawBehaviour;

public abstract class DrawObject {
	
	protected DrawBehaviour drawBehaviour;
	
	public DrawObject() {
	}
	
	public DrawObject(DrawBehaviour drawBehavior) {
		this.drawBehaviour = drawBehavior;
	}
		
	public void performDraw(Object drawMe, GraphicsContext context) {
		drawBehaviour.Draw(drawMe, context);
	}
}
