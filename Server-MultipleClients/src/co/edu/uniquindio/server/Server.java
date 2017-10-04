package co.edu.uniquindio.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import co.edu.uniquindio.gui.ServerGui;

public class Server {

	public final static int PORT = 3400;

	private ServerSocket socketBienvenida;
	private ArrayList<ManejadorComunicacion> manejadorClientes;
	private static ServerGui server_gui;

	public static void main(String[] args) {

		server_gui = new ServerGui();
		server_gui.frmServer.setVisible(true);
		new Server();
	}

	public Server() {
		
		server_gui.escribir("Servidor iniciado en el puerto 3400");
		manejadorClientes = new ArrayList<ManejadorComunicacion>();

		try {
		     socketBienvenida = new ServerSocket(3400);
                     server_gui.escribir("Conexion entrante");
			while (true) {
                              
				Socket cliente = socketBienvenida.accept();
				
				manejadorClientes.add(new ManejadorComunicacion(cliente, this, server_gui));
				manejadorClientes.get(manejadorClientes.size() - 1).start();

				for (int i = 0; i < manejadorClientes.size(); i++) {
					if (!manejadorClientes.get(i).isAlive()) {
						manejadorClientes.remove(i);
						i--;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

				if (socketBienvenida != null)
					socketBienvenida.close();
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}

	public void enviarMensajeATodos(String mensaje) {
		for (int i = 0; i < manejadorClientes.size(); i++) {
			if (manejadorClientes.get(i).isAlive()) {
				manejadorClientes.get(i).enviarMensaje(mensaje);
			}
		}

	}

}