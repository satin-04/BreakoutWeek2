package observer.pattern;

// Observable = Subject in the book
public interface Observable {
	public void registerObserver(Observer o);
	public void unregisterObserver(Observer o);
	public void tick();
}
