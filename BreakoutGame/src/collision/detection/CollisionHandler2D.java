package collision.detection;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import breakout.Ball;
import game.engine.GameObject;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;

public class CollisionHandler2D {
	
    // Singleton Pattern
    private static CollisionHandler2D uniqueInstance;
    
    private List<GameObject> gameObjects = new ArrayList<>();
    private Hashtable<GameObject, GameObject> collidedObjects = new Hashtable<GameObject, GameObject>();
    private Canvas gameCanvas;
	private final static int ZERO_BOUND = 0;
	private final static String LEFT = "LEFT";
	private final static String RIGHT = "RIGHT";
	private final static String TOP = "TOP";
	private final static String BOTTOM = "BOTTOM";

    private CollisionHandler2D() { 
    }
    
    public static CollisionHandler2D getInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new CollisionHandler2D();
        }
        return uniqueInstance;
    }
    
    public void setCanvas(Canvas gameCanvas) {
    	this.gameCanvas = gameCanvas;
    }

    public void addGameObject(GameObject object) {
		// Prevent double registration
		if(!gameObjects.contains(object)) {
			gameObjects.add(object);
		}
    }
    
    public void removeGameObject(GameObject object) {
		// Ensure DrawBehavior is already registered
		int objectIndex = gameObjects.indexOf(object);
		if (objectIndex >= 0) {
			gameObjects.remove(object);
		}
    }
    
    public void clearGameObject() {
    	gameObjects.clear();
    }
    
    public void setGameObjects(List<GameObject> objects) {
    	this.gameObjects = objects;
    }
    
    public List<GameObject> getGameObjects() {
    	return gameObjects;
    }
    
    public void processCollisions() {
		for (GameObject object1 : gameObjects) {
			for (GameObject object2 : gameObjects) {
				// Skip processing the same two objects
				if(object1 == object2) {
					continue;
				}
				checkObjectCollision(object1, object2);
			}
			checkScreenBoundsCollision(object1);
		}
		// Reset collided tracker per tick
		collidedObjects.clear();
    }

	private void checkObjectCollision(GameObject object1, GameObject object2) {
		Point2D object1Position = object1.getPosition();
		Point2D object2Position = object2.getPosition();
		
		// Basically if there is a collision between two Squares
		if(object1.getUpperRight(object1Position).getX() >= object2.getUpperLeft(object2Position).getX() &&
				object1.getUpperLeft(object1Position).getX() <= object2.getUpperRight(object2Position).getX() &&
				object1.getLowerLeft(object1Position).getY() >= object2.getUpperLeft(object2Position).getY() &&
				object1.getUpperLeft(object1Position).getY() <= object2.getLowerLeft(object2Position).getY()) 
		{
			// This seems to be a HACK
			// Not sure how we can fix this issue.
			if (object1 instanceof Ball) {
				handleObjectCollision(object1, object2);				
			}
			if (object2 instanceof Ball) {
				handleObjectCollision(object2, object1);
			}
		}
	}
    
    public void handleObjectCollision(GameObject object1, GameObject object2) {
    	// Break if we already dealt with this collision
    	// Due to the nature of the double loop it is possible to detect a collision twice in one tick()
    	if((collidedObjects.containsKey(object1) && collidedObjects.get(object1) == object2) ||
    			(collidedObjects.containsKey(object2) && collidedObjects.get(object2) == object1))
    	{
    		return;
    	} else {
        	collidedObjects.put(object1, object2);
        	String collisionDirection = determineCollisionDirection(object1, object2);
        	object1.handleObjectCollision(object2, collisionDirection);
        	object2.handleObjectCollision(object1, collisionDirection);
    	}
    }
    
    private String determineCollisionDirection(GameObject object1, GameObject object2) {
    	Point2D object1PreviousPosition = object1.getPreviousPosition();
    	Point2D object1Position = object1.getNextPosition();
    	Point2D object1Dimensions = object1.getDimensions();
    	Point2D object2Position = object2.getPosition();
    	Point2D object2Dimensions = object2.getDimensions();
    	    	
    	if ((object1PreviousPosition.getX() + object1Dimensions.getX()) < object2Position.getX() && 
    			(object1Position.getX() + object1Dimensions.getX()) >= object2Position.getX()) 
    	{
//        	System.out.println("Collision Direction : " + LEFT);
        	return LEFT;
    	}
    	
    	if (object1PreviousPosition.getX() >= (object2Position.getX() + object2Dimensions.getX()) && 
    			object1Position.getX() < (object2Position.getX() + object2Dimensions.getX()))
    	{
//        	System.out.println("Collision Direction : " + RIGHT);
    		return RIGHT;
    	}
    	
    	if ((object1PreviousPosition.getY() + object1Dimensions.getY()) < object2Position.getY() && 
    			(object1Position.getY() + object1Dimensions.getY()) >= object2Position.getY())
    	{
//        	System.out.println("Collision Direction : " + TOP);
    		return TOP;
    	}
    	
    	if (object1PreviousPosition.getY() >= (object2Position.getY() + object2Dimensions.getY()) && 
    			object1Position.getY() < (object2Position.getY() + object2Dimensions.getY()))
    	{
//        	System.out.println("Collision Direction : " + BOTTOM);
    		return BOTTOM;
    	}
    	    	
    	return "ERROR";
    }
    
//	private boolean collidedFromLeft(Object otherObj)
//	{
//	    return oldBoxRight < otherObj.Left && // was not colliding
//	           boxRight >= otherObj.Left;
//	}
	
//	private boolean collidedFromRight(Object otherObj)
//	{
//	    return oldBoxLeft >= otherObj.Right && // was not colliding
//	           boxLeft < otherObj.Right;
//	}

//	private boolean collidedFromTop(Object otherObj)
//	{
//	    return oldBoxBottom < otherObj.Top && // was not colliding
//	           boxBottom >= otherObj.Top;
//	}

//	private boolean collidedFromBottom(Object otherObj)
//	{
//	    return oldBoxTop >= otherObj.Bottom && // was not colliding
//	           boxTop < otherObj.Bottom;
//	}
	
    private void checkScreenBoundsCollision(GameObject object) {
    	Point2D nextPosition = object.getNextPosition();
    	Point2D dimensions = object.getDimensions();
		double positionX = nextPosition.getX();
		double positionY = nextPosition.getY();
		Point2D velocity = object.getVelocity();
		
		// Horizontal Scene Check
		// if moving left and beyond pixel 0 - dont move
		if (velocity.getX() <= 0 && nextPosition.getX() < ZERO_BOUND) {
			positionX = ZERO_BOUND;
		}
		// if moving right and beyond scene width - dont move 
		else if (velocity.getX() >= 0 && (nextPosition.getX() + dimensions.getX()) >= gameCanvas.getWidth()) { 
			positionX = gameCanvas.getWidth() - dimensions.getX();
		} 
		
		// Vertical Scene Check
		// if moving up and beyound pixel 0 - dont move
		if (velocity.getY() <= 0 && nextPosition.getY() < ZERO_BOUND) {
			positionY = ZERO_BOUND;
		}
		// if moving down and beyond scene height - dont move 
		else if (velocity.getY() >= 0 && (nextPosition.getY() + dimensions.getY()) >= gameCanvas.getHeight()) { 
			positionY = gameCanvas.getHeight() - dimensions.getY();
		} 

		object.handleScreenCollision(new Point2D(positionX, positionY));
    }

}
