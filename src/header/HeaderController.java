package header;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import appdata.AppStaticData;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContextMenu;

public class HeaderController {

	private HeaderView headerView;

	public HeaderController() {
		this.headerView = new HeaderView(this);
	}

	public HeaderView getView() {
		return headerView;
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
