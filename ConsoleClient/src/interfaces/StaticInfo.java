package interfaces;

public class StaticInfo {
    private static String connection;
    private static ChatInterface chatInterface;
    private static String login;
    private static String password;
    private static ConnectionInterface connectionInterface;
    private static UserPrivateMessageInterface privateInterface;

    public static ChatInterface getChatInterface() {
        return chatInterface;
    }

    public static void setChatInterface(ChatInterface chatInterface) {
        StaticInfo.chatInterface = chatInterface;
    }

    public static String getConnection() {
        return connection;
    }

    public static void setConnection(String conection) {
        StaticInfo.connection = conection;
    }

    public static void setLogin(String l){login = l;}

    public static String getLogin(){return login;}

    public static void setPassword(String pw){password = pw;}

    public static String getPassword(){return password;}

    public static void setConnectionInterface(ConnectionInterface ci){connectionInterface = ci;}

    public static ConnectionInterface getConnectionInterface(){return connectionInterface;}

    public static UserPrivateMessageInterface getPrivateInterface(){return privateInterface;}

    public static void setPrivateInterface(UserPrivateMessageInterface p){privateInterface = p;}
}
