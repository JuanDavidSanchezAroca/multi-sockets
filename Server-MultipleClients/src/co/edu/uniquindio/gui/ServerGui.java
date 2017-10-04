package co.edu.uniquindio.gui;

import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ServerGui {

	public JFrame frmServer;
	private static TextArea txaMensajes;

	/**
	 * Create the application.
	 */

	public ServerGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmServer = new JFrame();
		frmServer.setTitle("Server");
		frmServer.setBounds(100, 100, 347, 307);
		frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServer.setResizable(false);
		frmServer.setName("SERVER");
		frmServer.getContentPane().setLayout(null);

		txaMensajes = new TextArea();
		txaMensajes.setEditable(false);
		txaMensajes.setBounds(10, 69, 318, 167);
		frmServer.getContentPane().add(txaMensajes);

		JLabel lblMensajes = new JLabel("Mensajes:");
		lblMensajes.setBounds(23, 38, 92, 15);
		frmServer.getContentPane().add(lblMensajes);

	}

	public void escribir(String texto) {
		txaMensajes.append(texto + "\n");
	}
}
