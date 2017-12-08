public class LevelData {
	public static String[][] level;
	public static final int BLOCK_SIZE = 45;
	int levelNumber = 0;
	int levelWidth = 0;

	public LevelData() {
	}

	public void setBlock(int stage) {
		if (stage == 1) {
			level = new String[][] { LevelMap.LEVEL1 };
		} else if (stage == 2) {
			level = new String[][] { LevelMap.LEVEL2 };
		} else {
			level = new String[][] { LevelMap.LEVEL3 };
		}
		levelWidth = level[levelNumber][0].length() * BLOCK_SIZE;
		for (int i = 0; i < level[levelNumber].length; i++) {
			String line = level[levelNumber][i];
			for (int j = 0; j < line.length(); j++) {
				switch (line.charAt(j)) {
				// Block
				case '*':
					Block InvisibleBlock = new Block(Block.BlockType.INVISIBLE_BLOCK, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case '0':
					break;
				case '1':
					Block floor_down = new Block(Block.BlockType.FLOOR_DOWN, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case '2':
					Block floor_up = new Block(Block.BlockType.FLOOR_UP, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case '3':
					Block brick = new Block(Block.BlockType.BRICK, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case '4':
					Block door_up = new Block(Block.BlockType.DOOR_UP, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case '5':
					Block door_down = new Block(Block.BlockType.DOOR_DOWN, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case '6':
					Block stone = new Block(Block.BlockType.STONE, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case '7':
					Block box = new Block(Block.BlockType.BOX, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				// Exit out
				case 'o':
					Exit exit_out = new Exit(Exit.ExitType.EXIT_OUT, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				// Exit up
				case 'u':
					Exit exit_up = new Exit(Exit.ExitType.EXIT_UP, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				// Trap
				case '9':
					Trap floor_trap = new Trap(j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				// Decoration
				case 'q':
					Decoration ent = new Decoration(Decoration.DecType.ENTRANCE, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case 'w':
					Decoration fake_exit = new Decoration(Decoration.DecType.FAKE_EXIT, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case 'e':
					Decoration ice1 = new Decoration(Decoration.DecType.ICE1, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case 'a':
					Decoration ice = new Decoration(Decoration.DecType.ICE2, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case 's':
					Decoration mushroom = new Decoration(Decoration.DecType.MUSHROOM, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case 'd':
					Decoration mushroom_long = new Decoration(Decoration.DecType.MUSHROOM_LONG, j * BLOCK_SIZE,
							i * BLOCK_SIZE);
					break;
				case 'z':
					Decoration skull = new Decoration(Decoration.DecType.SKULL, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				case 'x':
					Decoration skull_long = new Decoration(Decoration.DecType.SKULL_LONG, j * BLOCK_SIZE,
							i * BLOCK_SIZE);
					break;
				case 'c':
					Decoration stone_dec = new Decoration(Decoration.DecType.STONE, j * BLOCK_SIZE, i * BLOCK_SIZE);
					break;
				}
			}
		}
	}
}
