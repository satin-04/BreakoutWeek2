package commands;


import java.util.List;

import rendering.Renderer;
import game.engine.DrawObject;
import javafx.scene.canvas.GraphicsContext;

public class Render implements Command{

	Renderer RENDERER_REF;
	List<DrawObject> drawablesBeforeRender;
	GraphicsContext contextBeforeRender;
	private double timeDelta;
	
	public Render(Renderer RENDERER_REF) {
		this.RENDERER_REF = RENDERER_REF;
		this.drawablesBeforeRender = RENDERER_REF.getDrawables();
		this.contextBeforeRender = RENDERER_REF.getGraphicsContext();
	}
	
	public void execute(double timeDelta) {
		this.timeDelta = timeDelta;
		RENDERER_REF.prepare();
		RENDERER_REF.render();
	}
	
	public void execute() {
		RENDERER_REF.prepare();
		RENDERER_REF.render();
	}


	public void unexecute() {
		RENDERER_REF.setDrawables(drawablesBeforeRender);
		RENDERER_REF.setGraphicsContext(contextBeforeRender);
		RENDERER_REF.prepare();
		RENDERER_REF.render();
	}
}
