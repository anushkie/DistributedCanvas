package server;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.DataTruncation;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import javax.swing.text.html.HTMLDocument.Iterator;
import canvas.GUI;
import server.database.DatabaseService;
import user.UserMUC;

public class ClientHandlerMUC implements Runnable {

	private boolean isSocketRunning;

	private int portNo;

	private Socket socket;

	private DataInputStream dis;

	private DataOutputStream dos;
	
	private String userName;

	private Map<Socket, UserMUC> onlineUsers;
	
	DatabaseService databaseService = new DatabaseService();

	public ClientHandlerMUC(int portNo, Socket socket, Map<Socket, UserMUC> map) throws IOException {

		this.dis = new DataInputStream(socket.getInputStream());
		this.dos = new DataOutputStream(socket.getOutputStream());
		this.onlineUsers = map;
		isSocketRunning = true;
		this.portNo = portNo;
		this.socket = socket;
	}

	@Override
	public void run() {

		while (isSocketRunning) {

			String messageRecieved = "";

			try {
				messageRecieved = dis.readUTF();

				System.out.println(messageRecieved);

				if (messageRecieved.charAt(0) == 'L') {
					System.out.println("Its a login messsage");

					String userName;
					String password;
					String[] splitLoginString = messageRecieved.split("-");
					userName = splitLoginString[0].substring(1);
					password = splitLoginString[1];

					// TODO Database connection and authentication

					if (databaseService.isValidUser(userName, password)) {
						System.out.println(splitLoginString);
						UserMUC user = new UserMUC(userName, password, dis, dos);
						dos.writeUTF("LS");
						onlineUsers.put(socket, user);
						
						String listOfOnlineUsers = "";

						for(Map.Entry<Socket, UserMUC> entry : onlineUsers.entrySet()) {
				
							listOfOnlineUsers = listOfOnlineUsers + entry.getValue().getUsername();
							System.out.println("list of users : " + listOfOnlineUsers + "-");
						}
						
						dos.writeUTF(listOfOnlineUsers);
						
					} else {
						dos.writeUTF("LF");
					}

				} else {
					if (messageRecieved.charAt(0) == 'R') {
						System.out.println("its a Register message");

						String[] splitRegisterString = messageRecieved.split("-");
						String firstName = splitRegisterString[0].substring(1);
						String lastName = splitRegisterString[1];
						String username = splitRegisterString[2];
						String password = splitRegisterString[3];
						String emailId = splitRegisterString[4];
						String gender = splitRegisterString[5];

					    System.out.println("FIRST NAME : " + firstName);
						System.out.println("LAST NAME : " + lastName);
						System.out.println("USERNAME : " + username);
						System.out.println("PASSWORD :" + password);
						System.out.println("EMAIL ID :" + emailId);
						System.out.println("GENDER : " + gender);

						if(databaseService.doesEmailExists(emailId)) {
							dos.writeUTF("RFE");
							dos.flush();
						} else {
							if(databaseService.doesUserNameExists(username)) {
								dos.writeUTF("RFU");
							} else {
								databaseService.registerUser(firstName, lastName, username, password, emailId, gender);
								dos.writeUTF("RS");
								dos.flush();
								UserMUC user = new UserMUC(username, password, dis, dos);
								onlineUsers.put(socket, user);
							}
						}
						

					} else 
						if (messageRecieved.charAt(0) == 'C') {
							System.out.println("This is a chat message..");
							
							// Sending the message to all the clients connected
							for(Map.Entry<Socket, UserMUC> entry : onlineUsers.entrySet()) {
								if(entry.getKey() != socket) {
									UserMUC userMUC = entry.getValue();
									userMUC.getDos().writeUTF(userName + " : " + messageRecieved.substring(1));
								}
							}
							
							System.out.println("Message : " + messageRecieved.substring(1));
						} else {
							System.out.println("this is a point message");
							//messageRecieved = messageRecieved.substring(1);
							for(Map.Entry<Socket, UserMUC> entry : onlineUsers.entrySet()) {
									System.out.println("Online users : " + entry.getValue().getUsername());
								if(entry.getKey() != socket) {
									UserMUC userMUC = entry.getValue();
									userMUC.getDos().writeUTF(messageRecieved);
								}
							}
						}
				}

			} catch (IOException e) {
				e.printStackTrace();
				isSocketRunning = false;
				System.out.println(e);
				System.out.println("Thread has stoppped dude! FO !");
				onlineUsers.remove(socket);
			}
		}
	}
}