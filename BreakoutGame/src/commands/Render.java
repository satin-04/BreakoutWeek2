package commands;


import java.util.List;

import rendering.Renderer;
import game.engine.DrawObject;

public class Render implements Command{

	Renderer RENDERER_REF;
	List<DrawObject> currentDrawables;
	public Render(Renderer RENDERER_REF) {
		this.RENDERER_REF = RENDERER_REF;
	}
	
	public void execute(double timeDelta) {
		// TODO Auto-generated method stub
		currentDrawables = RENDERER_REF.getDrawables();
		RENDERER_REF.prepare();
		RENDERER_REF.render();
	}

	public void unexecute() {
		// TODO Auto-generated method stub
		RENDERER_REF.setDrawables(currentDrawables);
		RENDERER_REF.prepare();
		RENDERER_REF.render();
	}

}
