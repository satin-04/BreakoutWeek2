package commands;

import java.util.ArrayList;
import java.util.Queue;

import collision.detection.CollisionHandler2D;
import observer.pattern.Observer;
import rendering.Renderer;

public class Tick implements Command{

	ArrayList<Command> commands;
	ArrayList<Observer> currentObservers;
	Renderer RENDERER_REF;
	CollisionHandler2D COLLISION_HANDLER_REF;
	double timeDelta;
	
	public Tick(ArrayList<Observer> observers, Renderer RENDERER, CollisionHandler2D COLLISION_HANDLER) {
		currentObservers = observers; 
		RENDERER_REF = RENDERER;
		COLLISION_HANDLER_REF = COLLISION_HANDLER;
		commands = new ArrayList<Command>();
	}
	
	@Override
	public void execute(double timeDelta) {
		this.timeDelta = timeDelta;

		CollisionCheckForTick collisionCommand = new CollisionCheckForTick(COLLISION_HANDLER_REF);
		commands.add(collisionCommand);
		Render renderCommand = new Render(RENDERER_REF);
		commands.add(renderCommand);
		
		for (Observer observer : currentObservers) {
			observer.update(this);
		}
		
		for(Command c: commands) {
			c.execute(timeDelta);
		}
	}
	
	public void reExecute() {
		for(Command c: commands) {
			c.reExecute();
		}
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		for(Command c : commands) {
			c.unexecute();
		}
	}
	
	public void addCommand(Command c) {
		commands.add(c);
	}
	
	public double getTimeDelta() {
		return timeDelta;
	}

}
