package Model;

import java.io.Serializable;

public class Usuario implements Serializable{
	private String username;
	private Mensagem msg;
	private String status;
	
	public Usuario(String username){
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	
}
