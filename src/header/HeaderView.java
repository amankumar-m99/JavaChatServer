package header;

import appdata.AppStaticData;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class HeaderView extends HBox{

	private HeaderController controller;

	public HeaderView(HeaderController headerController) {
		this.controller = headerController;
		this.setSpacing(15);
		this.setPadding(new Insets(10, 20, 10, 20));
		Pane pane = new Pane();
		HBox.setHgrow(pane, Priority.ALWAYS);
		Node copyServerIp = getCopyServerIpNode();
		Node copyServerPort = getCopyServerPortNode();
		ImageView settingImageView = getSettingsNode();
		this.getChildren().addAll(pane, copyServerIp, copyServerPort, settingImageView);
	}

	private Node getCopyServerIpNode(){
		Hyperlink copyServerIp = new Hyperlink("Copy server IP");
		copyServerIp.disableProperty().bind(AppStaticData.SERVER_RUNNING_PROPERTY.not());
		copyServerIp.setOnAction(e-> copyServerIP(copyServerIp));
		return copyServerIp;
	}

	private void copyServerIP(Hyperlink link) {
		controller.copyServerIP();
		link.setVisited(false);
	}

	private Node getCopyServerPortNode(){
		Hyperlink copyServerPort = new Hyperlink("Copy server Port");
		copyServerPort.disableProperty().bind(AppStaticData.SERVER_RUNNING_PROPERTY.not());
		copyServerPort.setOnAction(e-> copyServerPort(copyServerPort));
		return copyServerPort;
	}

	private void copyServerPort(Hyperlink link) {
		controller.copyServerPort();
		link.setVisited(false);
	}

	private ImageView getSettingsNode() {
		ImageView imageView = new ImageView(new Image("resources/settings.png"));
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(30);
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
		contextMenu.getItems().addAll(configMenu, aboutMenu);
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
