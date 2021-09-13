package commands;

import javafx.geometry.Point2D;

import breakout.Ball;
import movement.behaviors.MoveBehaviour;

public class Move implements Command{

	private Ball gameBall;
	private Point2D position;
	private Point2D velocity;
	private Point2D moveDirection;
	private double timeDelta;
	public Move (Ball gameBall) {
		this.gameBall = gameBall;
		position = gameBall.getPosition();
		velocity = gameBall.getVelocity();
		moveDirection = gameBall.getMoveDirection();
	}
	
	@Override
	public void execute(double timeDelta) {
		this.timeDelta = timeDelta;
		Point2D velocity = gameBall.getVelocity();
		MoveBehaviour behavior = gameBall.getMoveBehaviour();
		velocity = behavior.move(timeDelta, gameBall.getMoveDirection(), gameBall.getSpeed());
		gameBall.setNextPosition(gameBall.getPosition().add(velocity));
	}
	
	
	public void reExecute() {
		System.out.println("Executing ball redo");
		Point2D velocity = gameBall.getVelocity();
		MoveBehaviour behavior = gameBall.getMoveBehaviour();
		velocity = behavior.move(timeDelta, gameBall.getMoveDirection(), gameBall.getSpeed());
		gameBall.setNextPosition(gameBall.getPosition().add(velocity));
	}

	@Override
	public void unexecute() {
		gameBall.setPosition(position);
		gameBall.setVelocity(velocity);
		gameBall.setNextPosition(position);
		gameBall.setMoveDirection(moveDirection);
	}

}
