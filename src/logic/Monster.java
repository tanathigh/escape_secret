package logic;
import application.GameMain;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public abstract class Monster extends Entity {

	public Point2D playerVelocity = new Point2D(0, 0);
	protected int direction = 1;
	ImageView imageView;

	public Monster(int count, int columns, int offsetX, int offsetY, int width, int height) {
		this.count = count;
		this.columns = columns;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.setWidth(width);
		this.setHeight(height);
	}

	public void run() {
		imageView.setFitHeight(getHeight());
		imageView.setFitWidth(getWidth());
		imageView.setViewport(new Rectangle2D(offsetX, offsetY, getWidth(), getHeight()));
		animation = new Animation(this.imageView, Duration.millis(800), count, columns, offsetX, offsetY, getEntityWidth(),
				getEntityHeight());
		getChildren().addAll(this.imageView);
	}

	@Override
	public void walkX(int value) {
		boolean movingRight = value > 0;
		for (int i = 0; i < Math.abs(value); i++) {
			if (getBoundsInParent().intersects(GameMain.player.getBoundsInParent()))
				GameMain.player.setDead(true);
			for (Node platform : GameMain.platforms) {
				if (this.getBoundsInParent().intersects(platform.getBoundsInParent())) {
					if (movingRight) {
						if (this.getTranslateX() + this.getWidth() == platform.getTranslateX()) {
							this.setTranslateX(this.getTranslateX() - 1);
							this.setScaleX(-1);
							direction *= -1;
							return;
						}
					} else {
						if (this.getTranslateX() == platform.getTranslateX() + GameMain.BLOCK_SIZE) {
							this.setTranslateX(this.getTranslateX() + 1);
							this.setScaleX(1);
							direction *= -1;
							return;
						}
					}
				}
			}
			this.setTranslateX(this.getTranslateX() + (movingRight ? 1 : -1));
		}
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
}