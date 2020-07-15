/**
 * @Author HUANG Tianyu
 */
package interfaces;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserPrivateMessage extends UnicastRemoteObject
        implements UserPrivateMessageInterface{

    private String pseudoFrom;
    //private List<User> connectedUsers;

    protected UserPrivateMessage() throws RemoteException {
        //connectedUsers = new ArrayList<>();
    }

    public UserPrivateMessage(String pseudo) throws RemoteException {
        pseudoFrom = pseudo;
        //connectedUsers = new ArrayList<>();
    }

    public boolean ifSendMessage(String pseudo) throws RemoteException, InterruptedException{
        for(User u : Chat.getChat().getAllUser()){
            if(u.getPseudo().equals(pseudo)){
                /*if(ConnectionObject.getConnectedUsers().contains(u)){
                    notifyMessage();
                }*/
                return true;
            }
        }
        return false;
    }

    public synchronized void sendMessage(String message, String pseudo) throws RemoteException, InterruptedException{
        for(User u : Chat.getChat().getAllUser()){
            if(u.getPseudo().equals(pseudo)){
                u.offerMessage(message);
            }
        }
    }

    public synchronized void notifyMessage(String message, String pseudo) throws RemoteException, InterruptedException{
        for(User u : Chat.getChat().getAllUser()){
            if(u.getPseudo().equals(pseudo)){
                if(ConnectionObject.getConnectedUsers().contains(u)){
                    //add in queue
                    //notify message
                    while (u.getClear()){
                        Thread.sleep(100);
                    }
                    u.getNotify().getNotify(message);
                    u.pollMessage();
                    //u.removeMessage();
                }
            }
        }
    }

    public String notifyConnectedUserPrivateMessage() throws RemoteException, InterruptedException{
        for(User u : Chat.getChat().getAllUser()){
            if(u.getPseudo().equals(pseudoFrom)){
                if(u.getMessages().size() > 0)
                    return "There are some private messages";
            }
        }
        return null;
    }

    public String getPseudoFrom() throws RemoteException, InterruptedException{
        return pseudoFrom;
    }

    public void notifyAllUnreadMessage() throws RemoteException, InterruptedException{
        for(User u : Chat.getChat().getAllUser()){
            if(u.getPseudo().equals(pseudoFrom)){
                for(String s : u.getMessages()){
                    u.getNotify().getNotify(s);
                }
                u.clear();
                return;
            }
        }
    }

}
