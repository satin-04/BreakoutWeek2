package commands;

import breakout.Paddle;
import javafx.geometry.Point2D;
import movement.behaviors.MoveBehaviour;

public class PaddleMove implements Command{

	private Paddle srcPaddle;
	private Point2D position;
	private double storedTimeDelta;
	private Point2D moveDirection;
	public PaddleMove(Paddle srcPaddle) {
		this.srcPaddle = srcPaddle;
	}
	@Override
	public void execute(double timeDelta) {
		this.storedTimeDelta = timeDelta;
		position = srcPaddle.getPosition();
		moveDirection = srcPaddle.captureMoveDirection();
		srcPaddle.setVelocity(srcPaddle.getMoveBehaviour().move(timeDelta, moveDirection, srcPaddle.getSpeed()));
		srcPaddle.setNextPosition(srcPaddle.getPosition().add(srcPaddle.getVelocity()));
	}
	
	@Override
	public void reExecute() {
		srcPaddle.setVelocity(srcPaddle.getMoveBehaviour().move(storedTimeDelta, moveDirection, srcPaddle.getSpeed()));
		srcPaddle.setNextPosition(position.add(srcPaddle.getVelocity()));
	}

	@Override
	public void unexecute() {
		srcPaddle.setPosition(position);
	}

}
