package statusbar;

import appdata.AppStaticData;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import server.ServerStatus;
import util.CustumBinding;

public class StatusBarView extends HBox {

//	private StatusBarController controller;

	public StatusBarView(StatusBarController statusBarController) {
//		this.controller = statusBarController;
		setSpacing(25);
		this.setPadding(new Insets(10, 20, 10, 20));
		Pane xPane = new Pane();
		HBox.setHgrow(xPane, Priority.ALWAYS);
		getChildren().add(xPane);
		getChildren().add(getActiveConnectionsIndicator());
		getChildren().add(new Separator(Orientation.VERTICAL));
		getChildren().add(getRunningStatus());
		getChildren().add(new Separator(Orientation.VERTICAL));
		getChildren().add(getServerUpTime());
		getChildren().add(new Separator(Orientation.VERTICAL));
		getChildren().add(getCurrentDateTimeNode());
	}

	private Node getActiveConnectionsIndicator() {
		Label connectedNodesLabel = new Label("Active Connections: ");
		Label connectedNodesValueLabel = new Label("0");
		connectedNodesValueLabel.setStyle("-fx-font-weight: bold");
		CustumBinding.bind(connectedNodesValueLabel.textProperty(), AppStaticData.CLIENT_CONNECTIONS);
		HBox hbox = new HBox(10, connectedNodesLabel, connectedNodesValueLabel);
		hbox.disableProperty().bind(AppStaticData.SERVER_RUNNING_PROPERTY.not());
		return hbox;
	}

	private Node getRunningStatus() {
		Label runningStatusLabel = new Label("Server status: ");
		Label status = ServerStatus.STOPPED;
		HBox hbox = new HBox(10, runningStatusLabel, status);
		AppStaticData.SERVER_RUNNING_PROPERTY.addListener((observable, oldValue, newValue)->{
			if(newValue) {
				hbox.getChildren().set(1,ServerStatus.RUNNING);
			}
			else {
				hbox.getChildren().set(1,ServerStatus.STOPPED);
			}
		});
		return hbox;
	}

	private Node getServerUpTime() {
		Label label = new Label("Up Time: ");
		Label upTimeLabel = new Label("00:00:00");
		upTimeLabel.setStyle("-fx-font-weight: bold");
		HBox hbox = new HBox(10, label, upTimeLabel);
		return hbox;
	}

	private Node getCurrentDateTimeNode() {
		String dateTime = "00:00:00 23-Aug-2022";
		Label label = new Label(dateTime);
		label.setStyle("-fx-font-weight: bold;");
		HBox hbox = new HBox(label);
		return hbox;
	}
}
