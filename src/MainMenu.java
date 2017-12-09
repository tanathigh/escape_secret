import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class MainMenu extends Canvas {
	private static final Font TITLE_FONT = new Font("Monospace", 120);
	private static final Font MENU_FONT = new Font("Monospace", 40);
	Image bg;
	GraphicsContext gc;
	Instruction ins = new Instruction();

	public MainMenu() {
		super(1280, 620);
		gc = this.getGraphicsContext2D();
		gc.setTextAlign(TextAlignment.CENTER);
		bg = new Image("menuBG.png");
		gc.drawImage(bg, 0, 0);
		gc.setFill(Color.WHITE);
		gc.setFont(TITLE_FONT);
		gc.fillText("Escape", 1280 / 2, 620 / 4);
		gc.setFont(MENU_FONT);
		gc.setTextAlign(TextAlignment.LEFT);
		gc.fillText("Enter - Play", 1280 * 3 / 4, 620 / 2);
		gc.fillText("I - Instruction", 1280 * 3 / 4, 620 / 2 + 100);
		gc.fillText("ESC - Exit", 1280 * 3 / 4, 620 / 2 + 200);

		addKeyEventHandler();
	}

	private void addKeyEventHandler() {
		this.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				GameMain.countLife = 3;
				newGame();
			}
			if (event.getCode() == KeyCode.I) {
				SceneManager.gotoInstruction();
			}
			if (event.getCode() == KeyCode.ESCAPE) {
				Platform.exit();
			}
		});
	}

	private static void newGame() {
		SceneManager.gotoSceneOf(GameMain.appRoot);
		GameMain.playerMove = true;
		SceneManager.scene.setOnKeyPressed(event -> GameMain.keys.put(event.getCode(), true));
		SceneManager.scene.setOnKeyReleased(event -> {
			GameMain.keys.put(event.getCode(), false);
			GameMain.player.animation.stop();
		});
	}
}
