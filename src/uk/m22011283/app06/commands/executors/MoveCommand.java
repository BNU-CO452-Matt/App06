package uk.m22011283.app06.commands.executors;

import uk.m22011283.app06.Map;
import uk.m22011283.app06.Player;
import uk.m22011283.app06.commands.CommandExecutor;
import uk.m22011283.app06.commands.CommandManager;
import uk.m22011283.app06.rooms.Room;

import java.util.List;

public class MoveCommand implements CommandExecutor {
    private final Map map;

    /**
     * Creates a move command executor.
     * @param map Game map
     */
    public MoveCommand(Map map) {
        this.map = map;
    }

    @Override
    public boolean onCommand(Player player,
                             List<String> commandArgs,
                             String commandName) {

        var playerLocation = player.getRoom();
        var exits = this.map.getExits(playerLocation);

        String arg = null;
        if (!commandArgs.isEmpty()) {
            arg = commandArgs.get(0);
        }

        switch (commandName) {
            // Handle cardinal aliases
            case "n", "s", "e", "w":
                arg = commandName;
            default:
                // Map direction to exit
                Room exit = switch (arg) {
                    case "n", "north" -> exits.getNorth();
                    case "s", "south" -> exits.getSouth();
                    case "e", "east" -> exits.getEast();
                    case "w", "west" -> exits.getWest();
                    default -> null;
                };

                // Move player if possible
                if (exit != null) {
                    player.sendMessage("Moved.");
                    player.setRoom(exit);

                    // Not great, but easier
                    var examineCommand = CommandManager.getCommand("examine");
                    examineCommand.getExecutor().onCommand(
                            player, List.of(), examineCommand.getName());
                } else {
                    player.sendMessage("Cannot go that way.");
                }
        }

        return true;
    }
}
