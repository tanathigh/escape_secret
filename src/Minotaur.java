import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Minotaur extends Monster {
	public Point2D playerVelocity = new Point2D(0, 0);

	public Minotaur() {
		super(7, 7, 0, 0, 100, 100);
		imageView = new ImageView(new Image(getClass().getResourceAsStream("minotaur.png")));
	}
}
