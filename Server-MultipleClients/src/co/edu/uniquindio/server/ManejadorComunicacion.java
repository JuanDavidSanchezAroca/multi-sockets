package co.edu.uniquindio.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import co.edu.uniquindio.gui.ServerGui;

public class ManejadorComunicacion extends Thread{


	private Socket miCliente;
	private Server miServer;
	private ServerGui server_gui;
	private DataOutputStream salidaACliente;
	private BufferedReader entradaDeCliente;
	
	public ManejadorComunicacion(Socket cliente, Server server, ServerGui gui) {
		this.miCliente = cliente;
		this.miServer = server;
		this.server_gui=gui;
		try {			
			entradaDeCliente = new BufferedReader(new InputStreamReader(miCliente.getInputStream()));
			salidaACliente = new DataOutputStream(miCliente.getOutputStream());
			
		} catch (IOException e) {
					} 
	
	}
	
	@Override
	public void run() {
		super.run();
		boolean activo = true;
		while(activo){
			if(miCliente.isClosed()){
				activo = false;
				continue;
			}else{
				try {
						
						String mensaje = entradaDeCliente.readLine();
						if(mensaje!=null){
							server_gui.escribir(mensaje);
							miServer.enviarMensajeATodos(mensaje);
						}else {
							
						}
						
				} catch (IOException e) {
				
				
				}
			}			
		}		
	}
	
	

	

	public Socket getMiCliente() {
		return miCliente;
	}

	public void setMiCliente(Socket miCliente) {
		this.miCliente = miCliente;
	}

	public Server getMiServer() {
		return miServer;
	}

	public void setMiServer(Server miServer) {
		this.miServer = miServer;
	}

	public boolean  enviarMensaje(String mensaje) {
		if(!miCliente.isClosed()){
			try {
				salidaACliente.writeBytes(mensaje+"\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return false;
		
	}
}
