package header;

import appdata.AppStaticData;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import util.components.XHPane;

public class HeaderView extends HBox{

	private HeaderController controller;

	public HeaderView(HeaderController headerController) {
		this.controller = headerController;
		this.setSpacing(20);
		this.setPadding(new Insets(10, 20, 10, 20));
		XHPane pane = new XHPane();
		ImageView settingImageView = getSettingsNode();
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
		Button viewAllConnectionsBtn = new Button("Active Connections");
		viewAllConnectionsBtn.setMaxWidth(Double.MAX_VALUE);
		viewAllConnectionsBtn.setOnAction(e-> controller.viewAllConnectionsBtn());
		viewAllConnectionsBtn.disableProperty().bind(AppStaticData.SERVER_RUNNING_PROPERTY.not());
		//
		getChildren().addAll(startBtn, stopBtn, restartBtn);
		getChildren().add(viewAllConnectionsBtn);
		getChildren().add(pane);
		getChildren().add(getServerIpStatus());
		getChildren().add(getServerPortStatus());
		getChildren().addAll(settingImageView);
	}

	private Node getServerIpStatus() {
		Label label = new Label("Server Local IP: ");
		Label localIpLabel = new Label(AppStaticData.SERVER_IP);
		localIpLabel.setStyle("-fx-font-weight: bold");
//		localIpLabel.textProperty().bind(AppStaticData.SERVER_IP);
		ImageView copyImage = new ImageView(new Image("resources/copy.png"));
		copyImage.setPreserveRatio(true);
		copyImage.setFitHeight(20);
		copyImage.visibleProperty().bind(AppStaticData.SERVER_RUNNING_PROPERTY);
		HBox hbox = new HBox(10, label, localIpLabel, copyImage);
		hbox.disableProperty().bind(AppStaticData.SERVER_RUNNING_PROPERTY.not());
		return hbox;
	}

	private Node getServerPortStatus() {
		Label label = new Label("Server Port: ");
		Label localPortLabel = new Label(AppStaticData.SERVER_PORT);
		localPortLabel.setStyle("-fx-font-weight: bold");
//		localPortLabel.textProperty().bind(AppStaticData.SERVER_IP);
		ImageView copyImage = new ImageView(new Image("resources/copy.png"));
		copyImage.setPreserveRatio(true);
		copyImage.setFitHeight(20);
		copyImage.visibleProperty().bind(AppStaticData.SERVER_RUNNING_PROPERTY);
		HBox hbox = new HBox(10, label, localPortLabel, copyImage);
		hbox.disableProperty().bind(AppStaticData.SERVER_RUNNING_PROPERTY.not());
		return hbox;
	}


	private ImageView getSettingsNode() {
		ImageView imageView = new ImageView(new Image("resources/settings.png"));
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(35);
		imageView.setOnMouseEntered(e-> imageView.setCursor(Cursor.HAND));
		imageView.setOnMouseExited(e-> imageView.setCursor(Cursor.DEFAULT));
		imageView.setOnMouseClicked(e-> showContextMenu(e));
		return imageView;
	}

	private void showContextMenu(MouseEvent e) {
		double x = e.getScreenX();
		double y = e.getSceneY();
		controller.showContextMenu(e.getSource(),x,y);
	}

	ContextMenu getContextMenu() {
		MenuItem configMenu = getMenuItem("Server Configuration", "resources/config.png");
		MenuItem aboutMenu = getMenuItem("About", "resources/about.png");
		ContextMenu contextMenu = new ContextMenu();
		contextMenu.getItems().addAll(configMenu, new SeparatorMenuItem(), aboutMenu);
		return contextMenu;
	}

	private MenuItem getMenuItem(String text, String imagePath) {
		ImageView imageView = new ImageView(new Image(imagePath));
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(20);
		MenuItem menuItem = new MenuItem(text);
		menuItem.setGraphic(imageView);
		return menuItem;
	}
}
