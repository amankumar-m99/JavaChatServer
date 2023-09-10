package connection;

public class Connection {
	public String ip;
	public String logs;

	public Connection(String ip) {
		this(ip, "empty");
	}

	public Connection(String ip, String logs) {
		this.ip = ip;
		this.logs = logs;
	}
}
