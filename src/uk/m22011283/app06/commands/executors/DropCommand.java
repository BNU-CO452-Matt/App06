package uk.m22011283.app06.commands.executors;

import uk.m22011283.app06.Player;
import uk.m22011283.app06.commands.CommandExecutor;

import java.util.List;

public class DropCommand implements CommandExecutor {
    @Override
    public boolean onCommand(Player player,
                             List<String> commandArgs,
                             String commandName) {

        var arg = commandArgs.get(0);
        if (arg == null || arg.equals("")) {
            player.sendMessage("Nothing to drop!");
            return true;
        }

        for (var item : player.getInventory()) {
            if (item.getName().toLowerCase().contains(arg)) {
                player.moveItem(item, player.getRoom());
                player.sendMessage(String.format("Dropped %s!", item.getName()));

                return true;
            }
        }

        player.sendMessage("Couldn't find anything!");

        return true;
    }
}
