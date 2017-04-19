package client;

import java.io.DataInputStream;
import java.io.IOException;

import canvas.Canvas;

public class MessageHandlerMUC implements Runnable{
	
	private DataInputStream dis;

	private Canvas canvas;
	
	public MessageHandlerMUC(DataInputStream dis, Canvas canvas) {
		this.dis = dis;
		this.canvas = canvas;
	}
	
	@Override
	public void run() {
		
		while(true) {
			String messageToBePrinted = "";
			try {
				messageToBePrinted = dis.readUTF();
				if(messageToBePrinted.startsWith("P")) {
					messageToBePrinted = messageToBePrinted.substring(1);
					//System.out.println(messageToBePrinted);
					String[] coordinates = messageToBePrinted.split("#");
					int x = Integer.parseInt(coordinates[0]);
					int y = Integer.parseInt(coordinates[1]);
					
					int xx = Integer.parseInt(coordinates[2]);
					int yy = Integer.parseInt(coordinates[3]);
					
					int color = Integer.parseInt(coordinates[4]);
		
					switch(color) {
						case 0 : canvas.black();
						break;
						case 1 : canvas.clear();
						break;
						case 2 : canvas.red();
						break;
						case 3 : canvas.magenta();
						break;
						case 4 : canvas.green();
						break;
						case 5 : canvas.blue();
						break;
						case 6 : canvas.yellow();
						break;
					}
					
					canvas.draw(x,y,xx,yy);
				} else { 
					System.out.println(messageToBePrinted);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
