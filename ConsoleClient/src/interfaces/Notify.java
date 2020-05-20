package interfaces;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Notify extends UnicastRemoteObject implements NotifyInterface{
    public Notify() throws RemoteException {
    }

    @Override
    public void getNotify(String message) throws RemoteException, InterruptedException {
        System.out.println(message);
    }
}
