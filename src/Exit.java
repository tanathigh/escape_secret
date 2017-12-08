import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Exit extends Pane {
	ImageView exit = new ImageView(new Image(getClass().getResourceAsStream("exit.jpg")));

	public enum ExitType {
		EXIT_UP, EXIT_OUT
	}

	public Exit(ExitType exitType, int x, int y) {
		exit.setFitWidth(GameMain.BLOCK_SIZE);
		exit.setFitHeight(GameMain.BLOCK_SIZE);
		setTranslateX(x);
		setTranslateY(y);

		switch (exitType) {
		case EXIT_UP:
			exit.setViewport(new Rectangle2D(0, 0, 28, 28));
			break;

		case EXIT_OUT:
			exit.setViewport(new Rectangle2D(28, 0, 28, 28));
			break;
		}

		getChildren().add(exit);
		GameMain.exits.add(this);
		GameMain.gameRoot.getChildren().add(this);
	}
}
