/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contenido;

/**
 *
 * @author SAMUEL-PC
 */
public class VerificarContenido {

    public static synchronized void verificar5000(ServerContent serverContent, String[] userList) {
        System.out.println("HOLLA 5000");
        
        if(serverContent.getUsers().size()==0){
            int k;
            for (k = 0; k < userList.length-1; k++) {
                serverContent.setUser(userList[k]);
            }
            serverContent.setNewUser(userList[k]);
        }
        if (serverContent.getUsers().size()< userList.length) {
            System.out.println("if 1");
            int last = userList.length-1;
            serverContent.setNewUser(userList[last]);
        } else if (serverContent.getUsers().size() > userList.length) {
            System.out.println("if 2");
            serverContent.resetUsers();
            int k;
            for (k = 0; k < userList.length-1; k++) {
                serverContent.setUser(userList[k]);
            }
            serverContent.setNewUser(userList[k]);
        }

    }
}
