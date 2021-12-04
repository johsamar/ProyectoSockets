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
        
        if (serverContent.getCurrentUsers() < userList.length-1) {
            System.out.println("if 1");
            serverContent.resetUsers();
            for(String userL: userList){
                serverContent.setUser(userL);
            }
        } else if (serverContent.getCurrentUsers() > userList.length-1) {
            System.out.println("if 2");
            int k = 0;
            while (k<userList.length & !serverContent.getUsers().contains(userList[k])) {
                k++;
            }
            serverContent.deleteUser(userList[k]);
        }
        
    }
}
