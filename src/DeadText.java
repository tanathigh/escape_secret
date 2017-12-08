import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class DeadText extends Canvas{
	private static final Font TITLE_FONT = new Font("Monospace", 80);
	GraphicsContext gc;
	
	public DeadText() {
		super(1280, 620);
		gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFill(Color.WHITE);
		gc.setFont(TITLE_FONT);
		gc.fillText("Remaining : "+GameMain.countLife, 1280 / 2, 620 / 2);
		addKeyEventHandler();
	}


	private void addKeyEventHandler() {
		this.setOnKeyPressed(event ->{
			gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		});
	}

}
