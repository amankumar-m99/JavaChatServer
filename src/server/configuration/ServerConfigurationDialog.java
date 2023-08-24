package server.configuration;

import appdata.AppStaticData;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.stage.Modality;

public class ServerConfigurationDialog extends Dialog<ButtonType>{
	public ServerConfigurationDialog() {
		initOwner(AppStaticData.PRIMARY_STAGE);
		initModality(Modality.APPLICATION_MODAL);
		setTitle("Server Configuration");
		initDialogPane();
	}

	private void initDialogPane() {
		DialogPane dialogPane = new DialogPane();
		dialogPane.getButtonTypes().addAll(ButtonType.NEXT, ButtonType.CANCEL);
		dialogPane.setContent(getContent());
		setDialogPane(dialogPane);
	}

	private Node getContent() {
		return new Label("Server configuration");
	}
}
