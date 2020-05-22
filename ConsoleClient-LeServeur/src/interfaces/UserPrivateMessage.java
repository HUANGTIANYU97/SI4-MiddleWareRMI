package interfaces;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserPrivateMessage extends UnicastRemoteObject
        implements UserPrivateMessageInterface{
    protected UserPrivateMessage() throws RemoteException {
    }

    public void sendMessage(String pseudo) throws RemoteException, InterruptedException{

    }

    public void notifyMessage(String message, String pseudo) throws RemoteException, InterruptedException{

    }

    public String getPseudoTo(String login) throws RemoteException, InterruptedException{
        for(User u : Chat.getChat().getAllUser()){
            if(u.getLogin().equals(login)){
                return u.getPseudo();
            }
        }
        return null;
    }
}
