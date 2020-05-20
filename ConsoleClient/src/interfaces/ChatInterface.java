package interfaces;

//import javax.jms.JMSException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChatInterface extends Remote {
    public void echo() throws RemoteException, InterruptedException;

    public List<String> getListGroup() throws RemoteException, InterruptedException;

    public List<String> getMyListGroup() throws RemoteException, InterruptedException;

    public boolean joinGroup(String group) throws RemoteException, InterruptedException;

    public List<String> getGroupConnection(String group) throws RemoteException, InterruptedException;//, JMSException;

    public void getGroupDisconnection(String group) throws RemoteException, InterruptedException;

    public void receiveMessage(String message) throws RemoteException, InterruptedException;

    public void notifyMessage(String message) throws RemoteException, InterruptedException;

    public String notifyConnectedUserWelcome() throws RemoteException, InterruptedException;

    public void notifyUnreadMessage(String message) throws RemoteException, InterruptedException;

    public void notifyUnreadMessageAll() throws RemoteException, InterruptedException;
}
