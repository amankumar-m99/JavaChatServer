package homepage;

import javafx.scene.layout.BorderPane;

public class HomePageView extends BorderPane{

	private HomePageController controller;

	public HomePageView(HomePageController homePageController) {
		this.controller = homePageController;
		initHeader();
	}

	private void initHeader() {
	}
}
