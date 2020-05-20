package interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageManagement {
    private static MessageManagement messageManagement;
    List<String> messages;
    List<User> connectedClients;
    HashMap<User,Integer> unreadMessage;

    MessageManagement(){
        messages = new ArrayList<>();
        connectedClients = new ArrayList<>();
        unreadMessage = new HashMap<>();
        for(User u : Chat.getChat().getAllUser()){
            unreadMessage.put(u,0);//from index 0 in messages
        }

    }

    public static MessageManagement getMessageManagement()  {
        if (messageManagement == null){
            messageManagement = new MessageManagement();
        }
        return messageManagement;
    }

    public synchronized void addMessage(String message) throws InterruptedException {
        new Thread().sleep(1000);
        messages.add(message);
        System.out.println("Message add:" + message);
    }

    public void addConnectedClients(User user){
        connectedClients.add(user);
    }

    public void removeConnectedClients(User user){connectedClients.remove(user);}

    public void setUnreadMessage(User user, Integer i){
        unreadMessage.put(user,i);
    }

    public void updateUnreadMessage(User user){
        unreadMessage.put(user,unreadMessage.get(user)+1);
    }

    public List<User> getConnectedClients(){
        return connectedClients;
    }

    public HashMap<User,Integer> getUnreadMessage(){
        return unreadMessage;
    }

    public List<String> getMessages() {
        return messages;
    }
}
