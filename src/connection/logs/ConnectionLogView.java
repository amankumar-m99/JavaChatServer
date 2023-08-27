package connection.logs;

import connection.Connection;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ConnectionLogView extends VBox{

	private Connection connection;
	public ConnectionLogView(Connection connection) {
		this.connection = connection;
		getChildren().add(getHeader());
		getChildren().add(new TextArea(connection.logs));
	}

	private Node getHeader() {
		HBox header = new HBox(10);
		Pane pane = new Pane();
		HBox.setHgrow(pane, Priority.ALWAYS);
		Label label = new Label(connection.ip);
		ImageView cross = new ImageView(new Image("resources/close.png"));
		header.getChildren().addAll(label, pane, cross);
		return header;
	}
}
