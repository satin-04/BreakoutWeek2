package game.engine;

import rendering.Renderer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import application.Main;
import collision.detection.CollisionHandler2D;
import commands.Command;
import commands.Tick;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.util.Duration;
import observer.pattern.Observable;
import observer.pattern.Observer;

public class TimelineGameLoop implements Observable {

	// Observer pattern vars
	private ArrayList<Observer> observers;
	// Game engine logic vars
	private Timeline gameLoop;
	private KeyFrame keyFrame;
	// Attempt to achieve 60 FPS
	private final static float FRAMES_PER_SECOND = 0.0165f;
	private double totalTime = 0;
	private double previousTotalTime = 0;
	private double timeDelta = 0;
    private double startNanoTime = System.currentTimeMillis();
    private boolean paused = false;
    private final static Renderer RENDERER = Renderer.getInstance();
    private final static CollisionHandler2D COLLISION_HANDLER = CollisionHandler2D.getInstance();
    private Deque<Tick> ticks;

    // Singleton Pattern
    private static TimelineGameLoop uniqueInstance;
    private static Scene gameScene;
    private static Canvas gameCanvas;
    
    private TimelineGameLoop() {  
		observers = new ArrayList<Observer>();
		ticks = new ArrayDeque<Tick>();
		// Set up timeline as a 60 fps ticker
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		
		EventHandler<ActionEvent> tickEvent = new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent actionEvent) {
				previousTotalTime = totalTime;
				totalTime = (System.currentTimeMillis() - startNanoTime) / 1000.0; 
				timeDelta = totalTime - previousTotalTime;
//		        System.out.println("Elapsed Time: " + totalTime);
//		        System.out.println("Delta Time: " + timeDelta);
				tick();
				//gameTick.execute(timeDelta);
			}
		};
		keyFrame = new KeyFrame(Duration.seconds(FRAMES_PER_SECOND), tickEvent);
		gameLoop.getKeyFrames().add(keyFrame);
    }
    
    public static TimelineGameLoop getInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new TimelineGameLoop();
        }
        return uniqueInstance;
    }
    
	public void startGameLoop() {
		gameLoop.play();
	}
	
	public void setGameScene(Scene gameScene) {
		TimelineGameLoop.gameScene = gameScene;
	}
	
	public Scene getGameScene() {
		return gameScene;
	}
	
	public void setGameCanvas(Canvas gameCanvas) {
		TimelineGameLoop.gameCanvas = gameCanvas;
	}
	
	public Canvas getGameCanvas() {
		return gameCanvas;
	}
	
	public double getTimeDelta() {
		return timeDelta;
	}
	
	@Override
	public void registerObserver(Observer observer) {
		// Prevent double registration
		if(!observers.contains(observer)) {
			observers.add(observer);
		}
	}

	@Override
	public void unregisterObserver(Observer observer) {
		// Ensure observer is already registered
		int observerIndex = observers.indexOf(observer);
		if (observerIndex >= 0) {
			observers.remove(observerIndex);
		}
	}
	
	//sets the flow of the game state to 0
	//Handles the pause button functionality
	public void pause() {
		timeDelta = 0;
		gameLoop.pause();
		paused = true;
	}
	
	//Sets timedelta running again, unpausing the game
	public void unpause() {
		paused = false;
		previousTotalTime = totalTime;
		totalTime = (System.currentTimeMillis() - startNanoTime) / 1000.0; 
		timeDelta = totalTime - previousTotalTime;
		gameLoop.play();
	}
	
	public boolean getPause() {
		return paused;
	}
	
	//Undoes the latest tick
	public void undo() {
		if(paused) {
			for(int i = 0; i < 30; i++) {
				try {
				Command tickToUndo = ticks.removeLast();
				tickToUndo.unexecute();
				}
				catch(Exception ex) {
					i = 30;
					System.out.println("No Command to Undo");
				}
			}
		}
		else {
			System.out.println("Pause the game before you undo");
		}
		
	}
	
	public void restart() {
		if(paused) {
			Command undoTick;
			for(int i = ticks.size(); i>0; i--) {
				undoTick = ticks.removeLast();
				undoTick.unexecute();
			}
		}
		else {
			System.out.println("Pause the game before you restart");
		}
	}
	
	//Executes each tick in the existing stack one by one.
	public void replay() {
		if(paused) {
			Tick undoTick;
			Deque<Tick> replayCommands = new ArrayDeque<Tick>();
			//Puts the game back to starting position by undoing all ticks
			for(int i = ticks.size(); i>0; i--) {
				undoTick = ticks.removeLast();
				undoTick.unexecute();
				replayCommands.add(undoTick);
			}
			//ticks = replayCommands;
			
			final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		    executorService.scheduleAtFixedRate(new Runnable() {
		        @Override
		        public void run() {
		        	try {
		           Tick redoTick = replayCommands.removeFirst();
		            redoTick.reExecute();
		            if(replayCommands.size() == 0) {
				    	executorService.shutdown();
				    }
		        	}
		        	catch(Exception ex) {
		        		System.out.println("Data structure empty");
		        	}
		        }
		    }, 0, 50, TimeUnit.MILLISECONDS);
		    
	
		}
		else {
			System.out.println("Pause the game before you replay");
		}
		
	}

	/*
	 * Main Game Loop!
	 * Clear Screen
	 * Move Objects
	 * Detect Collision
	 * Adjust movement if necessary
	 * Draw
	 */
	@Override
	public void tick() {
		System.out.println("New Tick");
		Tick gameTick = new Tick(observers, RENDERER, COLLISION_HANDLER);
		ticks.addLast(gameTick);
		gameTick.execute(timeDelta);
	}	
}
