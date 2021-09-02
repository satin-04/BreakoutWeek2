package collision.detection;

import game.engine.GameObject;

public interface ObjectCollider {

	public void handleObjectCollision(GameObject collider, String collisionDirection);
}
