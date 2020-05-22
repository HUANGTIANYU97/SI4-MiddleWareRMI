package interfaces;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Notify extends UnicastRemoteObject implements NotifyInterface{
    public Notify() throws RemoteException {
    }

    @Override
    public void getNotify(String message) throws RemoteException, InterruptedException {
        int i = StaticInfo.getLogin().length();
        if(message.substring(0,i).equals(StaticInfo.getLogin())){
            System.out.println("\033[31;4m" + message + "\033[0m");
        }
        else System.out.println(message);
    }
}
