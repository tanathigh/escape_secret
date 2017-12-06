import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Switch extends Pane {

	ImageView switchButton = new ImageView(new Image(getClass().getResourceAsStream("switch.png")));

	public enum SwitchType {
		BEFORE, AFTER;
	}

	public Switch(SwitchType switchType, int x, int y) {

		switchButton.setFitWidth(GameMain.BLOCK_SIZE);
		switchButton.setFitHeight(GameMain.BLOCK_SIZE);
		setTranslateX(x);
		setTranslateY(y);

		switch (switchType) {
		case BEFORE:
			switchButton.setViewport(new Rectangle2D(0, 0, 15, 15));
			break;
		case AFTER:
			switchButton.setViewport(new Rectangle2D(0, 0, 15, 15));
			break;
		}

		getChildren().add(switchButton);
		GameMain.switchs.add(this);
		GameMain.gameRoot.getChildren().add(this);
	}
}
