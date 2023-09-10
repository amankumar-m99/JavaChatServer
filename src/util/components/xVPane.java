package util.components;

import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class xVPane extends Pane{
	public xVPane() {
		VBox.setVgrow(this, Priority.ALWAYS);
	}
}

