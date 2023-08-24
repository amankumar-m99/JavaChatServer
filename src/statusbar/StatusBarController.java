package statusbar;

import javafx.scene.Node;

public class StatusBarController {

	private StatusBarView statusBarView;

	public StatusBarController() {
		statusBarView = new StatusBarView(this);
	}

	public Node getView() {
		return statusBarView;
	}
}
