package util.components;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class xHPane extends Pane{
	public xHPane() {
		HBox.setHgrow(this, Priority.ALWAYS);
	}
}
