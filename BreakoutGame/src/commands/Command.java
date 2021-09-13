package commands;

public interface Command {
	public void execute(double timeDelta);
	
	public void unexecute();

	void execute();
}
