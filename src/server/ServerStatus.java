package server;

import javafx.scene.control.Label;

public class ServerStatus {

	private static String runningCSS = "-fx-text-fill: GREEN;";
	private static String startingCSS = "-fx-text-fill: ORANGE;";
	private static String stoppedCSS = "-fx-text-fill: RED;";

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
