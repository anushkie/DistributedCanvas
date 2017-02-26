package canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.JComponent;

import client.ClientMUC;

public class Canvas extends JComponent {

	// Image in which we're going to draw
	private Image image;
	// Graphics2D object ==> used to draw on
	private Graphics2D g2;
	// Mouse coordinates
	private int currentX, currentY, oldX, oldY;

	private int posX;

	private int posY;

	private DataOutputStream dos;

	private DataInputStream dis;
	
	private int currentColor;

	public Canvas(DataOutputStream dos, DataInputStream dis) {
		this.dos = dos;
		this.dis = dis;
		this.currentColor = 0;
		
		setDoubleBuffered(false);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// save coord x,y when mouse is pressed
				oldX = e.getX();
				oldY = e.getY();
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				// coord x,y when drag mouse
				currentX = e.getX();
				currentY = e.getY();
				

				if (g2 != null) {
					g2.drawLine(oldX, oldY, currentX, currentY);
					repaint();

					try {
						ActionListener actionListener = new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							}
						};
						String messageToBeSent = "P" + oldX + "#" + oldY + "#" + currentX + "#" + currentY + "#" + currentColor;
						dos.writeUTF(messageToBeSent);
						System.out.println(messageToBeSent);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					oldX = currentX;
					oldY = currentY;
				}
			}
		});
	}

	public void paintComponent(Graphics g) {

		if (image == null) {
			image = createImage(getSize().width, getSize().height);
			g2 = (Graphics2D) image.getGraphics();
			// enable antialiasing
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			// clear draw area
			clear();
		}
		g.drawImage(image, 0, 0, null);
	}

	public int getCoordinateX(int x) {
		int coordX = x;
		return coordX;
	}

	public int getCoordinateY(int y) {
		int coordY = y;
		return coordY;
	}

	public void clear() {
		g2.setPaint(Color.white);
		// draw white on entire draw area to clear
		g2.fillRect(0, 0, getSize().width, getSize().height);
		g2.setPaint(Color.black);
		repaint();
		currentColor = 1;
	}

	public void red() {
		g2.setPaint(Color.red);
		currentColor = 2;
	}

	public void black() {
		g2.setPaint(Color.black);
		currentColor = 0;
	}

	public void magenta() {
		g2.setPaint(Color.magenta);
		currentColor = 3;
	}

	public void green() {
		g2.setPaint(Color.green);
		currentColor = 4;
	}

	public void blue() {
		g2.setPaint(Color.blue);
		currentColor = 5;
	}

	public void yellow() {
		g2.setPaint(Color.yellow);
		currentColor = 6;
	}

	public void draw(int x, int y, int newX, int newY) {
		g2.drawLine(x, y, newX, newY);
		repaint();
	}
}
