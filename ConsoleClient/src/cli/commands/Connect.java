package cli.commands;

import api.PDPublicAPI;
import cli.commands.messagerie.*;
import cli.framework.Command;
import cli.framework.Shell;
import interfaces.*;
import logging.Logger;

import java.rmi.Naming;
import java.rmi.Remote;
import java.util.List;

public class Connect extends Command<PDPublicAPI> {

    private String login;

    private String password;

    @Override
    public String identifier() {
        return "Connect";
    }

    @Override
    public void load(List<String> args) {
        this.login = args.get(0);
        this.password = args.get(1);
        StaticInfo.setLogin(login);
        //StaticInfo.setPassword(password);
    }

    @Override
    public void execute() throws Exception {
        try
        {
            String address = StaticInfo.getConnection();
            Remote r = Naming.lookup("rmi://" + address + "/Connection");
            //Remote rPrivage = Naming.lookup("rmi://" + address + "/Connection");
            Notify notify = new Notify();
            ChatInterface chatInterface;
            ConnectionInterface connectionInterface = ((ConnectionInterface) r);
            UserPrivateMessageInterface privateInterface = connectionInterface.getPrivate(login);
            StaticInfo.setConnectionInterface(connectionInterface);
            StaticInfo.setPrivateInterface(privateInterface);
            chatInterface = connectionInterface.connect(login, password,notify);

            if (chatInterface == null){
                Logger.getLogger().println("Wrong login/password");
            }
            else {
                Logger.getLogger().println("");
                Logger.getLogger().println("Welcome "+login + " !");
                Logger.getLogger().println("Type back to disconnect and ? for help.");
                Logger.getLogger().println("");


                StaticInfo.setChatInterface(chatInterface);
                String welcome = StaticInfo.getChatInterface().notifyConnectedUserWelcome();
                String welcomePrivate = StaticInfo.getPrivateInterface().notifyConnectedUserPrivateMessage();
                if(welcome != null){
                    System.out.println(welcome);
                }
                if(welcomePrivate != null){
                    System.out.println(welcomePrivate);
                }

                Shell<PDPublicAPI> shell = new Shell<>();
                shell.system = new PDPublicAPI();
                shell.invite = "Discord";
                shell.register(
                        //Quit.class,
                        //GetListGroup.class,
                        //GetMyListGroup.class,
                        VisualiseGroup.class,
                        AllRegisters.class,
                        ShowPrivateMessages.class,
                        PrivateMessage.class
                        //JoinGroup.class
                );
                shell.run();
            }

        }
        catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    @Override
    public String describe() {
        return "Connect to the discord. Arguments : username, password.";
    }

}

