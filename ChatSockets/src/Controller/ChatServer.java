package Controller;

import java.io.InputStream;
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
			serverSocket = new ServerSocket(1234);

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
	}
}
