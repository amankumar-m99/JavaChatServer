package util.components;

import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class SearchField extends HBox{

	private final double SPACING = 10;
	private TextField textField;

	public SearchField() {
		textField = new TextField();
		textField.setPromptText("Search");
		Button button = new Button("Search");
		this.setSpacing(SPACING);
		this.getChildren().addAll(textField, button);
	}

	public StringProperty getTextProperty() {
		return textField.textProperty();
	}
}
