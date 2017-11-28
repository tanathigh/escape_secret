import javafx.scene.layout.Pane;

public abstract class Entity extends Pane implements Movable{
	
	public Animation animation;
	
	@Override
	public abstract void walkX(int value);
}
