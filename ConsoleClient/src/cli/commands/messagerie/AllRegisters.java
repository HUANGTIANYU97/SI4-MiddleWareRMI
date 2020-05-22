package cli.commands.messagerie;

import api.PDPublicAPI;
import cli.framework.Command;
import interfaces.ChatInterface;
import interfaces.StaticInfo;
import logging.Logger;

import java.util.List;


public class AllRegisters extends Command<PDPublicAPI>{
    @Override
    public String identifier() {
        return "See all the registerd users";
    }

    @Override
    public void load(List<String> args) {
    }

    @Override
    public void execute() throws Exception {
        System.out.println(StaticInfo.getConnectionInterface().getAllPseudos());
    }

    @Override
    public String describe() {
        return "See all the registered users' pseudos. No arguments.";
    }

}
