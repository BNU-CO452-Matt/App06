package uk.m22011283.app06.commands.executors;

import uk.m22011283.app06.Player;
import uk.m22011283.app06.commands.CommandExecutor;

import java.util.List;

/**
 * Allows a player to get items from the current room's inventory.
 */
public class TakeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(Player player,
                             List<String> commandArgs,
                             String commandName) {

        var arg = commandArgs.get(0);
        if (arg == null || arg.equals("")) {
            player.sendMessage("Nothing to take!");
            return true;
        }

        // Find item in room and move to player inventory
        var playerRoom = player.getRoom();
        for (var item : playerRoom.getInventory()) {
            if (item.getName().toLowerCase().contains(arg.toLowerCase())) {
                playerRoom.moveItem(item, player);
                player.sendMessage(String.format("Took %s!", item.getName()));

                return true;
            }
        }

        player.sendMessage("Couldn't find anything!");
        return true;
    }
}
