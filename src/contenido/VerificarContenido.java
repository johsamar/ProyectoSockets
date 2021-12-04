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

    public static void verificar5000(ServerContent serverContent, String[] userList) {
        synchronized (serverContent) {
            System.out.println("HOLLA 5000");
            if (serverContent.getCurrentUsers() < userList.length) {
                System.out.println("if 1");
                int size = userList.length - 1;
                serverContent.setUser(userList[size]);
            } else if (serverContent.getCurrentUsers() > userList.length) {
                System.out.println("if 2");
                int k = 0;
                while (serverContent.userExist(userList[k])) {
                    k++;
                }
                serverContent.deleteUser(userList[k]);
            }
        }
    }
}
