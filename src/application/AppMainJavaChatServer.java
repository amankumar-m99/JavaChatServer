package application;

import java.io.IOException;
import java.util.Optional;

import appdata.AppStaticData;
import homepage.HomePageController;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AppMainJavaChatServer extends Application{
	public static void main(String[] args) throws IOException {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initAppStaticData(primaryStage);
		Node homePage = getHomePageView();
		Scene scene = new Scene((Parent) homePage, 800, 1200);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Java Chat- Server");
		primaryStage.getIcons().add(AppStaticData.APP_ICON);
		primaryStage.setMaximized(true);
		primaryStage.setOnCloseRequest(e -> confirmClosingApplication(e));
		primaryStage.show();
	}

	private void initAppStaticData(Stage stage) {
		AppStaticData.PRIMARY_STAGE = stage;
		AppStaticData.APP_ICON = new Image("resources/app-icon.png");
		AppStaticData.SERVER_RUNNING_PROPERTY = new SimpleBooleanProperty(false);
		AppStaticData.SERVER_LOGS_PROPERTY = new SimpleStringProperty("");
		AppStaticData.CLIENT_CONNECTIONS = new SimpleIntegerProperty(0);
	}

	private Node getHomePageView() {
		HomePageController homePageController = new HomePageController();
		return homePageController.getView();
	}

	private void confirmClosingApplication(WindowEvent e) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit Application ?");
		alert.setHeaderText("Close application and exit ?");
		alert.initOwner(AppStaticData.PRIMARY_STAGE);
		alert.initModality(Modality.APPLICATION_MODAL);
		Optional<ButtonType> result = alert.showAndWait();
		if (!result.isPresent() || result.get().equals(ButtonType.CANCEL))
			e.consume();
//		try {
//			AppStaticData.SelfSocket.close();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
	}
}
