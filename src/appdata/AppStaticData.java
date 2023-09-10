package appdata;

import java.net.ServerSocket;

import connection.Connection;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AppStaticData {

	public static Stage PRIMARY_STAGE;
	public static Image APP_ICON;
	public static String SERVER_IP = "192.168.1.7";
	public static String SERVER_PORT = "4200";
	public static SimpleBooleanProperty SERVER_RUNNING_PROPERTY;
	public static SimpleStringProperty SERVER_LOGS_PROPERTY;
	public static SimpleIntegerProperty CLIENT_CONNECTIONS;
	public static ServerSocket SERVER_SOCKET;
	public static ObservableList<Connection> ACTIVE_CONNECTIONS = FXCollections.observableArrayList();

	public static double getScreenWidth() {
		return java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	}

	public static double getScreenHeight() {
		return java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	}
}
