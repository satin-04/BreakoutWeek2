package movement.behaviors;

import javafx.geometry.Point2D;

public interface MoveBehaviour {
	public Point2D move(double timeDelta, Point2D moveDirection, double speed);
}
