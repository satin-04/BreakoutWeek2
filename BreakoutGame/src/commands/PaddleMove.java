package commands;

import breakout.Paddle;
import javafx.geometry.Point2D;
import movement.behaviors.MoveBehaviour;

public class PaddleMove implements Command{

	private Paddle srcPaddle;
	private Point2D position;
	private double timeDelta;
	private Point2D moveDirection;
	public PaddleMove(Paddle srcPaddle) {
		this.srcPaddle = srcPaddle;
	}
	@Override
	public void execute(double timeDelta) {
		// TODO Auto-generated method stub
		this.timeDelta = timeDelta;
		position = srcPaddle.getPosition();
		srcPaddle.performMove(timeDelta);
		moveDirection = srcPaddle.getMoveDirection();
	}
	
	@Override
	public void execute() {
		srcPaddle.performMove(moveDirection, timeDelta);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		srcPaddle.setPosition(position);
	}

}
