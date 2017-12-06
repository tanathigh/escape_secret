import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Insect extends Monster {
	public Point2D playerVelocity = new Point2D(0, 0);

	public Insect() {
		super(4, 4, 0, 0, 32, 40);
		imageView = new ImageView(new Image(getClass().getResourceAsStream("insect.png")));
	}
}
