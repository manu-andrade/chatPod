package Controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private InputStream input = null;
	private OutputStream output = null;

	public ChatServer(){

	}

	public void creatSocket(){
		try {
			//cria o servidor
			serverSocket = new ServerSocket(7007);
			System.out.println("Servidor rodando!");

			while(true){
				socket = serverSocket.accept();
				input = socket.getInputStream();
				output = socket.getOutputStream();
				System.out.println("Conectado!");
				creatReadThread();
				creatWriteThread();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//thread para a escrida de dados
	private void creatWriteThread() {
		Thread writeThread = new Thread() {
			public void run(){
				
				while(socket.isConnected()){
					try {
						BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
						sleep(100);
						
						String typedMessage = inputReader.readLine();
						
						if(typedMessage != null && typedMessage.length() > 0){
							synchronized (socket) {
								output.write(typedMessage.getBytes("UTF-8"));
								sleep(100);
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		};
		writeThread.setPriority(Thread.MAX_PRIORITY);
		writeThread.start();
	}

	//thread para leitura das mensagens 
	private void creatReadThread() {
		//inicializa a thread
		Thread readThread = new Thread(){
			public void run(){

				while(socket.isConnected()){
					try {
						byte[] readBuffer = new byte[500];
						int num = input.read(readBuffer);

						if(num>0){
							byte[] arrayByte = new byte[500];
							System.arraycopy(readBuffer, 0, arrayByte, 0, num);

							String msgRecebida = new String(arrayByte, "UTF-8");
							System.out.println("Mensagem Recebida: " +msgRecebida);
						}else{
							notify();
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		};
		readThread.setPriority(Thread.MAX_PRIORITY);
		readThread.start();
	}
	
	public static void main(String[] args) {
		ChatServer chatServer = new ChatServer();
		chatServer.creatSocket();
	}
}
