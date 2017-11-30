public class LevelData {

	public static String[][] level;
	public static final int BLOCK_SIZE = 45;
	int levelNumber = 0;
	int levelWidth = 0;

	public LevelData() {
	}

	public void setBlock(String[] LEVEL) {
		level = new String[][] { LEVEL };
		levelWidth = level[levelNumber][0].length() * BLOCK_SIZE;
		for (int i = 0; i < level[levelNumber].length; i++) {
			String line = level[levelNumber][i];
			for (int j = 0; j < line.length(); j++) {
				switch (line.charAt(j)) {
				case '*':
					Block InvisibleBlock = new Block(Block.BlockType.INVISIBLE_BLOCK, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case '0':
					break;
				case '1':
					Block floor1 = new Block(Block.BlockType.FLOOR1, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case '2':
					Block floor2 = new Block(Block.BlockType.FLOOR2, j * BLOCK_SIZE, i * BLOCK_SIZE);
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
				// ***************************************************************************************************************
				case '6':
					Trap PipeBottomBlock = new Trap(j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				}
			}

		}
	}
}
