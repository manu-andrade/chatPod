package Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mensagem implements Serializable{
	private Usuario nomeEmissor;
	private String msg;
	private SimpleDateFormat dataFormatada;
	private String hora;
	private String data;
	
	public Mensagem(String msg, Usuario nomeEmissor){
		this.msg = msg;
		this.nomeEmissor = nomeEmissor;
		data = dataFormatada.format(new Date());
	}

	public Usuario getNomeEmissor() {
		return nomeEmissor;
	}

	public void setNomeEmissor(Usuario nomeEmissor) {
		this.nomeEmissor = nomeEmissor;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public SimpleDateFormat getDataFormatada() {
		return dataFormatada;
	}

	public void setDataFormatada(SimpleDateFormat dataFormatada) {
		this.dataFormatada = dataFormatada;
	}

	public String getHora() {
		SimpleDateFormat horaFormatada = new SimpleDateFormat("hh:mm:ss");
		return horaFormatada.format(new Date(System.currentTimeMillis()));
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
}
