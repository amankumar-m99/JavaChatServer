package util;

import javafx.beans.property.Property;

public class CustumBinding {

	public static void bind(Property<String> stringProperty, Property<Number> intProperty) {
		intProperty.addListener((observable, oldValue, newValue)->{
			String newStrValue = String.valueOf(newValue);
			stringProperty.setValue(newStrValue);
		});
	}
}
