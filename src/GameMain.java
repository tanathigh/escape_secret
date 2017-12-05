import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.HashMap;

public class GameMain extends Application {
	public static ArrayList<Block> platforms = new ArrayList<>();
	public static ArrayList<Trap> killers = new ArrayList<>();
	public static ArrayList<Monster> monsters = new ArrayList<>();
	private HashMap<KeyCode, Boolean> keys = new HashMap<>();
	private int stage = 1;

	Image backgroundImg = new Image(getClass().getResourceAsStream("BG.jpg"));
	ImageView background = new ImageView(backgroundImg);
	LevelData levelData = new LevelData();

	public static final int BLOCK_SIZE = 45;
	public static final int CHAR_SIZE_X = 65;
	public static final int CHAR_SIZE_Y = 65;

	public static final int IDLE = 0;
	public static final int CLIMB = 1;

	public static Pane appRoot = new Pane();
	public static Pane gameRoot = new Pane();

	public static Tom player = new Tom();
	public Minotaur minotaur = new Minotaur();

	Canvas canvas;
	GraphicsContext gc;

	private void initContent(String[] level) {
		background.setFitHeight(14 * BLOCK_SIZE);
		background.setFitWidth(70 * BLOCK_SIZE);
		levelData.setBlock(stage);
		player.run();
		player.setTranslateX(0);
		player.setTranslateY(400);
		player.translateXProperty().addListener((obs, old, newValue) -> {
			int offset = newValue.intValue();
			if (offset > 640 && offset < levelData.levelWidth - 640) {
				gameRoot.setLayoutX(-(offset - 640));
				background.setLayoutX(-(offset - 640));
			}
		});
		// ************** Create the AI here. ****************
		minotaur = new Minotaur();
		minotaur.run();
		minotaur.setTranslateX(1500);
		minotaur.setTranslateY(480);
		// *****************************************************
		canvas = new Canvas(3392, 620);
		gc = canvas.getGraphicsContext2D();
		/*
		 * gc.fillRect(0, 0, 3392, 620); // gc.clearRect(player.getTranslateX()-50,
		 * player.getTranslateY()-100, 200, // 200); clearCircle(player.getTranslateX(),
		 * player.getTranslateY(), 200, gc);
		 */
		gameRoot.getChildren().add(minotaur);
		gameRoot.getChildren().add(player);
		gameRoot.getChildren().add(canvas);
		appRoot.getChildren().addAll(background, gameRoot);
	}

	private void update() {
		minotaur.animation.play();
		minotaur.walkX(1 * minotaur.getDirection());

		if (isPressed(KeyCode.UP) && player.getTranslateY() >= 5) {
			player.jumpPlayer();
		}
		if (isPressed(KeyCode.LEFT) && player.getTranslateX() >= 5) {
			player.setScaleX(-1);
			player.animation.play();
			player.walkX(-5);
		}
		if (isPressed(KeyCode.RIGHT) && player.getTranslateX() + 40 <= levelData.levelWidth - 5) {
			player.setScaleX(1);
			player.animation.play();
			player.walkX(5);
		}
		if (player.playerVelocity.getY() < 10) {
			player.playerVelocity = player.playerVelocity.add(0, 1);
		}
		player.jumpY((int) player.playerVelocity.getY());
		/*
		 * gc.clearRect(0, 0, 3392, 620); gc.fillRect(0, 0, 3392, 620);
		 */
		// gc.clearRect(player.getTranslateX()-50, player.getTranslateY()-100, 200,
		// 200);
		// clearCircle(player.getTranslateX() + player.getWidth(),
		// player.getTranslateY() + player.getHeight(), 200, gc);

		if (player.isDead == true) {
			player.setTranslateX(0);
			player.setTranslateY(400);
			gameRoot.setLayoutX(0);
			background.setLayoutX(0);
			player.isDead = false;
			/*
			 * clearCircle(player.getTranslateX() + player.getWidth(),
			 * player.getTranslateY() + player.getHeight(), 200, gc);
			 */
		}
	}

	private boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		String[] level = LevelMap.LEVEL1;
		initContent(level);
		Scene scene = new Scene(appRoot, 1280, 620);
		scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
		scene.setOnKeyReleased(event -> {
			keys.put(event.getCode(), false);
			player.animation.stop();
		});
		primaryStage.setTitle("ESCAPE");
		primaryStage.setScene(scene);
		primaryStage.show();
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
			}
		};
		timer.start();
	}

	public void clearCircle(double x, double y, int r, GraphicsContext gc) {
		for (int rad = 0; rad < 90; rad += 1) {
			double dx = x - r * Math.cos(rad);
			double dy = y - r * Math.sin(rad);
			double w = r * 2 * Math.cos(rad);
			double h = r * 2 * Math.sin(rad);
			gc.clearRect(dx, dy, w, h);

		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}