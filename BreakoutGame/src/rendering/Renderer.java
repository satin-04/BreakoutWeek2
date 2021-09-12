package rendering;

import java.util.ArrayList;
import java.util.List;

import game.engine.DrawObject;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Renderer {
    
    // Singleton Pattern
    private static Renderer uniqueInstance;
    
    private Canvas canvas;
    private GraphicsContext context;
    private List<DrawObject> drawables = new ArrayList<>();

    // Singleton Pattern
    private Renderer() {    
    }
    
    public static Renderer getInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new Renderer();
        }
        return uniqueInstance;
    }
	
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
        this.context = canvas.getGraphicsContext2D();
    }
    
    public void addDrawable(DrawObject object) {
		// Prevent double registration
		if(!drawables.contains(object)) {
			drawables.add(object);
		}
    }
    
    public void removeDrawable(DrawObject object) {
		// Ensure DrawBehavior is already registered
		int objectIndex = drawables.indexOf(object);
		if (objectIndex >= 0) {
			drawables.remove(object);
		}
    }
    
    public List<DrawObject> getDrawables() {
    	return drawables;
    }
    
    public void setDrawables(List<DrawObject> drawables) {
    	this.drawables = drawables;
    }
    
    public void clearDrawables() {
    	drawables.clear();
    }

    // Clears screen so we can draw current frame
    public void prepare() {
        context.setFill(Color.GREY);
        context.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
    }
    
    // Draws current frame
    public void render() {
        context.save();
        
        for (DrawObject object : drawables) {
        	object.performDraw(object, context);
        }
    }
}
