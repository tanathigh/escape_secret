package logic;
import javafx.scene.layout.Pane;

public abstract class Entity extends Pane implements Movable {

	protected int count;
	protected int columns;
	protected int offsetX;
	protected int offsetY;
	private int width;
	private int height;

	public Animation animation;

	@Override
	public abstract void walkX(int value);

	public int getEntityWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getEntityHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
