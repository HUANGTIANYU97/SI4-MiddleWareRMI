package interfaces;

public class User {
    private String login;

    private String password;

    private String pseudo;

    private NotifyInterface notify;

    public User(String login,String password, String pseudo){
        this.login = login;
        this.password = password;
        this.pseudo = pseudo;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public NotifyInterface getNotify(){return notify;}

    public void setNotify(NotifyInterface n){notify = n;}
}
