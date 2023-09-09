package connection.logs;

import connection.Connection;
import homepage.HomePageController;
import javafx.scene.Node;

public class ConnectionLogController {

	private ConnectionLogView connectionLogView;
	private HomePageController controller;

	public ConnectionLogController(HomePageController controller) {
		this.controller = controller;
		Connection connection = new Connection();
		connection.ip = "0:0:0:0";
		connection.logs = "sdknekdfnlkfnibni";
		this.connectionLogView = new ConnectionLogView(connection, this);
	}

	public Node getView() {
		return connectionLogView;
	}

	public void closeView() {
		controller.setLeft(null);
	}

}
