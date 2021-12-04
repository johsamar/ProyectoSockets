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
    private String currentUser;
    
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
        currentUser = "";
    }

    public String getCurrentUser() {
        return currentUser;
    }
    
    public synchronized ArrayList<String> getUsers() {
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
        //System.out.println("- " + datos[0] + " * " + datos[1]);
        
        switch(datos[0]){
            case "1001":
                currentUser = "";
                serverContent.setErrorMessage(datos[1]+" "+datos[2]);
            break;
            case "5000":
                System.out.println("entro user");
                String[] userList = datos[1].split(";");
                serverContent.setCurrentUser(userList[userList.length-1]);
                VerificarContenido.verificar5000(serverContent, userList);
                
            break;
            case "4000":
            break;
            case "4010":
                System.out.println("Entro private");
                
                String messagePrivate = "";
                for (int i = 1; i < datos.length; i++) {
                    if(i==1){
                        messagePrivate += datos[i]+": ";
                    }else{
                        messagePrivate += datos[i]+" ";
                    }
                    
                }
                serverContent.setMessagePrivate(messagePrivate);
            break;

            case "2000":
                break;
            case "2010":
                System.out.println("Entro all");
                String messagePublic = "";
                for (int i = 1; i < datos.length; i++) {
                    if(i==1){
                    messagePublic += datos[i]+": ";
                    }else{
                        messagePublic += datos[i]+" ";
                    }
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
            currentUser = usuario;
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
        serverContent.setMessagePrivate("Tu a "+user+": "+message);
    }

    public void sendPublicMessage(String message) {
        String command = "SENDALL " + message;
        socketControl.writeText(command);
        serverContent.setMessageAll("Tú: "+message);
    }

    public void exit() {
        String command = "QUIT";
        socketController.writeText(command);
    }
    
    
    public synchronized boolean thereAreNewUsers(){
        return serverContent.thereAreNewUsers();
    }
    public synchronized boolean isNewPublicMessage(){
        return serverContent.isNewPublicMessage();
    }
    public synchronized boolean isNewPrivateMessage(){
        return serverContent.isNewPrivateMessage();
    }
    
    public synchronized String isUserValid(){
        String content = serverContent.thereAreErrorMessage();
        if(content!=null){
            return content;
        }
        return null;
    }
    
}
