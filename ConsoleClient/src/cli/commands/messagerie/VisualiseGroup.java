package cli.commands.messagerie;

import api.PDPublicAPI;
import cli.framework.Command;
import interfaces.StaticInfo;
import cli.framework.Shell;
import logging.Logger;


import java.util.List;

public class VisualiseGroup extends Command<PDPublicAPI> {
    private String idTopic = null;
    private String pseudo = null;
    private String login = null;
    private String password = null;
    private Boolean exit = true;


    @Override
    public String identifier() {
        return "Enter a topic";
    }

    @Override
    public void load(List<String> args) {
        //this.idTopic = args.get(0);
        this.idTopic = "1";
    }

    @Override
    public void execute() throws Exception {
        List<String> list = StaticInfo.getChatInterface().getGroupConnection(idTopic);
        login = list.get(0);
        password = list.get(1);
        pseudo = list.get(2);
        System.out.println("");
        System.out.println("Connected to topic #"+idTopic+" as "+"\u001B[31m"+pseudo+"\u001B[0m");
        System.out.println("Type exit to leave.");
        System.out.println("");
        StaticInfo.getChatInterface().notifyUnreadMessageAll();
        try
        {
            java.io.BufferedReader stdin =
                    new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
            while (exit)
            {

                String s = stdin.readLine();


                if(s == null){
                    StaticInfo.getChatInterface().getGroupDisconnection(idTopic);
                    exit = false;
                    //exit();
                }
                else if (s.equals("exit")){
                    StaticInfo.getChatInterface().getGroupDisconnection(idTopic);
                    exit = false;
                    //exit();
                }
                else if (s.length()>0)
                { String msg="";
                   
                  msg=pseudo + ": " + s;
                  StaticInfo.getChatInterface().receiveMessage(msg);
                  //StaticInfo.getChatInterface().notifyMessage(msg);

                  //Publish the message :
                  //System.out.println("Recu msg"+msg);
                }
            }
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    @Override
    public String describe() {
        return "Connect to a topic, see its messages and send messages to it. No arguments. Type exit to leave.";
    }


    private void exit()
    {
        System.out.println("");
        System.out.println("\u001B[31m"+pseudo+"\u001B[0m"+" left topic #"+idTopic);
        System.out.println("");




        Logger.getLogger().println("");
        Logger.getLogger().println("Type back to disconnect and ? for help.");
        Logger.getLogger().println("");





        Shell<PDPublicAPI> shell = new Shell<>();
        shell.system = new PDPublicAPI();
        shell.invite = "Discord>";
        shell.register(
                GetListGroup.class,
                GetMyListGroup.class,
                VisualiseGroup.class
                //JoinGroup.class
        );
        shell.run();

        //System.exit(0);
    }
}
