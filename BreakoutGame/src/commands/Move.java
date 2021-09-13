package commands;

import javafx.geometry.Point2D;

import breakout.Ball;
import movement.behaviors.MoveBehaviour;

public class Move implements Command{

	private Ball gameBall;
	private Point2D position;
	private Point2D velocity;
	public Move (Ball gameBall) {
		this.gameBall = gameBall;
		position = gameBall.getPosition();
		velocity = gameBall.getVelocity();
	}
	@Override
	public void execute(double timeDelta) {
		// TODO Auto-generated method stub
		Point2D velocity = gameBall.getVelocity();
		MoveBehaviour behavior = gameBall.getMoveBehaviour();
		velocity = behavior.move(timeDelta, gameBall.getMoveDirection(), gameBall.getSpeed());
		gameBall.setNextPosition(gameBall.getPosition().add(velocity));
	}

	@Override
	public void unexecute() {
		//gameBall.setPosition(position);
		//gameBall.setVelocity(velocity);
		//gameBall.setNextPosition(position);
	}

}
