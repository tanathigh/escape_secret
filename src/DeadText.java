import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class DeadText extends Canvas {
	private static final Font TITLE_FONT = new Font("Monospace", 80);
	private static final Font TITLE_FONT2 = new Font("Monospace", 40);
	GraphicsContext gc;
	Image bg;
	
	public DeadText() {
		super(1280, 620);
		gc = this.getGraphicsContext2D();
		bg = new Image("deadBG.png");
		gc.drawImage(bg, 0, 0);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFill(Color.RED);
		gc.setFont(TITLE_FONT);
		gc.fillText("You're Dead", 1280 / 2, 620 / 4);
		gc.setFill(Color.WHITE);
		gc.setFont(TITLE_FONT2);
		gc.fillText("Remaining life : " + GameMain.countLife, 1280 / 2, 620 / 2 + 200);
		gc.fillText("( Please press Enter )", 1280 / 2, 620 / 2 + 250);
		addKeyEventHandler();
	}

	private void addKeyEventHandler() {
		this.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				GameMain.playerMove = true;
				gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
			}
		});
	}
}
