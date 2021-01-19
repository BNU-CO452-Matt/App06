package uk.m22011283.app06.commands.executors;

import uk.m22011283.app06.Player;
import uk.m22011283.app06.commands.CommandExecutor;
import uk.m22011283.app06.commands.CommandManager;

import java.util.List;

public class HelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(Player player,
                             List<String> commandArgs,
                             String commandName) {

        StringBuilder out = new StringBuilder();
        out.append(String.format("Commands%n--------%n"));

        // Create help info for each command
        for (var command : CommandManager.getCommands().values()) {
            out.append(String.format(" - %s — %s%n",
                    command.getName(),
                    command.getDescription()));

            // Print aliases if set
            var aliases = command.getAliases();
            if (aliases.length > 0) {
                out.append(String.format("    (aliases: %s)%n",
                        String.join(",", aliases)));
            }
        }

        player.sendMessage(out.toString());
        return true;
    }
}
