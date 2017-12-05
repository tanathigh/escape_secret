
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public final class SceneManager {

	private static Stage primaryStage;
	private static Canvas mainMenuCanvas = new MainMenu();
	private static Scene mainMenuScene = new Scene(new Pane(mainMenuCanvas));
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

	public static void gotoSceneOf(Canvas canvas) {
		Scene scene = new Scene(new Pane(canvas));
		primaryStage.setScene(scene);
		
		canvas.requestFocus();
	}
	
	public static void gotoSceneOf(Pane pane) {
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		
		pane.requestFocus();
	}
}
