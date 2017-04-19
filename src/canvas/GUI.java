package canvas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author anushka
 *
 */
public class GUI {

	 private DataOutputStream dos;
	 
	 private Socket socket;
	  
	 private DataInputStream dis;
	
	  
	JButton clearBtn, yellowBtn, blackBtn, blueBtn, greenBtn, redBtn, magentaBtn;
	Canvas canvas;

	ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == clearBtn) {
				canvas.clear();
			} else if (e.getSource() == blackBtn) {
				canvas.black();
			} else if (e.getSource() == blueBtn) {
				canvas.blue();
			} else if (e.getSource() == greenBtn) {
				canvas.green();
			} else if (e.getSource() == redBtn) {
				canvas.red();
			} else if (e.getSource() == magentaBtn) {
				canvas.magenta();
			} else if(e.getSource() == yellowBtn) {
				canvas.yellow();
			}
		}
	};

	public GUI(DataOutputStream dos, DataInputStream dis) throws IOException {
		this.dos = dos;
		this.dis = dis;
		
		clearBtn = new JButton("Clear");
		blackBtn = new JButton("Black");
		blueBtn = new JButton("Blue");
		greenBtn = new JButton("Green");
		redBtn = new JButton("Red");
		magentaBtn = new JButton("Magenta");
		yellowBtn = new JButton("yellow");
	}

	public void show() {
		// create main frame
		// System.out.println(x + " " + y);
		JFrame frame = new JFrame("Canvas");

		Container content = frame.getContentPane();
		// set layout on content pane
		content.setLayout(new BorderLayout());
		// create draw area
		canvas = new Canvas(dos, dis);
		
		
		// add to content pane
		content.add(canvas, BorderLayout.CENTER);
		JPanel controls = new JPanel();
		frame.addComponentListener(new ComponentListener() {

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				// TODO Auto-generated method stub
			/*	System.out.println("Frame size : " + frame.getSize());
				System.out.println("Canvas size : " + canvas.getSize());
				System.out.println("Control panel size : " + controls.getSize());
				canvas.validate();*/
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		// create controls to apply colors and call clear feature
		/*JPanel controls = new JPanel();*/

		clearBtn.addActionListener(actionListener);
		blackBtn.addActionListener(actionListener);
		blueBtn.addActionListener(actionListener);
		greenBtn.addActionListener(actionListener);
		redBtn.addActionListener(actionListener);
		magentaBtn.addActionListener(actionListener);
		yellowBtn.addActionListener(actionListener);

		// add to panel
		controls.add(greenBtn);
		controls.add(blueBtn);
		controls.add(blackBtn);
		controls.add(redBtn);
		controls.add(magentaBtn);
		controls.add(yellowBtn);
		controls.add(clearBtn);

		// add to content pane
		content.add(controls, BorderLayout.NORTH);

		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// show the swing paint result
		frame.setVisible(true);
	}

	/*public static void main(String[] args) throws IOException {
		new GUI(null, null).show();
	}*/

	public Canvas getCanvas() {
		
		return canvas;
	}
}