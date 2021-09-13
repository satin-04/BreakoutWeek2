package commands;

import collision.detection.CollisionHandler2D;
import game.engine.GameObject;
import java.util.List;

public class CollisionCheckForTick implements Command{

	CollisionHandler2D COLLISION_HANDLER_REF;
	private List<GameObject> currentStateOfObjects;
	private double timeDelta;
	public CollisionCheckForTick(CollisionHandler2D COLLISION_HANDLER_REF) {
		this.COLLISION_HANDLER_REF = COLLISION_HANDLER_REF;
		currentStateOfObjects = COLLISION_HANDLER_REF.getGameObjects();
	}
	
	public void execute(double timeDelta) {
		this.timeDelta = timeDelta;
		COLLISION_HANDLER_REF.processCollisions();
	}
	
	public void reExecute() {
		COLLISION_HANDLER_REF.processCollisions();
	}

	public void unexecute() {
		// TODO Auto-generated method stub
		COLLISION_HANDLER_REF.setGameObjects(currentStateOfObjects);
	}
	

}
