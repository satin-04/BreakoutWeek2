package game.engine;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import observer.pattern.Observable;
import observer.pattern.Observer;

public abstract class TextObject extends DrawObject implements Observer  {
	
	protected String label;
    protected Point2D position;
	protected String font;
	protected int fontSize;
    protected Color myColor;

	public TextObject() {
		super();
		label = "";
        position = new Point2D(0,0);
        font = "Verdana";
        fontSize = 14;
        myColor = Color.MAGENTA;
	}
	
	public TextObject(Observable gameLoop, String font, int fontSize, int locationX, int locationY, Color color) {
		super(); 
    	gameLoop.registerObserver(this);
    	this.font = font;
    	this.fontSize = fontSize;
        position = new Point2D(locationX, locationY);
        myColor = color;
        label = "";
    }
	
    public Point2D getPosition() {
        return position;
    }
    
    public void setPosition(float x, float y) {
        position = new Point2D(x, y);
    }
    
	public Color getColor() {
		return myColor;
	}

	public void setColor(Color newColor) {
		myColor = newColor;		
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
}
