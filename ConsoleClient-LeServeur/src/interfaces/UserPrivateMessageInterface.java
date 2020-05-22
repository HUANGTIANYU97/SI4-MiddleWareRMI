package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserPrivateMessageInterface extends Remote{

    public boolean ifSendMessage(String pseudo) throws RemoteException, InterruptedException;

    public void sendMessage(String message, String pseudo) throws RemoteException, InterruptedException;

    public void notifyMessage(String message, String pseudo) throws RemoteException, InterruptedException;

    public String getPseudoFrom() throws RemoteException, InterruptedException;

    public String notifyConnectedUserPrivateMessage() throws RemoteException, InterruptedException;

    public void notifyAllUnreadMessage() throws RemoteException, InterruptedException;

    public void removeMessage(String pseudo) throws RemoteException, InterruptedException;

}
