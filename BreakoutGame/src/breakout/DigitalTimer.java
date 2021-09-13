package breakout;

import commands.Tick;
import commands.TimerTick;
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
	public void update(Tick currentTick) {
		TimerTick timerUpdate = new TimerTick(this);
		currentTick.addCommand(timerUpdate);
	}	
	
	public void setFinalTime(double time) {
		finalTime = time;
	}
	
	public double getFinalTime() {
		return finalTime;
	}
	
	public void setLabel(String s) {
		label = s;
	}
}
