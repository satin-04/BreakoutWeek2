package commands;

public interface Command {
	public void execute(double timeDelta);
	
	public void unexecute();

	public void reExecute();
}
