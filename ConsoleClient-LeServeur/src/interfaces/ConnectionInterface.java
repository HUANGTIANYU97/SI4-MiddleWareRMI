package interfaces;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConnectionInterface extends Remote {
    public void echo() throws RemoteException, InterruptedException;
    
    public ChatInterface connect(String user, String password,NotifyInterface notify) throws RemoteException, InterruptedException;

    public void disconnect(String user) throws RemoteException, InterruptedException;
}