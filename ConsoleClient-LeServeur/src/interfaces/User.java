package interfaces;

import java.util.LinkedList;
import java.util.Queue;

public class User {
    private String login;

    private String password;

    private String pseudo;

    private NotifyInterface notify;

    private Queue<String> messages;

    public User(String login,String password, String pseudo){
        this.login = login;
        this.password = password;
        this.pseudo = pseudo;
        messages = new LinkedList<>();
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public NotifyInterface getNotify(){return notify;}

    public void setNotify(NotifyInterface n){notify = n;}

    public Queue<String> getMessages(){return messages;}

    public void offerMessage(String message){messages.offer(message);}

    public String pollMessage(){
        return messages.poll();
        //if empty ,return null
    }

    public void removeMessage(){messages.remove();}
}
