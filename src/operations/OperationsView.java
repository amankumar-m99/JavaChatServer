package operations;

import appdata.AppStaticData;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class OperationsView extends VBox {

	private OperationsController controller;

	public OperationsView(OperationsController operationsController) {
		controller = operationsController;
		this.setSpacing(20);
		this.setPadding(new Insets(10,20,10,20));
		Button startBtn = new Button("Start Server");
		startBtn.setMaxWidth(Double.MAX_VALUE);
		startBtn.setOnAction(e -> controller.startServer());
		startBtn.disableProperty().bind(AppStaticData.SERVER_RUNNING_PROPERTY);
		//
		Button stopBtn = new Button("Stop Server");
		stopBtn.setMaxWidth(Double.MAX_VALUE);
		stopBtn.setOnAction(e -> controller.stopServer());
		stopBtn.disableProperty().bind(AppStaticData.SERVER_RUNNING_PROPERTY.not());
		//
		Button restartBtn = new Button("Restart Server");
		restartBtn.setMaxWidth(Double.MAX_VALUE);
		restartBtn.setOnAction(e -> controller.restartServer());
		restartBtn.disableProperty().bind(AppStaticData.SERVER_RUNNING_PROPERTY.not());
		//
		Button viewAllConnectionsBtn = new Button("View All Connections");
		viewAllConnectionsBtn.setMaxWidth(Double.MAX_VALUE);
		viewAllConnectionsBtn.setOnAction(e-> controller.viewAllConnectionsBtn());
		viewAllConnectionsBtn.disableProperty().bind(AppStaticData.SERVER_RUNNING_PROPERTY.not());
		//
		Pane xHPane = new Pane();
		VBox.setVgrow(xHPane, Priority.ALWAYS);
		//
		Button clearLogsBtn = new Button("Clear Logs");
		clearLogsBtn.setOnAction(e-> controller.clearLogs());
		clearLogsBtn.setMaxWidth(Double.MAX_VALUE);
		//
		Button downloadLogsBtn = new Button("Download Logs");
		downloadLogsBtn.setOnAction(e-> controller.downloadLogs());
		downloadLogsBtn.setMaxWidth(Double.MAX_VALUE);
		//
		Button serverConfigBtn = new Button("Server Configuration");
		serverConfigBtn.setOnAction(e-> controller.serverConfig());
		serverConfigBtn.setMaxWidth(Double.MAX_VALUE);
		//
		this.getChildren().addAll(startBtn, stopBtn, restartBtn, viewAllConnectionsBtn, xHPane, clearLogsBtn, downloadLogsBtn, serverConfigBtn);
	}

}
