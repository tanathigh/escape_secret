import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Alice extends Entity{
	public Point2D playerVelocity = new Point2D(0, 0);
	protected boolean canJump = true;
	Image CharacterImg = new Image(getClass().getResourceAsStream("AlicePic.png"));
	ImageView imageView = new ImageView(CharacterImg);
	// �ӹǹ���
	int count = 6;
	int columns = 6;
	int offsetX = 0;
	int offsetY = 0;
	int width = 58;
	int height = 95;

	public Alice() {
		// ��Ҵ����Ф�
		imageView.setFitHeight(95);
		imageView.setFitWidth(59);
		imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
		animation = new Animation(this.imageView, Duration.millis(800), count, columns, offsetX, offsetY, width,
				height);
		getChildren().addAll(this.imageView);
	}

	@Override
	public void walkX(int value) {
		boolean movingRight = value > 0;
		for (int i = 0; i < Math.abs(value); i++) {
			for (Node platform : GameMain.platforms) {
				if (this.getBoundsInParent().intersects(platform.getBoundsInParent())) {
					if (movingRight) {
						if (this.getTranslateX() + GameMain.CHAR_SIZE_X == platform.getTranslateX()) {
							this.setTranslateX(this.getTranslateX() - 1);
							return;
						}
					} else {
						if (this.getTranslateX() == platform.getTranslateX() + GameMain.BLOCK_SIZE) {
							this.setTranslateX(this.getTranslateX() + 1);
							return;
						}
					}
				}
			}
			this.setTranslateX(this.getTranslateX() + (movingRight ? 1 : -1));
		}
	}

	public void jumpY(int value) {
		boolean movingDown = value > 0;
		for (int i = 0; i < Math.abs(value); i++) {
			for (Block platform : GameMain.platforms) {
				if (getBoundsInParent().intersects(platform.getBoundsInParent())) {
					if (movingDown) {
						if (this.getTranslateY() + GameMain.CHAR_SIZE_Y == platform.getTranslateY()) {
							this.setTranslateY(this.getTranslateY() - 1);
							canJump = true;
							return;
						}
					} else {
						if (this.getTranslateY() == platform.getTranslateY() + GameMain.BLOCK_SIZE) {
							this.setTranslateY(this.getTranslateY() + 5);
							playerVelocity = new Point2D(0, 10);
							return;
						}
					}
				}
			}
			this.setTranslateY(this.getTranslateY() + (movingDown ? 1 : -1));
			if (this.getTranslateY() > 640) {
				this.setTranslateX(0);
				this.setTranslateY(400);
				GameMain.gameRoot.setLayoutX(0);
			}
		}
	}

	public void jumpPlayer() {
		if (canJump) {
			// �����٧��á��ⴴ
			playerVelocity = playerVelocity.add(0, -25);
			canJump = false;
		}
	}

}