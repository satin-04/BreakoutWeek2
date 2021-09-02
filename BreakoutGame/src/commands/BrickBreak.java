package commands;

import breakout.Brick;
import javafx.geometry.Point2D;

public class BrickBreak implements Command{
	
	double previousX;
	double previousY;
	Brick b;
	
	public BrickBreak(Brick b) {
		Point2D point = b.getPosition();
		previousX = point.getX();
		previousY = point.getY();
		this.b = b;
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(double timeDelta) {
		// TODO Auto-generated method stub
		b.update(timeDelta);
	}

}
