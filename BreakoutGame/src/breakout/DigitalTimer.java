package breakout;

import game.engine.TextObject;
import javafx.scene.paint.Color;
import observer.pattern.Observable;
import rendering.DrawText;

public class DigitalTimer extends TextObject {

	private double finalTime;
	
	public DigitalTimer() {
		super();
	}
	
	public DigitalTimer(Observable gameLoop, String font, int fontSize, int locationX, int locationY, Color color) {
		super(gameLoop, font, fontSize, locationX, locationY, color);
		drawBehaviour = new DrawText();
	}

	@Override
	public void update(double timeDelta) {
		finalTime += timeDelta;
		int finalMins = (int) (finalTime/60);
		int finalSecs = (int) (finalTime%60);
		
		label = String.format("Time: %02d:%02d", (finalMins), (finalSecs));		
	}	
}
