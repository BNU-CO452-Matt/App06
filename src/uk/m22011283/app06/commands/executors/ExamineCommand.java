package uk.m22011283.app06.commands.executors;

import uk.m22011283.app06.Map;
import uk.m22011283.app06.Player;
import uk.m22011283.app06.commands.CommandExecutor;
import uk.m22011283.app06.direction.Cardinal;
import uk.m22011283.app06.direction.Direction;
import uk.m22011283.app06.rooms.Room;

import java.util.List;

public class ExamineCommand implements CommandExecutor {
    private final Map map;

    public ExamineCommand(Map map) {
        this.map = map;
    }

    @Override
    public boolean onCommand(Player player,
                             List<String> commandArgs,
                             String commandName) {

        var playerRoom = player.getRoom();
        var playerRoomInventory = playerRoom.getInventory();
        var playerRoomExits = this.map.getExits(playerRoom);

        if (!commandArgs.isEmpty()) {
            var arg = commandArgs.get(0);

            // Get exit from user input
            switch (arg) {
                case "n", "north" -> {
                    this.sendOutput(player,
                            playerRoomExits.getDirection(Cardinal.NORTH));
                    return true;
                }
                case "s", "south" -> {
                    this.sendOutput(player,
                            playerRoomExits.getDirection(Cardinal.SOUTH));
                    return true;
                }
                case "e", "east" -> {
                    this.sendOutput(player,
                            playerRoomExits.getDirection(Cardinal.EAST));
                    return true;
                }
                case "w", "west" -> {
                    this.sendOutput(player,
                            playerRoomExits.getDirection(Cardinal.WEST));
                    return true;
                }
            }

            // Search player and room inventory for item name
            for (var item : playerRoomInventory) {
                if (item.getName().equalsIgnoreCase(arg)) {
                    player.sendMessage(item.getDescription());
                    break;
                }
            }
            for (var item : player.getInventory()) {
                if (item.getName().equalsIgnoreCase(arg)) {
                    player.sendMessage(item.getDescription());
                    break;
                }
            }

            // If nothing else found, search triggers
            var triggerOptional = player.searchTrigger(arg);
            triggerOptional.ifPresent(trigger ->
                    player.sendMessage(trigger.getDescription()));
        } else {
            player.sendMessage(String.format("You are in %s. %s",
                    playerRoom.getName(),
                    playerRoom.getDescription()));

            // Output room inventory
            if (!playerRoomInventory.isEmpty()) {
                player.sendMessage(playerRoomInventory.size() == 1
                    ? String.format("There are %d items here:",
                            playerRoomInventory.size())
                    : "There is 1 item here:");

                for (var item : playerRoomInventory) {
                    player.sendMessage(String.format(
                            " - %s: %s",
                            item.getName(),
                            item.getDescription()));
                }
            }

            // Display all exit details
            for (var direction : playerRoomExits) {
                if (direction.getValue() != null) {
                    this.sendOutput(player, direction);
                }
            }
        }

        return true;
    }

    /**
     * Outputs info about a given neighbouring room
     * @param player Player to send output to
     * @param exitDirection Direction to output info about
     */
    private void sendOutput(Player player,
                            Direction<Room> exitDirection) {

        var exit = exitDirection.getValue();
        var direction = exitDirection.getDirection();

        player.sendMessage(
                exit != null
                    ?  String.format("To the %s you see %s: %s",
                            direction.getValue(),
                            exit.getName(),
                            exit.getDescription())
                    : "There isn't anything that way.");
    }
}
