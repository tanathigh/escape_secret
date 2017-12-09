package logic;
import application.GameMain;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Decoration extends Pane {
	ImageView dec = new ImageView(new Image(ClassLoader.getSystemResource("decoration.png").toString()));

	public enum DecType {
		ENTRANCE, FAKE_EXIT, STONE, SKULL, SKULL_LONG, ICE1, ICE2, MUSHROOM, MUSHROOM_LONG;
	}

	public Decoration(DecType decType, int x, int y) {
		dec.setFitWidth(GameMain.BLOCK_SIZE);
		dec.setFitHeight(GameMain.BLOCK_SIZE);
		setTranslateX(x);
		setTranslateY(y);
		switch (decType) {
		// 1
		case ENTRANCE:
			dec.setViewport(new Rectangle2D(0, 0, 50, 50));
			break;
		case FAKE_EXIT:
			dec.setViewport(new Rectangle2D(0, 50, 50, 50));
			break;
		case STONE:
			dec.setViewport(new Rectangle2D(150, 50, 50, 50));
			break;
		case SKULL:
			dec.setViewport(new Rectangle2D(150, 0, 50, 50));
			break;
		case SKULL_LONG:
			dec.setViewport(new Rectangle2D(300, 0, 100, 50));
			dec.setFitWidth(GameMain.BLOCK_SIZE * 2);
			break;
		case ICE1:
			dec.setViewport(new Rectangle2D(150, 0, 50, 50));
			break;
		case ICE2:
			dec.setViewport(new Rectangle2D(250, 0, 50, 50));
			break;
		case MUSHROOM:
			dec.setViewport(new Rectangle2D(200, 50, 50, 50));
			break;
		case MUSHROOM_LONG:
			dec.setViewport(new Rectangle2D(300, 50, 100, 50));
			dec.setFitWidth(GameMain.BLOCK_SIZE * 2);
			break;
		}
		getChildren().add(dec);
		GameMain.decorations.add(this);
		GameMain.gameRoot.getChildren().add(this);
	}
}
