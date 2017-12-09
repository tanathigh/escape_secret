package logic;
import application.GameMain;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Block extends Pane {
	ImageView block = new ImageView(new Image(ClassLoader.getSystemResource("obAll.png").toString()));

	public enum BlockType {
		FLOOR_DOWN, FLOOR_UP, BRICK, DOOR_UP, DOOR_DOWN, STONE, BOX, INVISIBLE_BLOCK;
	}

	public Block(BlockType blockType, int x, int y) {
		block.setFitWidth(GameMain.BLOCK_SIZE);
		block.setFitHeight(GameMain.BLOCK_SIZE);
		setTranslateX(x);
		setTranslateY(y);
		switch (blockType) {
		// 1
		case FLOOR_DOWN:
			block.setViewport(new Rectangle2D(0, 0, 30, 15));
			block.setFitWidth(GameMain.BLOCK_SIZE * 2);
			break;
		// 2
		case FLOOR_UP:
			block.setViewport(new Rectangle2D(0, 16, 30, 15));
			block.setFitWidth(GameMain.BLOCK_SIZE * 2);
			break;
		// 3
		case BRICK:
			block.setViewport(new Rectangle2D(32, 0, 15, 15));
			break;
		// 4
		case DOOR_UP:
			block.setViewport(new Rectangle2D(64, 0, 15, 15));
			break;
		// 5
		case DOOR_DOWN:
			block.setViewport(new Rectangle2D(64, 16, 15, 15));
			break;
		// 6
		case STONE:
			block.setViewport(new Rectangle2D(48, 0, 15, 15));
			break;
		// 7
		case BOX:
			block.setViewport(new Rectangle2D(48, 16, 15, 15));
			break;
		// *
		case INVISIBLE_BLOCK:
			block.setViewport(new Rectangle2D(65, 0, 16, 16));
			block.setOpacity(0);
			break;
		}
		getChildren().add(block);
		GameMain.platforms.add(this);
		GameMain.gameRoot.getChildren().add(this);
	}
}
