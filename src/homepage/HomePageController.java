package homepage;

import appdata.AppStaticData;
import header.HeaderController;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import operations.OperationsController;

public class HomePageController {

	private HomePageView homePageView;
	private HeaderController headerController;
	private OperationsController operationsController;

	public HomePageController() {
		homePageView = new HomePageView(this);
		headerController = new HeaderController();
		homePageView.setTop(headerController.getView());
		operationsController = new OperationsController();
		homePageView.setRight(operationsController.getView());
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

}
