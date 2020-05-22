package interfaces;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ConnectionObject extends UnicastRemoteObject
        implements ConnectionInterface {

    List<User> usersList;
    static List<User> connectedUsers;

    public ConnectionObject() throws RemoteException {
        usersList = Chat.getChat().getAllUser();
        connectedUsers = new ArrayList<>();
    }

    public ConnectionObject(int port) throws RemoteException {
        super(port);
    }

    public void echo() throws RemoteException, InterruptedException {
        Thread.sleep(10000);
    }

    public ChatInterface connect(String userLogin, String passwordLogin, NotifyInterface notifyInterface) throws RemoteException, InterruptedException{
        for (User user : usersList){
            if (user.getLogin().equals(userLogin) && user.getPassword().equals(passwordLogin)){
                ChatObject messagerieObject = new ChatObject(user);
                user.setNotify(notifyInterface);
                connectedUsers.add(user);
                System.out.println(user.getLogin() + " is now connected");
                return messagerieObject;
            }
        }
        return null;

    }

    @Override
    public UserPrivateMessageInterface getPrivate(String login) throws RemoteException, InterruptedException {
        UserPrivateMessageInterface up;
        for(User u : Chat.getChat().getAllUser()){
            if(u.getLogin().equals(login)){
                up = new UserPrivateMessage(u.getPseudo());
                System.out.println("private channel created " + u.getPseudo());
                return up;
            }
        }
        return null;
    }

    public void disconnect(String userLogin) throws RemoteException, InterruptedException{
        for(User user : usersList){
            if(user.getLogin().equals(userLogin)){
                connectedUsers.remove(user);
            }
        }
        System.out.println(userLogin + " is now disconnected");
    }

    public static List<User> getConnectedUsers(){return connectedUsers;}

    public String getAllPseudos() throws RemoteException, InterruptedException{
        String p = "";
        for(User u : usersList){
            p = p + u.getPseudo() + "\n";
        }
        return p;
    }
} 