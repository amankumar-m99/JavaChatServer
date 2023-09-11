package util.components;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class XHPane extends Pane{
	public XHPane() {
		HBox.setHgrow(this, Priority.ALWAYS);
	}
}
