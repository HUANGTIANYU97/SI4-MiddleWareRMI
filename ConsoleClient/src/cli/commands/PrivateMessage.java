package cli.commands;

import api.PDPublicAPI;
import cli.commands.messagerie.*;
import cli.framework.Command;
import cli.framework.Shell;
import interfaces.ChatInterface;
import interfaces.ConnectionInterface;
import interfaces.Notify;
import interfaces.StaticInfo;
import logging.Logger;

import java.rmi.Naming;
import java.rmi.Remote;
import java.util.List;


public class PrivateMessage extends Command<PDPublicAPI> {

    private String pseudoTo;

    private String pseudoFrom;

    private boolean exit = true;

    @Override
    public String identifier() {
        return "Send private message";
    }

    @Override
    public void load(List<String> args) {
        this.pseudoTo = args.get(0);
    }

    @Override
    public void execute() throws Exception {
        if(StaticInfo.getPrivateInterface().ifSendMessage(pseudoTo)) {
            System.out.println("");
            System.out.println("Talking to " + pseudoTo + " in private ");
            System.out.println("Type exit to leave.");
            System.out.println("");
            try {
                java.io.BufferedReader stdin =
                        new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
                while (exit) {
                    pseudoFrom = StaticInfo.getPrivateInterface().getPseudoFrom();
                    String s = stdin.readLine();


                    if (s == null) {
                        exit = false;
                        //exit();
                    } else if (s.equals("exit")) {
                        exit = false;
                        //exit();
                    } else if (s.length() > 0) {
                        String msg = "";

                        msg = "[Private message] " + pseudoFrom + ": " + s;
                        //StaticInfo.getChatInterface().receiveMessage(msg);
                        //StaticInfo.getChatInterface().notifyMessage(msg);

                        //Publish the message :
                        //System.out.println(msg);
                        StaticInfo.getPrivateInterface().sendMessage(msg,pseudoTo);
                        StaticInfo.getPrivateInterface().notifyMessage(msg,pseudoTo);
                        //StaticInfo.getPrivateInterface().removeMessage(pseudoTo);
                        System.out.println("Ok, message sent to user");
                    }
                }
            } catch (java.io.IOException ioe) {
                ioe.printStackTrace();
            }
        }
        else {
            System.out.println("Unknown destination");
        }
    }

    @Override
    public String describe() {
        return "Send private message to another user. Arguments : pseudo";
    }

}
