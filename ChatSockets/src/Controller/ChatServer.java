package Controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Model.Usuario;

//http://www.mballem.com/post/chat-multi-usuarios-com-socket/
//http://pirate.shu.edu/~wachsmut/Teaching/CSAS2214/Virtual/Lectures/chat-client-server.html

public class ChatServer {
	//id unico para cada conexao
//	private static int uniqID;
	private ServerSocket serverSocket = null;
	
	private Socket socket = null;
	private InputStream input = null;
	private OutputStream output = null;
	private ArrayList<Usuario> usuarios;
	

	public ChatServer(){

	}

	
}





