package commands;

import collision.detection.CollisionHandler2D;
import game.engine.GameObject;
import java.util.List;

public class CollisionCheckForTick implements Command{

	CollisionHandler2D COLLISION_HANDLER_REF;
	private List<GameObject> currentStateOfObjects;
	public CollisionCheckForTick(CollisionHandler2D COLLISION_HANDLER_REF) {
		this.COLLISION_HANDLER_REF = COLLISION_HANDLER_REF;
	}
	
	public void execute(double timeDelta) {
		// TODO Auto-generated method stub
		currentStateOfObjects = COLLISION_HANDLER_REF.getGameObjects();
		COLLISION_HANDLER_REF.processCollisions();
	}

	public void unexecute() {
		// TODO Auto-generated method stub
		COLLISION_HANDLER_REF.setGameObjects(currentStateOfObjects);
	}
	

}
