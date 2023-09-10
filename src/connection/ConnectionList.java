package connection;

import appdata.AppStaticData;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class ConnectionList extends ListView<Connection>{

	public ConnectionList() {
		ImageView iv = new ImageView(new Image("resources/app-icon.png"));
		iv.setPreserveRatio(true);
		iv.setOpacity(0.5);
		iv.setFitHeight(150);
		Label label = new Label("No Active Connections");
		label.setStyle("-fx-font-weight:bold;-fx-background-color: white;");
		setPlaceholder(new StackPane(iv, label));
		setCellFactory(new ConnectionListCellFactory());
		setItems(AppStaticData.ACTIVE_CONNECTIONS);
	}
}
