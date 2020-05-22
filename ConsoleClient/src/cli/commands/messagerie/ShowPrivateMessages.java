package cli.commands.messagerie;

import api.PDPublicAPI;
import cli.framework.Command;
import interfaces.StaticInfo;

import java.util.List;

import api.PDPublicAPI;
import cli.framework.Command;
import interfaces.ChatInterface;
import interfaces.StaticInfo;
import interfaces.UserPrivateMessageInterface;
import logging.Logger;

import java.util.List;

public class ShowPrivateMessages extends Command<PDPublicAPI>{


    @Override
    public String identifier() {
        return "See all the unread private messages";
    }

    @Override
    public void load(List<String> args) {
    }

    @Override
    public void execute() throws Exception {
        StaticInfo.getPrivateInterface().notifyAllUnreadMessage();
    }

    @Override
    public String describe() {
        return "See all the unread private messages. No arguments.";
    }

}
