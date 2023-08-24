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
import server.Server;
import server.ServerStatus;
import util.CustumBinding;

public class StatusBarView extends HBox {

	private StatusBarController controller;

	public StatusBarView(StatusBarController statusBarController) {
		this.controller = statusBarController;
		setSpacing(20);
		this.setPadding(new Insets(10, 20, 10, 20));
		Pane xPane = new Pane();
		HBox.setHgrow(xPane, Priority.ALWAYS);
		getChildren().addAll(xPane, getConnectedNodesStatus(), new Separator(Orientation.VERTICAL), getRunninnngStatus());
	}

	private Node getConnectedNodesStatus() {
		Label connectedNodesLabel = new Label("Connected Nodes: ");
		Label connectedNodesValueLabel = new Label("670");
		CustumBinding.bind(connectedNodesValueLabel.textProperty(), AppStaticData.CLIENT_CONNECTIONS);
		HBox hbox = new HBox(10, connectedNodesLabel, connectedNodesValueLabel);
		return hbox;
	}

	private Node getRunninnngStatus() {
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
}
