/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerSocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MIRKO
 */
public class ServidorTCP {
    public static List<Socket> Clientes  = new ArrayList<>();
    
    public static void main(String args[]){
        ServerSocket servidor;
        Socket socket;
        
        try {
            servidor = new ServerSocket(5000);
            System.out.println("Servidor TCP iniciado en el puerto 5000...");
            while(true){
                socket = servidor.accept();
                Clientes.add(socket);
                new HiloServidorTCP("Cliente: "+Clientes.size(), socket).start();
            }            
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    static class HiloServidorTCP extends Thread {
        Socket socket;
        DataInputStream entrada;
        //DataOutputStream salida;
        public HiloServidorTCP(String name, Socket socket){
            super(name);
            this.socket = socket;
        }
        public void run(){
            System.out.println(getName()+" aceptado!!!");
            
            try {    
                while (true) {                    
                    entrada = new DataInputStream(socket.getInputStream());
                    String mensaje = entrada.readUTF();
                    System.out.println(getName() + " dice : " + mensaje);
                }
            } catch (IOException ex) {
                Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}


