package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserPrivateMessageInterface extends Remote{

    public void sendMessage(String pseudo) throws RemoteException, InterruptedException;

    public void notifyMessage(String message, String pseudo) throws RemoteException, InterruptedException;

    public String getPseudoTo(String login) throws RemoteException, InterruptedException;

}
