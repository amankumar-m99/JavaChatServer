package homepage;

import appdata.AppStaticData;
import header.HeaderController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import operations.OperationsController;
import statusbar.StatusBarController;
import util.components.XHPane;

public class HomePageController {

	private HomePageView homePageView;
	private HeaderController headerController;
	private StatusBarController statusBarController;

	public HomePageController() {
		homePageView = new HomePageView(this);
		//
		headerController = new HeaderController(this);
		homePageView.setTop(headerController.getView());
		//
		statusBarController = new StatusBarController();
		homePageView.setBottom(statusBarController.getView());
		//
		Node centerNode = getServerLogsView();
		homePageView.setCenter(centerNode);
	}

	public Node getView() {
		return homePageView;
	}

	public void setLeft(Node connectionLogView) {
		homePageView.setLeft(connectionLogView);
	}

	private Node getServerLogsView() {
		Label label = new Label("Server Logs");
		label.setStyle("-fx-font-weight: bold;");
		XHPane hPane = new XHPane();
		OperationsController operationsController = new OperationsController();
		HBox hbox = new HBox(10, label, hPane, operationsController.getView());
		hbox.setPadding(new Insets(20, 5, 5, 5));
		hbox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0,0,0.5,0))));
		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		textArea.textProperty().bind(AppStaticData.SERVER_LOGS_PROPERTY);
		HBox.setHgrow(textArea, Priority.ALWAYS);
		VBox.setVgrow(textArea, Priority.ALWAYS);
		VBox vbox = new VBox(0, hbox, textArea);
		vbox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
		return vbox;
	}

}
