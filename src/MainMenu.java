
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class MainMenu extends Canvas {
	private static int i = 0;
	private static final Font TITLE_FONT = new Font("Monospace", 80);
	private static final Font MENU_FONT = new Font("Monospace", 40);
	GraphicsContext gc;
	Instruction ins = new Instruction();

	public MainMenu() {
		super(1280, 620);
		gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFill(Color.WHITE);
		gc.setFont(TITLE_FONT);
		gc.fillText("Escape", 1280 / 2, 620 / 4);
		gc.setFont(MENU_FONT);
		gc.fillText("Enter - Play", 1280 / 2, 620 / 2);
		gc.fillText("I - Instruction", 1280 / 2, 620 / 2 + 100);
		gc.fillText("ESC - Exit", 1280 / 2, 620 / 2 + 200);

		addKeyEventHandler();
	}

	private void addKeyEventHandler() {
		this.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				GameMain.countLife = 3;
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
		if (i == 0) {
			SceneManager.scene.setOnKeyPressed(event -> GameMain.keys.put(event.getCode(), true));
			SceneManager.scene.setOnKeyReleased(event -> {
				GameMain.keys.put(event.getCode(), false);
				GameMain.player.animation.stop();
				i++;
			});
		}
	}
}
