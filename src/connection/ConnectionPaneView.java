package connection;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import util.components.SearchField;
import util.components.xHPane;

public class ConnectionPaneView extends VBox{

	private final double SPACING = 10;
	private SearchField searchField;
	private ConnectionPaneController controller;

	public ConnectionPaneView(ConnectionPaneController connectionPaneController) {
		this.controller = connectionPaneController;
		this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
		Label label = new Label("Active Connections");
		label.setStyle("-fx-font-weight:bold;");
		ImageView closeImage = new ImageView(new Image("resources/close.png"));
		closeImage.setPreserveRatio(true);
		closeImage.setFitHeight(15);
		closeImage.setOnMouseEntered(e-> closeImage.setCursor(Cursor.HAND));
		closeImage.setOnMouseExited(e-> closeImage.setCursor(Cursor.DEFAULT));
		closeImage.setOnMouseClicked(e-> controller.closeView());
		xHPane xPane = new xHPane();
		HBox hbox = new HBox(label, xPane,closeImage);
		hbox.setPadding(new Insets(5));
		hbox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0,0,0.5,0))));
		searchField = new SearchField();
		ConnectionList list = new ConnectionList();
		VBox.setVgrow(list, Priority.ALWAYS);
		this.getChildren().addAll(hbox, searchField, list);
		this.setPadding(new Insets(10));
		this.setSpacing(SPACING);
	}
}
