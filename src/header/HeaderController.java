package header;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;

import appdata.AppStaticData;
import connection.ConnectionPaneController;
import homepage.HomePageController;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContextMenu;
import server.Server;

public class HeaderController {

	private HeaderView headerView;
	private HomePageController controller;

	public HeaderController(HomePageController controller) {
		this.controller = controller;
		this.headerView = new HeaderView(this);
	}

	public HeaderView getView() {
		return headerView;
	}

	public void startServer() {
		try {
			Thread serverThread = new Thread(new Server());
			serverThread.setDaemon(true);
			serverThread.start();
			AppStaticData.SERVER_RUNNING_PROPERTY.setValue(true);
		} catch (Exception e) {
			AppStaticData.SERVER_RUNNING_PROPERTY.setValue(false);
			e.printStackTrace();
		}
	}

	public void stopServer() {
		try {
			AppStaticData.SERVER_SOCKET.close();
			AppStaticData.SERVER_RUNNING_PROPERTY.setValue(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void restartServer() {
		stopServer();
		startServer();
	}

	public void viewAllConnectionsBtn() {
		ConnectionPaneController connectionPaneController = new ConnectionPaneController(controller);
		controller.setLeft(connectionPaneController.getView());
	}

	public void copyServerIP() {
		String ip = AppStaticData.SERVER_IP;
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection strse1 = new StringSelection(ip);
		clip.setContents(strse1, strse1);
		showClipboardCopiedAlert();
	}

	public void copyServerPort() {
		String port = AppStaticData.SERVER_PORT;
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection strse1 = new StringSelection(port);
		clip.setContents(strse1, strse1);
		showClipboardCopiedAlert();
	}

	private void showClipboardCopiedAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Copied to clipboard");
		alert.setContentText("Content copied to clipboard.");
		alert.showAndWait();
	}

	public void showContextMenu(Object object, double x, double y) {
		ContextMenu contextMenu = headerView.getContextMenu();
		contextMenu.show((Node) object, x, y);
	}
}
