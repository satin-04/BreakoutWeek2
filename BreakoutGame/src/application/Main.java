package application;
	
import breakout.*;
import collision.detection.CollisionHandler2D;
import game.engine.GameObject;
import game.engine.TimelineGameLoop;
import input.KeyPolling;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import rendering.Renderer;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	primaryStage.setTitle("P532 Homework1: Breakout");
        
    	Group root = new Group();
    	Scene gameScene = new Scene(root);
    	primaryStage.setScene(gameScene);

    	KeyPolling.getInstance().pollScene(gameScene);
    	
    	BorderPane gameFrame = new BorderPane();
    	
    	Canvas gameCanvas = new Canvas(800, 600);
    	gameCanvas.setStyle("border-color: RED");
    	
    	Canvas menuCanvas = new Canvas(200, 600);
    	Button pauseButton = new Button();
    	Button startButton = new Button();
    	Button undoButton = new Button();
    	Button replayButton = new Button();
    	
    	menuCanvas.setLayoutX(1100);
    	//root.getChildren().add(gameFrame); 
    	gameFrame.getChildren().addAll(gameCanvas, menuCanvas);
    	root.getChildren().add(gameFrame);
    	
    	Renderer renderer = Renderer.getInstance();
    	renderer.setCanvas(gameCanvas);

        CollisionHandler2D collisionHandler = CollisionHandler2D.getInstance();
    	collisionHandler.setCanvas(gameCanvas);
        
        TimelineGameLoop gameLoop = TimelineGameLoop.getInstance();
        gameLoop.setGameScene(gameScene);
        gameLoop.setGameCanvas(gameCanvas);
        
        GameObject ball = new Ball(gameLoop, 395, 500, 10, 10, Color.BLUE);
        renderer.addDrawable(ball);
        collisionHandler.addGameObject(ball);
        
        // This is sloppy we know =/
        BuildBrickLayer1(gameLoop, renderer, collisionHandler);
        BuildBrickLayer2(gameLoop, renderer, collisionHandler);
        BuildBrickLayer3(gameLoop, renderer, collisionHandler);
        BuildBrickLayer4(gameLoop, renderer, collisionHandler);
        
        GameObject paddle = new Paddle(gameLoop, 350, 550, 100, 10, Color.GREEN);
        renderer.addDrawable(paddle);
        collisionHandler.addGameObject(paddle);
        
        DigitalTimer clock = new DigitalTimer(gameLoop, "Verdana", 14, 700, 20, Color.BLACK);
        renderer.addDrawable(clock);
        
        gameLoop.startGameLoop();
        primaryStage.show();
    }
    
    // This is sloppy we know =/
    private void BuildBrickLayer1(TimelineGameLoop gameLoop, Renderer renderer, CollisionHandler2D collisionHandler) {
        GameObject brick = new Brick(gameLoop, 50, 50, 50, 25, Color.RED);
        renderer.addDrawable(brick);
        collisionHandler.addGameObject(brick);
        brick = new Brick(gameLoop, 250, 50, 50, 25, Color.RED);
        renderer.addDrawable(brick);
        collisionHandler.addGameObject(brick);
        brick = new Brick(gameLoop, 450, 50, 50, 25, Color.RED);
        renderer.addDrawable(brick);
        collisionHandler.addGameObject(brick);
    }
    
    private void BuildBrickLayer2(TimelineGameLoop gameLoop, Renderer renderer, CollisionHandler2D collisionHandler) {
    	GameObject brick = new Brick(gameLoop, 100, 100, 50, 25, Color.RED);
        renderer.addDrawable(brick);
        collisionHandler.addGameObject(brick);
        brick = new Brick(gameLoop, 300, 100, 50, 25, Color.RED);
        renderer.addDrawable(brick);
        collisionHandler.addGameObject(brick);
        brick = new Brick(gameLoop, 500, 100, 50, 25, Color.RED);
        renderer.addDrawable(brick);
        collisionHandler.addGameObject(brick);
    }
    
    private void BuildBrickLayer3(TimelineGameLoop gameLoop, Renderer renderer, CollisionHandler2D collisionHandler) {
        GameObject brick = new Brick(gameLoop, 150, 150, 50, 25, Color.RED);
        renderer.addDrawable(brick);
        collisionHandler.addGameObject(brick);
        brick = new Brick(gameLoop, 350, 150, 50, 25, Color.RED);
        renderer.addDrawable(brick);
        collisionHandler.addGameObject(brick);
        brick = new Brick(gameLoop, 550, 150, 50, 25, Color.RED);
        renderer.addDrawable(brick);
        collisionHandler.addGameObject(brick);
    }
    
    private void BuildBrickLayer4(TimelineGameLoop gameLoop, Renderer renderer, CollisionHandler2D collisionHandler) {
        GameObject brick = new Brick(gameLoop, 200, 200, 50, 25, Color.RED);
        renderer.addDrawable(brick);
        collisionHandler.addGameObject(brick);
        brick = new Brick(gameLoop, 400, 200, 50, 25, Color.RED);
        renderer.addDrawable(brick);
        collisionHandler.addGameObject(brick);
        brick = new Brick(gameLoop, 600, 200, 50, 25, Color.RED);
        renderer.addDrawable(brick);
        collisionHandler.addGameObject(brick);
    }

}