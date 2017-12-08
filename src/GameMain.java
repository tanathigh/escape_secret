import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;

public class GameMain extends Application {
	public static ArrayList<Block> platforms = new ArrayList<>();
	public static ArrayList<Trap> killers = new ArrayList<>();
	public static ArrayList<Exit> exits = new ArrayList<>();
	public static ArrayList<Decoration> decorations = new ArrayList<>();
	static HashMap<KeyCode, Boolean> keys = new HashMap<>();

	public static int countLife = 3;
	private static int stage = 1;
	public static boolean playerMove = true;
	public static boolean nextDoorIsOpen = false;

	Image backgroundImg = new Image(getClass().getResourceAsStream("BG1.jpg"));
	ImageView background = new ImageView(backgroundImg);
	LevelData levelData = new LevelData();
	AudioClip song = new AudioClip(ClassLoader.getSystemResource("DarkCave.mp3").toString());
	AudioClip scream = new AudioClip(ClassLoader.getSystemResource("ScreamSound.mp3").toString());

	public static final int BLOCK_SIZE = 45;
	public static final int CHAR_SIZE_X = 38;
	public static final int CHAR_SIZE_Y = 55;

	public static final int IDLE = 0;
	public static final int CLIMB = 1;

	public static Pane appRoot = new Pane();
	public static Pane gameRoot = new Pane();

	public static Tom player;
	public Minotaur minotaur1, minotaur2;
	public Insect insect1, insect2, insect3, insect4;
	public DeadTree tree1;

	Canvas canvas;
	GraphicsContext gc;
	DeadText dead;

	private void initContent(int stage) {
		background.setFitHeight(14 * BLOCK_SIZE);
		background.setFitWidth(70 * BLOCK_SIZE);
		levelData.setBlock(stage);
		player = new Tom();
		player.run();
		player.setTranslateX(100);
		player.setTranslateY(450);
		gameRoot.setLayoutX(0);
		background.setLayoutX(0);
		player.translateXProperty().addListener((obs, old, newValue) -> {
			int offset = newValue.intValue();
			if (offset > 640 && offset < levelData.levelWidth - 640) {
				gameRoot.setLayoutX(-(offset - 640));
				background.setLayoutX(-(offset - 640));
			}
		});
		// ************** Create the AI here. ****************
		if (stage == 2) {
			minotaur1 = new Minotaur();
			minotaur1.run();
			minotaur1.setTranslateX(1500);
			minotaur1.setTranslateY(484);
			minotaur2 = new Minotaur();
			minotaur2.run();
			minotaur2.setTranslateX(900);
			minotaur2.setTranslateY(484);
			insect2 = new Insect();
			insect2.run();
			insect2.setTranslateX(1200);
			insect2.setTranslateY(175);
			tree1 = new DeadTree();
			tree1.run();
			tree1.setTranslateX(1000);
			tree1.setTranslateY(225);
			gameRoot.getChildren().addAll(minotaur1, minotaur2, insect2, tree1);
		}
		if (stage == 3) {
			insect1 = new Insect();
			insect1.run();
			insect1.setTranslateX(100);
			insect1.setTranslateY(100);
			insect2 = new Insect();
			insect2.run();
			insect2.setTranslateX(1200);
			insect2.setTranslateY(175);
			insect3 = new Insect();
			insect3.run();
			insect3.setTranslateX(750);
			insect3.setTranslateY(100);
			insect4 = new Insect();
			insect4.run();
			insect4.setTranslateX(2000);
			insect4.setTranslateY(100);
			tree1 = new DeadTree();
			tree1.run();
			tree1.setTranslateX(1100);
			tree1.setTranslateY(539);
			gameRoot.getChildren().addAll(insect1, insect2, insect3, insect4, tree1);
		}
		// *****************************************************
		canvas = new Canvas(3392, 620);
		gc = canvas.getGraphicsContext2D();

		// ================circlr==================
		gc.fillRect(0, 0, 3392, 620);
		clearCircle(player.getTranslateX() + player.width / 2, player.getTranslateY() + player.height / 2, 200, gc);
		// =======================================

		gameRoot.getChildren().add(player);
		gameRoot.getChildren().add(canvas);
		appRoot.getChildren().addAll(background, gameRoot);
	}

