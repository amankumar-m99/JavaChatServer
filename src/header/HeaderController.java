package header;

public class HeaderController {

	private HeaderView headerView;

	public HeaderController() {
		this.headerView = new HeaderView(this);
	}

	public HeaderView getView() {
		return headerView;
	}
}
