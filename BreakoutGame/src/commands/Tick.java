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
	
	public Tick(ArrayList<Observer> observers, Renderer RENDERER, CollisionHandler2D COLLISION_HANDLER) {
		currentObservers = observers; 
		RENDERER_REF = RENDERER;
		COLLISION_HANDLER_REF = COLLISION_HANDLER;
		commands = new ArrayList<Command>();
	}
	
	@Override
	public void execute(double timeDelta) {
		// TODO Auto-generated method stub
		Render renderCommand = new Render(RENDERER_REF);
		commands.add(renderCommand);
		CollisionCheckForTick collisionCommand = new CollisionCheckForTick(COLLISION_HANDLER_REF);
		commands.add(collisionCommand);
		
		for (Observer observer : currentObservers) {
			observer.update(timeDelta);
		}
		

		collisionCommand.execute(timeDelta);
		renderCommand.execute(timeDelta);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		for(Command c : commands) {
			c.unexecute();
		}
	}

}
