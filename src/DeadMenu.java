import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class DeadMenu extends Canvas {
	private static final Font TITLE_FONT = new Font("Monospace", 80);

	GraphicsContext gc;
	Instruction ins = new Instruction();

	public DeadMenu() {
		super(1280,620);
		gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFill(Color.WHITE);
		gc.setFont(TITLE_FONT);
		gc.fillText("Remaing = ", 1280 / 2, 620 / 4);

		addKeyEventHandler();
	}

	private void addKeyEventHandler() {
		this.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				newGame();
			}
			if (event.getCode() == KeyCode.I) {
				SceneManager.gotoSceneOf(ins);
			}
			if (event.getCode() == KeyCode.ESCAPE) {
				Platform.exit();
			}
		});
	}

	private static void newGame() {
		SceneManager.gotoSceneOf(GameMain.appRoot);
		SceneManager.scene.setOnKeyPressed(event -> GameMain.keys.put(event.getCode(), true));
		SceneManager.scene.setOnKeyReleased(event -> {
			GameMain.keys.put(event.getCode(), false);
			GameMain.player.animation.stop();
		});
	}
}
