package rendering;

import javafx.scene.canvas.GraphicsContext;

public interface DrawBehaviour {
	public void Draw(Object drawMe, GraphicsContext context);
}