	private void update() {
		if (countLife == 0 || stage > 3) {
			stage = 1;
			countLife = 3;
			platforms.clear();
			killers.clear();
			exits.clear();
			gameRoot.getChildren().clear();
			appRoot.getChildren().clear();
			keys.clear();
			initContent(stage);
			SceneManager.gotoMainMenu();
		}
		if (countLife > 0) {
			if (nextDoorIsOpen == true) {
				nextDoorIsOpen = false;
				stage++;
				platforms.clear();
				killers.clear();
				exits.clear();
				gameRoot.getChildren().clear();
				appRoot.getChildren().clear();
				initContent(stage);
			}
			if (stage == 2) {
				Thread bot = new Thread(new Runnable() {
					public void run() {
						Platform.runLater(new Runnable() {
							public void run() {
								minotaur1.animation.play();
								minotaur1.walkX(1 * minotaur1.getDirection());
								minotaur2.animation.play();
								minotaur2.walkX(1 * minotaur2.getDirection());
								insect2.animation.play();
								insect2.walkX(3 * insect2.getDirection());
								tree1.animation.play();
								tree1.walkX(2 * tree1.getDirection());
							}
						});
					}
				});
				bot.start();
			}
			if (stage == 3) {
				Thread bot = new Thread(new Runnable() {
					public void run() {
						Platform.runLater(new Runnable() {
							public void run() {
								insect1.animation.play();
								insect1.walkX(3 * insect1.getDirection());
								insect2.animation.play();
								insect2.walkX(3 * insect2.getDirection());
								insect3.animation.play();
								insect3.walkX(3 * insect3.getDirection());
								insect4.animation.play();
								insect4.walkX(3 * insect4.getDirection());
								tree1.animation.play();
								tree1.walkX(2 * tree1.getDirection());
							}
						});
					}
				});
				bot.start();
			}
			if (playerMove == true) {
				Thread mainPlayer = new Thread(new Runnable() {
					public void run() {
						Platform.runLater(new Runnable() {
							public void run() {
								if (isPressed(KeyCode.UP) && player.getTranslateY() >= 5) {
									player.jumpPlayer();
								}
								if (isPressed(KeyCode.LEFT) && player.getTranslateX() >= 5) {
									player.setScaleX(-1);
									player.animation.play();
									player.walkX(-5);
								}
								if (isPressed(KeyCode.RIGHT)
										&& player.getTranslateX() + 40 <= levelData.levelWidth - 5) {
									player.setScaleX(1);
									player.animation.play();
									player.walkX(5);
								}
								if (player.playerVelocity.getY() < 10) {
									player.playerVelocity = player.playerVelocity.add(0, 1);
								}
								player.jumpY((int) player.playerVelocity.getY());

								if (player.isDead == true) {
									scream.play();
									playerMove = false;
									player.isDead = false;
									countLife--;
									dead = new DeadText();
									gameRoot.getChildren().add(dead);
									dead.requestFocus();
									player.setTranslateX(100);
									player.setTranslateY(450);
									gameRoot.setLayoutX(0);
									background.setLayoutX(0);
								}
							}
						});

					}
				});
				mainPlayer.start();
			}
		}
		/*
		 * if (isPressed(KeyCode.UP) && player.getTranslateY() >= 5) {
		 * player.jumpPlayer(); } if (isPressed(KeyCode.LEFT) && player.getTranslateX()
		 * >= 5) { player.setScaleX(-1); player.animation.play(); player.walkX(-5); } if
		 * (isPressed(KeyCode.RIGHT) && player.getTranslateX() + 40 <=
		 * levelData.levelWidth - 5) { player.setScaleX(1); player.animation.play();
		 * player.walkX(5); } if (player.playerVelocity.getY() < 10) {
		 * player.playerVelocity = player.playerVelocity.add(0, 1); } player.jumpY((int)
		 * player.playerVelocity.getY());
		 */

		// ===========================cirlce================
		gc.clearRect(0, 0, 3392, 620);
		gc.fillRect(0, 0, 3392, 620);

		clearCircle(player.getTranslateX() + player.getWidth() / 2, player.getTranslateY() + player.getHeight() / 2,
				200, gc);
		// =================================================

		/*
		 * if (player.isDead == true) { player.setTranslateX(0);
		 * player.setTranslateY(400); gameRoot.setLayoutX(0); background.setLayoutX(0);
		 * player.isDead = false; }
		 */

		/*
		 * clearCircle(player.getTranslateX() + player.getWidth(),
		 * player.getTranslateY() + player.getHeight(), 200, gc);
		 */
	}

	private boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initContent(stage);
		song.setCycleCount(99);
		song.play();
		/*
		 * Scene scene = new Scene(appRoot, 1280, 620); scene.setOnKeyPressed(event ->
		 * keys.put(event.getCode(), true)); scene.setOnKeyReleased(event -> {
		 * keys.put(event.getCode(), false); player.animation.stop(); });
		 */
		SceneManager.initialize(primaryStage);
		SceneManager.gotoMainMenu();
		primaryStage.setTitle("ESCAPE");
		// primaryStage.setScene(scene);
		// primaryStage.show();
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