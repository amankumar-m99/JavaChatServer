package server;

import javafx.scene.control.Label;

public class ServerStatus {

	private static String runningCSS = "-fx-text-fill: GREEN;-fx-font-weight:bold;";
	private static String startingCSS = "-fx-text-fill: ORANGE;-fx-font-weight:bold;";
	private static String stoppedCSS = "-fx-text-fill: RED;-fx-font-weight:bold;";

	public static Label RUNNING = new Label("Running");
	public static Label STARTING = new Label("Starting");
	public static Label STOPPED = new Label("Stopped");

	static{
		System.out.println("static");
		RUNNING.setStyle(runningCSS);
		STARTING.setStyle(startingCSS);
		STOPPED.setStyle(stoppedCSS);
	}
}
