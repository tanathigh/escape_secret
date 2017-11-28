import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;

public class GameMain extends Application {
	public static ArrayList<Block> platforms = new ArrayList<>();
	private HashMap<KeyCode, Boolean> keys = new HashMap<>();

	Image backgroundImg = new Image(getClass().getResourceAsStream("BG.jpg"));

	public static final int BLOCK_SIZE = 45;
	public static final int CHAR_SIZE_X = 58;
	public static final int CHAR_SIZE_Y = 95;
	
	private int state;
	public static final int IDLE = 0;
	public static final int CLIMB = 1;

	public static Pane appRoot = new Pane();
	public static Pane gameRoot = new Pane();

	public Alice player;
	int levelNumber = 0;
	private int levelWidth;

	private void initContent() {
		ImageView background = new ImageView(backgroundImg);
		background.setFitHeight(14 * BLOCK_SIZE);
		background.setFitWidth(212 * BLOCK_SIZE);

		levelWidth = LevelData.levels[levelNumber][0].length() * BLOCK_SIZE;
		for (int i = 0; i < LevelData.levels[levelNumber].length; i++) {
			String line = LevelData.levels[levelNumber][i];
			for (int j = 0; j < line.length(); j++) {
				switch (line.charAt(j)) {
				case '0':
					break;
				case '1':
					Block platformFloor = new Block(Block.BlockType.PLATFORM, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case '2':
					Block brick = new Block(Block.BlockType.BRICK, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case '3':
					Block bonus = new Block(Block.BlockType.BONUS, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case '4':
					Block stone = new Block(Block.BlockType.STONE, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case '5':
					Block PipeTopBlock = new Block(Block.BlockType.PIPE_TOP, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case '6':
					Block PipeBottomBlock = new Block(Block.BlockType.PIPE_BOTTOM, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case '*':
					Block InvisibleBlock = new Block(Block.BlockType.INVISIBLE_BLOCK, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				}
			}

		}
		player = new Alice();
		player.setTranslateX(0);
		player.setTranslateY(400);
		player.translateXProperty().addListener((obs, old, newValue) -> {
			int offset = newValue.intValue();
			if (offset > 640 && offset < levelWidth - 640) {
				gameRoot.setLayoutX(-(offset - 640));
				background.setLayoutX(-(offset - 640));
			}
		});
		gameRoot.getChildren().add(player);
		appRoot.getChildren().addAll(background, gameRoot);
	}

	private void update() {
		if (isPressed(KeyCode.UP) && player.getTranslateY() >= 5) {
			player.jumpPlayer();
		}
		if (isPressed(KeyCode.LEFT) && player.getTranslateX() >= 5) {
			player.setScaleX(-1);
			player.animation.play();
			player.walkX(-5);
		}
		if (isPressed(KeyCode.RIGHT) && player.getTranslateX() + 40 <= levelWidth - 5) {
			player.setScaleX(1);
			player.animation.play();
			player.walkX(5);
		}
		if (player.playerVelocity.getY() < 10) { 
			player.playerVelocity = player.playerVelocity.add(0, 1);
		}
		player.jumpY((int) player.playerVelocity.getY());
	}

	private boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initContent();
		Scene scene = new Scene(appRoot, 1200, 620);
		scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
		scene.setOnKeyReleased(event -> {
			keys.put(event.getCode(), false);
			player.animation.stop();
		});
		primaryStage.setTitle("Escape");
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

	public static void main(String[] args) {
		launch(args);
	}
}
