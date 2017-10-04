package co.edu.uniquindio.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JOptionPane;
import co.edu.uniquindio.gui.ViwClient;
import java.util.ArrayList;
public class Client {

	public static final int PORT = 3400;
	public static final String SERVER_LOCATION = "localhost";
        public ArrayList<String> users=new ArrayList<>();
        
        boolean isConnectes=false;
	private Socket clientSocket;
	private DataOutputStream salidaDatos;
	private BufferedReader entradaDatos;
	private static String name = " ";
        private String msj="";

	private static ViwClient cliente_gui;	
	
       public void socket(){
           try {

			clientSocket = new Socket(SERVER_LOCATION, PORT);
			salidaDatos = new DataOutputStream(clientSocket.getOutputStream());
			entradaDatos = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
 
			Thread escuchador = new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							if (!clientSocket.isClosed() && entradaDatos.ready())
								cliente_gui.escribir(entradaDatos.readLine());

						} catch (IOException e) {

							//e.printStackTrace();
							cliente_gui.escribir("Comunicación finalizada!!!!");
							break;

						}
					}

				}
			});
			escuchador.start();
                        
                      
                        
                        
		} catch (IOException e) {
			e.printStackTrace();
		}
       }
       
       
       public void mensaje(){
           
           
           while(!msj.equals("")){
                           
                        
                            try {
					if (!clientSocket.isClosed())
						salidaDatos.writeBytes(name + ": " + msj + "\n");
                                        msj="";
                         
				} catch (IOException e) {
					e.printStackTrace();
					break;
				}
                        }
           
       }
       
       public void cerrarConexion(){
         try{
            if (entradaDatos != null)
             entradaDatos.close();
            if (salidaDatos != null)
             salidaDatos.close();
            if (clientSocket != null)
            clientSocket.close();
         }catch(IOException e){
         }
       }
	public Client(ViwClient v) {
            
                cliente_gui = v;
		cliente_gui.escribir("TCP Client");
		name = JOptionPane.showInputDialog(null, "¿Cual es su nombre?");
                socket();
	}

    public void setMsj(String msj) {
        this.msj = msj;
    }

    public static String getName() {
        return name;
    }
    
        
        
}