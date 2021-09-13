package observer.pattern;

import commands.Tick;

public interface Observer {
	public void update(Tick currentTick);
}
