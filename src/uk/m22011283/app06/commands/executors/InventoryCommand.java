package uk.m22011283.app06.commands.executors;

import uk.m22011283.app06.Player;
import uk.m22011283.app06.commands.CommandExecutor;

import java.util.List;

/**
 * Allows a player to get a list of their inventory contents.
 */
public class InventoryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(Player player,
                             List<String> commandArgs,
                             String commandName) {

        var inventory = player.getInventory();

        if (inventory.size() == 0) {
            player.sendMessage("You have no items.");
            return true;
        }

        StringBuilder out = new StringBuilder();
        out.append(inventory.size() > 1
            ? String.format("You have %d items:", inventory.size())
            : "You have 1 item:");

        // Output inventory contents
        for (var item : inventory) {
            out.append(String.format(
                    "%n - %s: %s",
                    item.getName(),
                    item.getDescription()));
        }

        player.sendMessage(out.toString());
        return true;
    }
}
