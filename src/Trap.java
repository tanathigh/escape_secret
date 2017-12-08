import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Trap extends Pane {
	Image TrapImg = new Image(getClass().getResourceAsStream("floor_trap.png"));
	ImageView trap;

	public Trap(int x, int y) {
		trap = new ImageView(TrapImg);
		trap.setFitWidth(GameMain.BLOCK_SIZE);
		trap.setFitHeight(GameMain.BLOCK_SIZE);
		setTranslateX(x);
		setTranslateY(y);
		trap.setViewport(new Rectangle2D(0, 0, 15, 14));
		getChildren().add(trap);
		GameMain.killers.add(this);
		GameMain.gameRoot.getChildren().add(this);
	}
}
