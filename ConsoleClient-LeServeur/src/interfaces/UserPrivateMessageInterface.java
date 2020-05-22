package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserPrivateMessageInterface extends Remote{

    public void sendMessage(String pseudo) throws RemoteException, InterruptedException;

    public void

}
