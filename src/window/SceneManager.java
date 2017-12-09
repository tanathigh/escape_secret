package window;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.Instruction;
import ui.MainMenu;

public final class SceneManager {
	private static Stage primaryStage;
	private static Canvas mainMenuCanvas = new MainMenu();
	private static Canvas instruction = new Instruction();
	private static Scene mainMenuScene = new Scene(new Pane(mainMenuCanvas));
	private static Scene instructionScene = new Scene(new Pane(instruction));
	public static Scene scene;
	public static final int SCENE_WIDTH = 800;
	public static final int SCENE_HEIGHT = 600;

	public static void initialize(Stage stage) {
		primaryStage = stage;
		primaryStage.show();
	}

	public static void gotoMainMenu() {
		primaryStage.setScene(mainMenuScene);
		mainMenuCanvas.requestFocus();
	}
	
	public static void gotoInstruction() {
		primaryStage.setScene(instructionScene);
		instruction.requestFocus();
	}

	public static void gotoSceneOf(Canvas canvas) {
		scene = new Scene(new Pane(canvas), 1280, 620);
		primaryStage.setScene(scene);
		canvas.requestFocus();
	}

	public static void gotoSceneOf(Pane pane) {
		if (scene == null) {
			scene = new Scene(pane, 1280, 620);
			pane.requestFocus();
		}
		primaryStage.setScene(scene);
	}
}
