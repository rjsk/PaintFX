package plugin.brush;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.paint.Color;
import sample.Command;
import sample.UILoader;

import java.net.URL;

public class BrushControl extends AnchorPane implements Command {

    private String controlName;

    @FXML
    private Button button;

    public BrushControl() {
        this.controlName = "tool.brush";
        UILoader uiLoader = new UILoader(getClass().getResource("brush.fxml"),this);
        uiLoader.load();
    }

    public String getName() {
        return controlName;
    }

    @Override
    public void setAction(EventHandler<ActionEvent> value) {
       button.setOnAction(value);
    }

    @Override
    public void execute(GraphicsContext gc, MouseEvent e, Color color, int size) {

        gc.setLineWidth(size);
        gc.setStroke(color);
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.setLineJoin(StrokeLineJoin.ROUND);
        if(e.getEventType()==MouseEvent.MOUSE_DRAGGED) {
            gc.lineTo(e.getX(), e.getY());
            gc.stroke();
        }
        else if(e.getEventType()==MouseEvent.MOUSE_PRESSED) {
            gc.beginPath();
            gc.moveTo(e.getX(), e.getY());
            gc.stroke();
        }
    }
}
