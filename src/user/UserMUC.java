package user;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class UserMUC {

private String username;
	
	private String password;
	
	private DataInputStream dis;
	
	private DataOutputStream dos;
	
	public UserMUC(String username, String password, DataInputStream dis, DataOutputStream dos) {
		this.dis = dis;
		this.dos = dos;
		this.username = username;
		this.password = password;
	}
	
	
	 public DataInputStream getDis() {
		return dis;
	}


	public void setDis(DataInputStream dis) {
		this.dis = dis;
	}


	public DataOutputStream getDos() {
		return dos;
	}


	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}


	public String getUsername() {
		return username;
	}
}
