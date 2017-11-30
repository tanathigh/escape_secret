import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Entity extends Pane implements Movable {
	
	protected int count;
	protected int columns;
	protected int offsetX;
	protected int offsetY;
	protected int width;
	protected int height;

	public Animation animation;

	@Override
	public abstract void walkX(int value);
}
