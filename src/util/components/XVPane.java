package util.components;

import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class XVPane extends Pane{
	public XVPane() {
		VBox.setVgrow(this, Priority.ALWAYS);
	}
}

