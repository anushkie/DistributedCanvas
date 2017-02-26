package client;

import java.util.concurrent.CancellationException;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import canvas.Canvas;
import canvas.GUI;

public class ClientMUC {

	private String ipAddress;

	private int portNo;

	private int posX;

	private int posY;

	private DataOutputStream dos;

	private DataInputStream dis;

	LoginGui loginGui;

	LoginPanel loginPanel;

	GUI gui;

	public ClientMUC(String ipAddress, int portNo) {
		this.ipAddress = ipAddress;
		this.portNo = portNo;
	}

	public void connect() throws UnknownHostException, IOException, InterruptedException {

		Socket socket = new Socket(ipAddress, portNo);

		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

		gui = new GUI(dos, dis);
		loginGui = new LoginGui(dos, dis);
		loginPanel = new LoginPanel(dos, dis);
		
		/*
		 * client's display screen
		 */

		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					loginGui.initialize();
					loginGui.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		Scanner scanner = new Scanner(System.in);

		String messageToPrint = dis.readUTF();

		System.out.println(messageToPrint);

		if (messageToPrint.equals("LS")) {
			System.out.println("LOGIN SUCCESSFUL!");
			gui.show();

			// Online users
			String printOnlineUsers = dis.readUTF();
			System.out.println(" " + printOnlineUsers);
			String[] splitString = printOnlineUsers.split("-");
			for (int i = 0; i < splitString.length; i++) {
				System.out.println("Users : " + splitString[i] + " ");

			}

			// reading messages
			MessageHandlerMUC messageHandler = new MessageHandlerMUC(dis, gui.getCanvas());
			Thread thread = new Thread(messageHandler);
			thread.start();

			// writing messages
			while (true) {
				String chatMessage = scanner.nextLine();
				if (chatMessage.equalsIgnoreCase("quit")) {
					break;
				}
				dos.writeUTF('C' + chatMessage);
			}
		} else if (messageToPrint.equals("LF")) {
			System.out.println("Login Unsuccessful");
			
		} else if (messageToPrint.equals("RS")) {
			System.out.println("REGISTRATION SUCCESSFUL!");
			gui.show();
		} else {
			if (messageToPrint.equals("RFE")) {
				System.out.println("EMAIL ID ALREADY EXISTS");
			} else {
				System.out.println("USERNAME ALREADY EXISTS");
			}
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		ClientMUC client = new ClientMUC("localHost", 81);
		try {
			client.connect();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
