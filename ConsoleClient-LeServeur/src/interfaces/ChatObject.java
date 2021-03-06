package interfaces;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatObject extends UnicastRemoteObject implements ChatInterface {

    //List<String> messages;
    //List<String> connectedClients;
    //HashMap<User,Integer> unreadMessage;

    User user;
    Chat chat;
    MessageManagement mm;

    protected ChatObject(int port) throws RemoteException {
        super(port);
    }

    protected ChatObject(User user) throws RemoteException {
        //this.messages = new ArrayList<>();
        //this.connectedClients = new ArrayList<>();
        //this.unreadMessage = new HashMap<>();
        this.user = user;
        this.chat = Chat.getChat();
        this.mm = MessageManagement.getMessageManagement();
    }

    public void echo() throws RemoteException, InterruptedException {
        Thread.sleep(10000);
    }

    public List<String> getListGroup() throws RemoteException, InterruptedException{
        return chat.getAllGroups();
    }

    public List<String> getMyListGroup() throws RemoteException, InterruptedException{
        return chat.getGroupSubscribedPerson().get(user);
    }

    public boolean joinGroup(String group) throws RemoteException, InterruptedException {

        System.out.println(user.getLogin() + " is trying to join topic #" + group);

        if(chat.getAllGroups().contains(group)) {
            if (!chat.getGroupSubscribedPerson().get(user).contains(group)) {
                chat.getGroupSubscribedPerson().get(user).add(group);
            }


            System.out.println(user.getLogin() + " has joined the topic #" + group);

            return true;
        }
        return false;
    }

    @Override
    public List<String> getGroupConnection(String group) throws RemoteException, InterruptedException {

        System.out.println(user.getLogin() + " is trying to enter topic #" + group);
        mm.addConnectedClients(user);
        joinGroup(group);

        List<String> list = new ArrayList<>();
        list.add("user");
        list.add("password");
        list.add(user.getPseudo());

        System.out.println(user.getLogin() + " has entered topic #" + group);

        return list;
    }

    @Override
    public void getGroupDisconnection(String group) throws RemoteException, InterruptedException {
        mm.removeConnectedClients(user);
        System.out.println(user.getLogin() + " has left topic #" + group);
    }

    @Override
    public synchronized void receiveMessage(String message) throws RemoteException, InterruptedException{
        mm.addMessage(message);
        System.out.println(message);
        notifyMessage(message);
    }

    @Override
    public synchronized void notifyMessage(String message) throws RemoteException, InterruptedException{
        ExecutorService pool = Executors.newCachedThreadPool();
        for (User u : mm.getConnectedClients()) {
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        //new Thread().sleep(1000);
                        u.getNotify().getNotify(message);
                        mm.updateUnreadMessage(u);
                        System.out.println(Thread.currentThread().getName()+" to "+ u.getPseudo());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            pool.execute(run);
        }
        pool.shutdown();
    }

    @Override
    public String notifyConnectedUserWelcome() throws RemoteException, InterruptedException{
        //int i = mm.getUnreadMessage().get(user);
        if(mm.getUnreadMessage().get(user) < (mm.getMessages().size() - 1)){
            return "There are new messages in the channel";
        }
        return null;
    }

    @Override
    public void notifyUnreadMessage(String message) throws RemoteException, InterruptedException{
            user.getNotify().getNotify(message);
            mm.updateUnreadMessage(user);
    }

    @Override
    public void notifyUnreadMessageAll() throws RemoteException, InterruptedException {
        //System.out.println("begin");
        int index = mm.getUnreadMessage().get(user);
        //System.out.println("index");
        if(index < (mm.getMessages().size() - 1)){
            for(int i = index;i < mm.getMessages().size();i++){
                user.getNotify().getNotify(mm.getMessages().get(i));
                mm.updateUnreadMessage(user);
            }
        }
    }
}
