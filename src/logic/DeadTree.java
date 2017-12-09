package logic;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DeadTree extends Monster {
	public Point2D playerVelocity = new Point2D(0, 0);

	public DeadTree() {
		super(6, 6, 0, 0, 45, 43);
		imageView = new ImageView(new Image(ClassLoader.getSystemResource("tree.png").toString()));
	}
}
