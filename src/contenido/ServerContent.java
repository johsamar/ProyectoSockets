/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contenido;

import java.util.ArrayList;

/**
 *
 * @author SAMUEL-PC
 */
public class ServerContent {
    private ArrayList<String> users;
    private ArrayList<String> messagePrivate;
    private ArrayList<String> messageAll;
    
    private int currentUsers;
    private int currentmessagePrivate;
    private int currentmessageAll;
    
    private String errorMessage;

    public ServerContent() {
        users = new ArrayList<>();
        messagePrivate = new ArrayList<>();
        messageAll = new ArrayList<>();
        
        currentUsers = 0;
        errorMessage="";
    }

    public int getCurrentUsers() {
        return currentUsers;
    }
    
    public void setUser(String newUsers) {
        currentUsers = users.size();
        users.add(newUsers);
    }
    public boolean userExist(String user){
        return users.contains(user);
    }
    public void deleteUser(String previousUsers) {
        currentUsers = users.size();
        users.remove(previousUsers);
    }
    
    public void setMessagePrivate(String newMessagePrivate) {
        currentmessagePrivate = messagePrivate.size();
        messagePrivate.add(newMessagePrivate);
    }

    public void setMessageAll(String newMessageAll) {
        currentmessageAll = messageAll.size();
        messageAll.add(newMessageAll);
    }
    
      
    public ArrayList<String> getUsers() {
        return users;
    }

    public String getLastMessagePrivate() {
        int last = messagePrivate.size()-1;
        return messagePrivate.get(last);
    }

    public String getLastMessageAll() {
        int last = messageAll.size()-1;
        return messageAll.get(last);
    }
    
    public boolean thereAreNewUsers(){
        if(users.size()==currentUsers){
            return true;
        }
        currentUsers = users.size()-1;
        return false;
    }
    
    public boolean isNewPrivateMessage(){
        if(messagePrivate.size()==currentmessagePrivate){
            return true;
        }
        return false;
    }
    public boolean isNewPublicMessage(){
        if(messageAll.size()==currentmessageAll){
            return true;
        }
        return false;
    }

    public String thereAreErrorMessage() {
        if(errorMessage.length()==0){
            String error = errorMessage;
            errorMessage="";
            return error;
        }
        return null;
    }

    public void setErrorMessage(String newErrorMessage) {
        errorMessage = newErrorMessage;
    }
    
}
