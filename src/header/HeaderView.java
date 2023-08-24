package header;

import appdata.AppStaticData;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import util.CustumBinding;

public class HeaderView extends HBox{

	private HeaderController controller;

	public HeaderView(HeaderController headerController) {
		this.controller = headerController;
		this.setSpacing(15);
		this.setPadding(new Insets(10, 20, 10, 20));
		Label connectedNodesLabel = new Label("Connected Nodes: ");
		Label connectedNodesValueLabel = new Label("670");
		CustumBinding.bind(connectedNodesValueLabel.textProperty(), AppStaticData.CLIENT_CONNECTIONS);
		Label runningStatusLabel = new Label("Server status: ");
		Label status = new Label("unconnected");
		this.getChildren().addAll(connectedNodesLabel, connectedNodesValueLabel, runningStatusLabel, status);
	}
}
