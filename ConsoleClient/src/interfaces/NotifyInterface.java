package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NotifyInterface extends Remote{
    public void getNotify(String message) throws RemoteException, InterruptedException;
}
