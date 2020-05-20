package cli.commands;

import api.PDPublicAPI;
import cli.framework.Command;

import static java.lang.System.exit;

public class Quit extends Command<PDPublicAPI> {

    public String identifier() {
        return "Quit";
    }

    public void execute() throws Exception {
    }

    public String describe() {
        return "quits the client. No argument.";
    }

    @Override
    public boolean shouldContinue() {
        return false;
    }
}
