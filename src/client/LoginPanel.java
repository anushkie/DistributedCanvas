package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class LoginPanel extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	
	public String username;
	
	public String password;
	
	private DataInputStream dis;
	
	private DataOutputStream dos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//LoginPanel frame = new LoginPanel(dos, dis);
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginPanel(DataOutputStream dos, DataInputStream dis) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\java2\\Multi User Canvas\\resources\\bkgrnd.jpg"));
		
		this.dos = dos;
		this.dis = dis;
		username = "";
		password = "";
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblLogin.setBounds(172, 11, 92, 25);
		contentPane.add(lblLogin);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel.setBounds(55, 84, 92, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(55, 156, 79, 17);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				  username =  textField.getText();
			      System.out.println("USERNAME :" + username);
			      password = passwordField.getText();
			      System.out.println("PASSWORD : " + password);
			      try {
			    	  System.out.println("Dis is running");
					dos.writeUTF("L" + username + "-" + password);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 18));
		btnNewButton.setBounds(172, 228, 109, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(197, 78, 152, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(197, 150, 152, 25);
		contentPane.add(passwordField); 
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("E:\\java2\\Multi User Canvas\\resources\\b3.jpg"));
		lblNewLabel_2.setBounds(0, 0, 444, 272);
		contentPane.add(lblNewLabel_2);
		 
	
	}
}
