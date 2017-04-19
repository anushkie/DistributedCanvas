package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

import user.UserMUC;

/**
 * 
 * @author Anushka
 *
 */

public class ServerMUC {

	private boolean isRunning;
	
	private int counter;
	
	private int portNo;
		
	private ConcurrentHashMap<Socket, UserMUC> map;
	
 	public ServerMUC(int portNo) {
		this.portNo = portNo;
		this.counter = 0;
		this.map = new ConcurrentHashMap<>();
	
	}
	
	public void start() throws IOException {
		
		isRunning = true;
		
		ServerSocket serverSocket = new ServerSocket(portNo);
		while(isRunning) {
			
			System.out.println("Server is running");
			
			Socket socket = serverSocket.accept();
			System.out.println("A client is connected with remote address : " + socket.getRemoteSocketAddress());
			
			ClientHandlerMUC handler = new ClientHandlerMUC(81, socket, map);
			Thread thread = new Thread(handler);
			thread.start();
		}
	}
	
	public static void main(String[] args) throws IOException {
	
		new ServerMUC(81).start();
	}
}
