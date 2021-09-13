package commands;

import breakout.Paddle;
import javafx.geometry.Point2D;

public class PaddleMove implements Command{

	private Paddle srcPaddle;
	private Point2D position;
	private double timeDelta;
	public PaddleMove(Paddle srcPaddle) {
		this.srcPaddle = srcPaddle;
	}
	@Override
	public void execute(double timeDelta) {
		// TODO Auto-generated method stub
		this.timeDelta = timeDelta;
		position = srcPaddle.getPosition();
		srcPaddle.performMove(timeDelta);
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		position = srcPaddle.getPosition();
		srcPaddle.performMove(timeDelta);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		srcPaddle.setPosition(position);
	}

}
