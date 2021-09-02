package commands;

import java.util.Queue;

public class Tick implements Command{

	Queue<Command> commands;
	
	public Tick() {
		
	}
	
	@Override
	public void execute(double timeDelta) {
		// TODO Auto-generated method stub
		System.out.println("Code here");
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		for(Command c : commands) {
			c.unexecute();
		}
	}

}
