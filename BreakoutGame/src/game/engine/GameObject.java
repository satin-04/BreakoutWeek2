package game.engine;

import collision.detection.ObjectCollider;
import collision.detection.ScreenCollider;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import observer.pattern.Observable;
import observer.pattern.Observer;

public abstract class GameObject extends DrawObject implements Observer, ScreenCollider, ObjectCollider {
	
	protected Point2D previousPosition;
    protected Point2D position;
    protected Point2D nextPosition;
    protected Point2D dimensions;
    protected Point2D velocity;
    protected Point2D moveDirection;
    protected Color myColor;
    
    public GameObject() {
		super();
		previousPosition = new Point2D(0, 0);
        position = new Point2D(0, 0);
        nextPosition = new Point2D(0, 0);
        velocity = new Point2D(0, 0);
        dimensions = new Point2D(0, 0);
        myColor = Color.MAGENTA;
    }
    
    public GameObject(Observable gameLoop, int locationX, int locationY, int width, int height, Color color) {
		super();
    	gameLoop.registerObserver(this);
		previousPosition = new Point2D(locationX, locationY);
        position = new Point2D(locationX, locationY);
        nextPosition = new Point2D(locationX, locationY);
        velocity = new Point2D(0, 0);
        dimensions = new Point2D(width, height);
        myColor = color;
    }
            
    public Point2D getPosition() {
        return position;
    }
    
    public void setPosition(float x, float y) {
        position = new Point2D(x, y);
    }
    
    public void setPosition(Point2D newPosition) {
        position = newPosition;
    }
    
    public Point2D getNextPosition() {
        return nextPosition;
    }
    
    public Point2D getPreviousPosition() {
    	return previousPosition;
    }
    
    public Point2D getDimensions() {
        return dimensions;
    }
    
    public void setDimensions(int width, int height) {
        dimensions = new Point2D(width, height);
    }
    
    public Point2D getVelocity() {
        return velocity;
    } 
    
    public Point2D getCenter(Point2D position) {
        return new Point2D(position.getX() + dimensions.getX() / 2, position.getY() + dimensions.getY() / 2);
    }
    
    public Point2D getUpperLeft(Point2D position) {
        return new Point2D(getCenter(position).getX() - dimensions.getX() / 2, getCenter(position).getY() - dimensions.getY() / 2);
    }
    
    public Point2D getLowerLeft(Point2D position) {
        return new Point2D(getCenter(position).getX() - dimensions.getX() / 2, getCenter(position).getY() + dimensions.getY() / 2);
    }
    
    public Point2D getUpperRight(Point2D position) {
        return new Point2D(getCenter(position).getX() + dimensions.getX() / 2, getCenter(position).getY() - dimensions.getY() / 2);
    }
    
    public Point2D getLowerRight(Point2D position) {
        return new Point2D(getCenter(position).getX() + dimensions.getX() / 2, getCenter(position).getY() + dimensions.getY() / 2);
    }

	public Color getColor() {
		return myColor;
	}

	public void setColor(Color newColor) {
		myColor = newColor;		
	}
}
