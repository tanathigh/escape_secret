import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Block extends Pane {
	Image blocksImg = new Image(getClass().getResourceAsStream("ob1.png"));
	ImageView block;
	public enum BlockType {
		FLOOR1, FLOOR2, BONUS, PIPE_TOP, PIPE_BOTTOM, INVISIBLE_BLOCK, STONE;
	}

	public Block(BlockType blockType, int x, int y) {
		block = new ImageView(blocksImg);
		block.setFitWidth(GameMain.BLOCK_SIZE);
		block.setFitHeight(GameMain.BLOCK_SIZE);
		setTranslateX(x);
		setTranslateY(y);

		switch (blockType) {
		case FLOOR1:
			block.setViewport(new Rectangle2D(0, 0, 30, 16));
			block.setFitWidth(GameMain.BLOCK_SIZE * 2);
			break;
		case FLOOR2:
			block.setViewport(new Rectangle2D(34, 0, 27, 16));
			block.setFitWidth(GameMain.BLOCK_SIZE * 2);
			break;
		case BONUS:
			block.setViewport(new Rectangle2D(65, 0, 16, 16));
			break;
		case PIPE_TOP:
			block.setViewport(new Rectangle2D(65, 0, 16, 16));
			break;
		case PIPE_BOTTOM:
			block.setViewport(new Rectangle2D(65, 0, 16, 16));
			break;
		case INVISIBLE_BLOCK:
			block.setViewport(new Rectangle2D(65, 0, 16, 16));
			block.setOpacity(0);
			break;
		case STONE:
			block.setViewport(new Rectangle2D(65, 0, 16, 16));
			break;
		}
		getChildren().add(block);
		GameMain.platforms.add(this);
		GameMain.gameRoot.getChildren().add(this);
	}
}
