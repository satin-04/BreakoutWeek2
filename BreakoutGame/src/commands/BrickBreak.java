package commands;

import breakout.Brick;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class BrickBreak implements Command{

	private Brick srcBrick;
	private Point2D position;
	private Point2D dimensions;
	private double timeDelta;
	public BrickBreak(Brick srcBrick, Point2D position, Point2D dimensions) {
		this.srcBrick = srcBrick;
		this.position = position;
		this.dimensions = dimensions;
	}
	@Override
	public void execute(double timeDelta) {
		// TODO Auto-generated method stub
		this.timeDelta = timeDelta;
		srcBrick.setPosition(new Point2D(-2, -2));
		srcBrick.setDimensions(-2, -2);
		srcBrick.setColor(Color.TRANSPARENT);
	}
	
	@Override
	public void execute() {
		srcBrick.setPosition(new Point2D(-2, -2));
		srcBrick.setDimensions(-2, -2);
		srcBrick.setColor(Color.TRANSPARENT);
	}

	@Override
	public void unexecute() {
		srcBrick.setPosition(position);
		srcBrick.setDimensions((int)dimensions.getX(), (int)dimensions.getY());
		srcBrick.setColor(Color.RED);
	}

}
