package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import appdata.AppStaticData;
import connection.Connection;
import util.Logger;

public class Server implements Runnable{
	private Map<String, ServerThread> srvThrds = new HashMap<>();

	public void startServer() {
		int portNo = 4200;
		try (ServerSocket ss = new ServerSocket(portNo)) {
			Logger.log("Server started at " + ss.getInetAddress().getLocalHost() + " on port " + portNo);
			AppStaticData.SERVER_SOCKET = ss;
			while (true) {
				Socket socket = ss.accept();
				String clientName = socket.getInetAddress().getHostName();
				Logger.log(clientName + " connected");
				AppStaticData.CLIENT_CONNECTIONS.add(1);
				ServerThread st;
				if (srvThrds.containsKey(clientName)) {
					st = srvThrds.get(clientName);
					if(st.isAlive())
						continue;
				}
				st = new ServerThread(this, socket, clientName);
				st.setDaemon(true);
				srvThrds.put(clientName, st);
				st.start();
			}
		} catch (IOException e) {
//			e.printStackTrace();
			AppStaticData.SERVER_SOCKET = null;
			AppStaticData.SERVER_RUNNING_PROPERTY.setValue(false);;
		}
	}

	public void sendMessage(String from, String to, String msg) {
		Logger.log("sending msg=>from:"+from+"|to:"+to+"|msg:"+msg);
		ServerThread serverThread = srvThrds.get(to);
		if (serverThread == null)
			return;
		DataOutputStream dos = serverThread.getDataOutputStream();
		try {
			dos.writeUTF(from+":"+msg);
			dos.flush();
			Logger.log("flushed=>from:"+from+"|to:"+to+"|msg:"+msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendConfirmation(String requestor, String request) {
		Logger.log(requestor+" requesting connection to "+request);
		ServerThread requestorThread = srvThrds.get(requestor);
		if (requestorThread == null) {
			Logger.log("request from unregistred node:"+requestor);
			return;
		}
		String msg = "?0";
		if (srvThrds.containsKey(request)) {
			msg = "?1:"+request;
			Logger.log(request+" found");
		}
		else {
			Logger.log(request+" not found");
		}
		DataOutputStream dos = requestorThread.getDataOutputStream();
		try {
			dos.writeUTF(msg);
			dos.flush();
			Logger.log("sending confirmation "+ msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<String, ServerThread> getUserThreads() {
		return srvThrds;
	}

	@Override
	public void run() {
		startServer();
	}
}