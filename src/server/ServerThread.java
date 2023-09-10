package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import appdata.AppStaticData;
import connection.Connection;
import util.Logger;

class ServerThread extends Thread {

	private Server server;
	private Socket socket;
	private String clientName;
	private Connection connection;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;

	public ServerThread(Server server, Socket socket, String clientName) throws IOException {
		this.server = server;
		this.socket = socket;
		this.clientName = clientName;
		dataInputStream = new DataInputStream(socket.getInputStream());
		dataOutputStream = new DataOutputStream(socket.getOutputStream());
		connection = new Connection(clientName);
		AppStaticData.ACTIVE_CONNECTIONS.add(connection);
	}

	@SuppressWarnings("deprecation")
	public void run() {
		while(true) {
			try {
				if(socket.isClosed() || !socket.isConnected())
					break;
				DataInputStream dis=new DataInputStream(socket.getInputStream());
				String str = "";
				try {
					str = (String)dis.readUTF();
				}
				catch(SocketException e) {
					break;
				}
				catch (EOFException e) {
//					e.printStackTrace();
					break;
				}
				Logger.log("Received: "+str+ "from" + clientName);
				String target = "";
				if(str.startsWith("?"))
				{
					target = str.substring(1).trim();
					server.sendConfirmation(clientName, target);
					continue;
				}
				int index = str.indexOf(":");
				if(index == -1)
					break;
				target = str.substring(0, index).trim();
				String message = str.substring(index+1).trim();
				server.sendMessage(clientName, target, message);
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}  
		}
		Logger.log(clientName+" disconnected");
		AppStaticData.ACTIVE_CONNECTIONS.remove(connection);
		server.getUserThreads().remove(clientName);
		this.stop();
	}

	public DataInputStream getDataInputStream() {
		return dataInputStream;
	}

	public DataOutputStream getDataOutputStream() {
		return dataOutputStream;
	}
}