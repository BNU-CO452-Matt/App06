package uk.m22011283.app06.commands.executors;

import uk.m22011283.app06.Player;
import uk.m22011283.app06.commands.CommandExecutor;

import java.util.List;

public class ExitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(Player player,
                             List<String> commandArgs,
                             String commandName) {

        player.sendMessage("Exiting game...");
        System.exit(0);
        return true;
    }
}
