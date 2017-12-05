import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Instruction extends Canvas{
	private static final Font FONT = new Font("Monospace", 40);
	GraphicsContext gc;
	
	public Instruction() {
		super(1280,620);
		gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFill(Color.WHITE);
		gc.setFont(FONT);
		gc.fillText("<^v> - Move", 1280 / 2, 620 / 4);
		gc.fillText("Spacebar - Jump", 1280 / 2, 620 / 2);
		gc.fillText("Press any key to return to main menu", 1280 / 2, 620 * 4 / 5);
		
		addKeyEventHandler();
	}
	
	private void addKeyEventHandler() {
		this.setOnKeyPressed(event ->{
			SceneManager.gotoMainMenu();
		});
	}

}
