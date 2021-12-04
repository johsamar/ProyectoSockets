/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionServer;

import contenido.ServerContent;
import contenido.VerificarContenido;
import demomessengerserver.SocketController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SAMUEL-PC
 */
public class SocketControlMessages {
    
    private String host;
    private int puerto;
    private SocketController socketController;
    private final SocketController socketControl;
    
    private ServerContent serverContent;
    
    public SocketControlMessages() {
        host = "127.0.0.1";
        puerto = 21212; 
        try {
            socketController = new SocketController(host,puerto);
        } catch (IOException ex) {
            Logger.getLogger(SocketControlMessages.class.getName()).log(Level.SEVERE, null, ex);
        }
        socketControl=socketController;
        serverContent = new ServerContent();
    }
    
    public ArrayList<String> getUsers() {
        return serverContent.getUsers();
    }

    public String getMessage4All() {
        return serverContent.getLastMessageAll();
    }

    public String getMessageInPrivate() {
        return serverContent.getLastMessagePrivate();
    }
    
    public void readText() {
        this.socketController.start(() -> {
            while (true) {
                read(socketController.readText());
            }
        });
    }
    
    public void read(String dato) {
        String[] datos = dato.split(" ");
        System.out.println("- " + datos[0] + " * " + datos[1]);
        
        switch(datos[0]){
            case "1001":
                serverContent.setErrorMessage(datos[1]+" "+datos[2]);
            break;
            case "5000":
                System.out.println("entro user");
                String[] userList = datos[1].split(";");
                VerificarContenido.verificar5000(serverContent, userList);
            break;
            case "4010":
                System.out.println("Entro private");
                
                String messagePrivate = "";
                for (int i = 1; i < datos.length; i++) {
                    messagePrivate += datos[i];
                }
                serverContent.setMessagePrivate(messagePrivate);
            break;

            case "2000":
            case "2010":
                System.out.println("Entro all");
                String messagePublic = "";
                for (int i = 1; i < datos.length; i++) {
                    messagePublic += datos[i];
                      System.out.println(datos[i]);
                }
                serverContent.setMessageAll(messagePublic);
            break;
        }
    }

    public Thread getThread() {
        return this.socketController.getThread();
    }
    
    public void RegisterUser(String usuario) {
        String command = "REGISTER " + usuario;
        if (this.socketController != null) {
            socketController.writeText(command);
        }
    }

    public void getActiveUsers() {
        String command = "GETUSERNAMES";
        if (socketController != null) {
            socketControl.writeText(command);
        }
    }

    public void sendPrivateMessage(String message, String user) {
        String command = "SEND " + user + " " + message;
        socketControl.writeText(command);
    }

    public void sendPublicMessage(String message) {
        String command = "SENDALL " + message;
        socketControl.writeText(command);
    }

    public void exit() {
        String command = "QUIT";
        socketController.writeText(command);
    }
    
    
    public boolean thereAreNewUsers(){
        return serverContent.thereAreNewUsers();
    }
    public boolean isNewPublicMessage(){
        return serverContent.isNewPublicMessage();
    }
    public boolean isNewPrivateMessage(){
        return serverContent.isNewPrivateMessage();
    }
    
    public String isUserValid(){
        String content = serverContent.thereAreErrorMessage();
        if(content!=null){
            return content;
        }
        return null;
    }
    
}
