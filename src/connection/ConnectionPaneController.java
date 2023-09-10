package connection;

import homepage.HomePageController;
import javafx.scene.Node;

public class ConnectionPaneController {
	private ConnectionPaneView connectionPaneView;
	private HomePageController controller;

	public ConnectionPaneController(HomePageController controller) {
		this.controller = controller;
		connectionPaneView = new ConnectionPaneView(this);
	}

	public Node getView() {
		return connectionPaneView;
	}

	public void closeView() {
		controller.setLeft(null);
	}
}
