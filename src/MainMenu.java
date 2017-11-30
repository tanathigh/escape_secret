import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class MainMenu extends Canvas{
	private static final Font TITLE_FONT = new Font("Monospace", 80);
	private static final Font MENU_FONT = new Font("Monospace", 40);
	GraphicsContext gc;
	
	public MainMenu() {
		super(1280,620);
		gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFill(Color.WHITE);
		gc.setFont(TITLE_FONT);
		gc.fillText("Escape", 1280 / 2, 620 / 4);
		gc.setFont(MENU_FONT);
		gc.fillText("Press Enter to start", 1280 / 2, 620 * 3 / 4);
	}
	
	public void update() {
		gc.clearRect(0, 0, this.getWidth(), this.getHeight());
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	private void addKeyEventHandler() {
		//TODO
	}

}
