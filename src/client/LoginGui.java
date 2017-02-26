package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.fabric.xmlrpc.base.Data;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.awt.event.ActionEvent;

public class LoginGui {

	private JPanel contentPane;
	public JFrame frame;
	
	public JFrame frame2;
	
	public JButton btnNewButton;

	private DataInputStream dis;
	
	private DataOutputStream dos;
	
	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
	public LoginGui(DataOutputStream dos, DataInputStream dis) {
		this. dos = dos;
		this.dis = dis;
		contentPane = new JPanel();
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					LoginGui window = new LoginGui(null,null);
					window.initialize();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		contentPane.setLayout(null);
		
		frame = new JFrame("Welcome");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblMultiUserCanvas = new JLabel("MULTI USER CANVAS");
		lblMultiUserCanvas.setFont(new Font("Tempus Sans ITC", Font.BOLD, 28));
		lblMultiUserCanvas.setBounds(62, 11, 306, 30);
		frame.getContentPane().add(lblMultiUserCanvas);
		
		btnNewButton = new JButton("LOGIN");
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 22));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				LoginPanel loginPanel = new LoginPanel(dos, dis);
				loginPanel.setVisible(true);
			}
		});
		
		
		btnNewButton.setBounds(155, 91, 116, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("REGISTER");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				RegisterPanel registerPanel = new RegisterPanel(dos, dis);
				registerPanel.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Calibri", Font.BOLD, 20));
		btnNewButton_1.setBounds(155, 155, 116, 30);
		frame.getContentPane().add(btnNewButton_1);
	
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("E:\\java2\\Multi User Canvas\\resources\\12345567.jpg"));
		lblNewLabel_2.setBounds(0, 0, 444, 272);
		frame.getContentPane().add(lblNewLabel_2);
		
	}
}
