/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosockets;

import conexionServer.SocketControlMessages;

/**
 *
 * @author SAMUEL-PC
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SocketControlMessages scm = new SocketControlMessages();
        //while(true){
        scm.readText();
        System.out.println("SENDALL");
        scm.RegisterUser("samuel");
        scm.sendPrivateMessage("Hola tu", "usuario1");
        scm.sendPublicMessage("De mi para el mundo");
        scm.getActiveUsers();
        while(true){
            
        }

    }

}
