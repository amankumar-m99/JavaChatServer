package operations;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import appdata.AppStaticData;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.DirectoryChooser;
import server.Server;
import server.configuration.ServerConfigurationDialog;

public class OperationsController {

	private OperationsView operationsView;

	public OperationsController() {
		operationsView = new OperationsView(this);
	}

	public OperationsView getView() {
		return operationsView;
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
	}

	public void downloadLogs() {
		if (AppStaticData.SERVER_LOGS_PROPERTY.getValue().trim().length() == 0) {
			showEmptyLogDownloadAlert();
			return;
		}
		try {
			File targetFile = getTargetDirectory();
			if (targetFile == null)
				return;
			targetFile.mkdirs();
			targetFile.createNewFile();
			FileWriter myWriter = new FileWriter(targetFile);
			myWriter.write(AppStaticData.SERVER_LOGS_PROPERTY.getValue().trim());
			myWriter.close();
			showFileCreatedAlert(targetFile.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showFileCreatedAlert(String canonicalPath) {		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Log file saved.");
		alert.setContentText("Location: "+ canonicalPath);
		alert.showAndWait();
	}

	private void showEmptyLogDownloadAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("No logs to download.");
		alert.setContentText("No logs are generated.");
		alert.showAndWait();
	}

	private File getTargetDirectory() throws IOException {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Choose directory to save log file");
		File directory = directoryChooser.showDialog(AppStaticData.PRIMARY_STAGE);
		if (directory == null)
			return null;
		Date date = new Date();
		@SuppressWarnings("deprecation")
		String fileName = String.format("%d-%d-%d-%d-%d-%d.log", date.getYear(), date.getMonth(), date.getDay(),
				date.getHours(), date.getMinutes(), date.getSeconds());
		String fileLocation = directory.getCanonicalPath() + File.separator + fileName.toString();
		return new File(fileLocation);
	}

	public void serverConfig() {
		ServerConfigurationDialog dialog = new ServerConfigurationDialog();
		dialog.showAndWait();
	}

	public void clearLogs() {
		if (AppStaticData.SERVER_LOGS_PROPERTY.getValue().trim().length() == 0) {
			showEmptyLogClearAlert();
			return;
		}
		Optional<ButtonType> confirmClearLogs = confirmClearLogs();
		if (confirmClearLogs.isPresent() && confirmClearLogs.get().equals(ButtonType.OK))
			AppStaticData.SERVER_LOGS_PROPERTY.setValue("");
	}

	private void showEmptyLogClearAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("No logs to clear.");
		alert.setContentText("Logs are already clear.");
		alert.showAndWait();
	}

	private Optional<ButtonType> confirmClearLogs() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confrim clear logs");
		alert.setHeaderText("Clear all logs ?");
		alert.setContentText("This action can't be reversed.");
		return alert.showAndWait();
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
}
