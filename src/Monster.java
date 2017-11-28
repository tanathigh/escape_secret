import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public abstract class Monster extends Entity {
	
	public Point2D playerVelocity = new Point2D(0, 0);
	Image CharacterImg = new Image(getClass().getResourceAsStream("Tom/walk.png"));
	ImageView imageView = new ImageView(CharacterImg);
	// จำนวนเฟรม
	int count = 4;
	int columns = 4;
	int offsetX = 0;
	int offsetY = 0;
	int width = 65;
	int height = 65;

	public Monster() {
		// ขนาดตัวละคร
		imageView.setFitHeight(65);
		imageView.setFitWidth(65);
		imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
		animation = new Animation(this.imageView, Duration.millis(800), count, columns, offsetX, offsetY, width,
				height);
		getChildren().addAll(this.imageView);
	}

	@Override
	public void walkX(int value) {
		boolean movingRight = value > 0;
		for (int i = 0; i < Math.abs(value); i++) {
			for (Trap killer : GameMain.killers) {
				if (getBoundsInParent().intersects(killer.getBoundsInParent())) {
				}
			}
			for (Node platform : GameMain.platforms) {
				// ถ้ากรอบมัน intersect กัน
				if (this.getBoundsInParent().intersects(platform.getBoundsInParent())) {
					if (movingRight) {
						if (this.getTranslateX() + GameMain.CHAR_SIZE_X == platform.getTranslateX()) {
							// ไม่ให้เดินทะลุ
							this.setTranslateX(this.getTranslateX() - 1);
							return;
						}
					} else {
						if (this.getTranslateX() == platform.getTranslateX() + GameMain.BLOCK_SIZE) {
							// ไม่ให้เดินทะลุ
							this.setTranslateX(this.getTranslateX() + 1);
							return;
						}
					}
				}
			}
			this.setTranslateX(this.getTranslateX() + (movingRight ? 1 : -1));
		}
	}
}
