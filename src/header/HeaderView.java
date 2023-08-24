package header;

import appdata.AppStaticData;
import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class HeaderView extends HBox{

	private HeaderController controller;
	private Hyperlink copyServerIp;
	private Hyperlink copyServerPort;

	public HeaderView(HeaderController headerController) {
		this.controller = headerController;
		this.setSpacing(15);
		this.setPadding(new Insets(10, 20, 10, 20));
		Pane pane = new Pane();
		HBox.setHgrow(pane, Priority.ALWAYS);
		//
		copyServerIp = new Hyperlink("Copy server IP");
		copyServerIp.disableProperty().bind(AppStaticData.SERVER_RUNNING_PROPERTY.not());
		copyServerIp.setOnAction(e-> copyServerIP());
		//
		copyServerPort = new Hyperlink("Copy server Port");
		copyServerPort.disableProperty().bind(AppStaticData.SERVER_RUNNING_PROPERTY.not());
		copyServerPort.setOnAction(e-> copyServerPort());
		//
		this.getChildren().addAll(pane, copyServerIp, copyServerPort);
	}

	private void copyServerIP() {
		controller.copyServerIP();
		copyServerIp.setVisited(false);
		
	}
	private void copyServerPort() {
		controller.copyServerPort();
		copyServerPort.setVisited(false);
	}
}
