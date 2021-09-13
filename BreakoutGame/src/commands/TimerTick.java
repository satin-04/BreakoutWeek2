package commands;

import breakout.DigitalTimer;

public class TimerTick implements Command{
	
	DigitalTimer sourceTimer;
	String displayBeforeTick;
	double finalTimeBeforeTick;
	private double timeDelta;
	public TimerTick(DigitalTimer source) {
		sourceTimer = source;
		displayBeforeTick = source.getLabel();
		finalTimeBeforeTick = source.getFinalTime();
	}

	@Override
	public void execute(double timeDelta) {
		// TODO Auto-generated method stub
		this.timeDelta = timeDelta;
		sourceTimer.setFinalTime(sourceTimer.getFinalTime() + timeDelta);
		int finalMins = (int) (sourceTimer.getFinalTime()/60);
		int finalSecs = (int) (sourceTimer.getFinalTime()%60);
		sourceTimer.setLabel(String.format("Time: %02d:%02d", (finalMins), (finalSecs)));	
	}
	
	@Override
	public void execute() {
		sourceTimer.setFinalTime(sourceTimer.getFinalTime() + timeDelta);
		int finalMins = (int) (sourceTimer.getFinalTime()/60);
		int finalSecs = (int) (sourceTimer.getFinalTime()%60);
		sourceTimer.setLabel(String.format("Time: %02d:%02d", (finalMins), (finalSecs)));	
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		sourceTimer.setFinalTime(finalTimeBeforeTick);
		sourceTimer.setLabel(displayBeforeTick);		
	}

}
