package connection;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ConnectionListCellFactory implements Callback<ListView<Connection>, ListCell<Connection>>{

	@Override
	public ListCell<Connection> call(ListView<Connection> param) {
		final ListCell<Connection> cell = new ListCell<Connection>() {
            @Override
            public void updateItem(Connection item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        setText(item.ip);
                        setTooltip(null);
                    }
            }
        };
        return cell;
	}
	
}
