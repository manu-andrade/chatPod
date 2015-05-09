package Controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ChatClient {
	private Socket socket = null;
	private InputStream input = null;
	private OutputStream output = null;
	
	public ChatClient(){
		
	}
	
	public void creatSocket(){
		
		try {
			socket = new Socket("192.168.2.6", 3339);
			System.out.println("Socket Cliente conectdo!");
			
			input = socket.getInputStream();
			output = socket.getOutputStream();
			
			creatReadThread();
			creatWriteThread();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void creatReadThread() {
		Thread readThread = new Thread(){
			
			public void run() {
				
				while(socket.isConnected()){
					
					try {
						
						byte[] readBuffer = new byte[500];
						int num = input.read(readBuffer);
						
						if (num > 0) {
							byte[] arrayBytes = new byte[500];
							System.arraycopy(readBuffer, 0, arrayBytes, 0, num);
							
							String msgRecebida = new String(arrayBytes, "UTF-8");
							System.out.println("Mensagem Recebida: " +msgRecebida);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		readThread.setPriority(Thread.MAX_PRIORITY);
		readThread.start();
	}

	private void creatWriteThread() {
		Thread writeThread = new Thread(){
			
			public void run() {
				
				while (socket.isConnected()) {
					
					try {
						BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
						sleep(100);
						String typedMsg = inputReader.readLine();
						if(typedMsg != null && typedMsg.length() > 0){
							synchronized (socket) {
								output.write(typedMsg.getBytes("UTF-8"));
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
	
	public static void main(String[] args) {
		ChatClient myChatClient = new ChatClient();
		myChatClient.creatSocket();
	}
}
