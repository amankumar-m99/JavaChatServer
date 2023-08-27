package homepage;

import appdata.AppStaticData;
import connection.logs.ConnectionLogView;
import header.HeaderController;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import operations.OperationsController;
import statusbar.StatusBarController;

public class HomePageController {

	private HomePageView homePageView;
	private HeaderController headerController;
	private OperationsController operationsController;
	private StatusBarController statusBarController;

	public HomePageController() {
		homePageView = new HomePageView(this);
		//
		headerController = new HeaderController(this);
		homePageView.setTop(headerController.getView());
		//
		operationsController = new OperationsController();
		homePageView.setRight(operationsController.getView());
		//
		statusBarController = new StatusBarController();
		homePageView.setBottom(statusBarController.getView());
		//
		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		textArea.textProperty().bind(AppStaticData.SERVER_LOGS_PROPERTY);
		HBox.setHgrow(textArea, Priority.ALWAYS);
		VBox.setVgrow(textArea, Priority.ALWAYS);
		homePageView.setCenter(textArea);
	}

	public Node getView() {
		return homePageView;
	}

	public void setLeft(ConnectionLogView connectionLogView) {
		homePageView.setLeft(connectionLogView);
	}

}
