package interfaces;

import java.util.*;

public class Chat {
    private static Chat chat;

    private  List<String> allGroups;
    private  Map<User, List<String>> groupSubscribedPerson;
    //private List<User> clients;

    Chat()  {
         allGroups = new ArrayList<>();
         groupSubscribedPerson = new HashMap<>();
         //clients = new ArrayList<>();

        allGroups.add("channel 1"); // here, 1 is the name of a channel (not very good idea, should better have
        //allGroups.add("2"); // been channel1, for instance!  But I've kept this so this is in line
        //allGroups.add("3"); // with the provided documentation (see the PDF file)

        User user = new User("Tianyu" , "1234" , "Tianyu111");
        User user2 = new User("Kejia" , "4567" , "Kejia111");
        User user3 = new User("Shuangshuang","8900","Shuangshuang111");
        //clients.add(user);
        //clients.add(user2);
        //clients.add(user3);

        groupSubscribedPerson.put(user, new ArrayList<>());
        groupSubscribedPerson.put(user2, new ArrayList<>());
        groupSubscribedPerson.put(user3, new ArrayList<>());

        groupSubscribedPerson.get(user).add("channel 1");
        groupSubscribedPerson.get(user2).add("channel 1");
        groupSubscribedPerson.get(user3).add("channel 1");
    }

    public static Chat getChat()  {
        if (chat == null){
            chat = new Chat();
        }
        return chat;
    }

    public List<String> getAllGroups() {
        return allGroups;
    }

    public Map<User, List<String>> getGroupSubscribedPerson() {
        return groupSubscribedPerson;
    }

    public List<User> getAllUser(){
        return new ArrayList<>(groupSubscribedPerson.keySet());
    }

}
