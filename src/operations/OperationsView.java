package operations;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class OperationsView extends VBox {

	private OperationsController controller;

	public OperationsView(OperationsController operationsController) {
		controller = operationsController;
		this.setSpacing(30);
		this.setPadding(new Insets(20,20,10,20));
		//
		ImageView copyLogIcon = getImageView("resources/copy.png", "Copy logs to clipboard");
		copyLogIcon.setOnMouseClicked(e-> controller.copyLogs());
		//
		ImageView saveLogIcon = getImageView("resources/save-logs.png", "Save Logs");
		saveLogIcon.setOnMouseClicked(e-> controller.downloadLogs());
		//
		ImageView clearLogsIcon = getImageView("resources/clear-logs.png", "Clear log");
		clearLogsIcon.setOnMouseClicked(e-> controller.clearLogs());
		//
		this.getChildren().addAll(copyLogIcon, saveLogIcon, clearLogsIcon);
	}

	private ImageView getImageView(String path, String tooltipText) {
		ImageView imageView = new ImageView(new Image(path));
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(35);
		if(tooltipText != null && tooltipText.length() > 0) {
			Tooltip.install(imageView, new Tooltip(tooltipText));
		}
		imageView.setOnMouseEntered(e-> imageView.setCursor(Cursor.HAND));
		imageView.setOnMouseExited(e-> imageView.setCursor(Cursor.DEFAULT));
		return imageView;
	}
}
