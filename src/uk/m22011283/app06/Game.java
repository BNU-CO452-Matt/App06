package uk.m22011283.app06;

import uk.m22011283.app06.commands.CommandManager;
import uk.m22011283.app06.commands.executors.DropCommand;
import uk.m22011283.app06.commands.executors.ExamineCommand;
import uk.m22011283.app06.commands.executors.ExitCommand;
import uk.m22011283.app06.commands.executors.HelpCommand;
import uk.m22011283.app06.commands.executors.InventoryCommand;
import uk.m22011283.app06.commands.executors.MoveCommand;
import uk.m22011283.app06.commands.executors.TakeCommand;
import uk.m22011283.app06.commands.executors.UseCommand;
import uk.m22011283.app06.rooms.Room;

import java.util.List;


/**
 * Main game class.
 */
public class Game {
    private final Map map = new Map();
    private final Player player;

    public Game() {
        // Create the player and place them in the starting room
        Room startingRoom = this.map.getStartingRoom().orElseThrow();
        this.player = new Player("P1", startingRoom);

        CommandManager.getCommand(
                "help", "Print help info.")
            .setExecutor(new HelpCommand());

        CommandManager.getCommand(
                "move", "Move in a given direction.",
                    "go", "travel",
                    "mv", "m",
                    "n", "s", "e", "w")
            .setExecutor(new MoveCommand(this.map));

        var examineCommand = CommandManager.getCommand(
                "examine", "Examine something or look around.",
                    "look", "x");
        examineCommand.setExecutor(new ExamineCommand(map));

        CommandManager.getCommand(
                "take", "Take an item.",
                    "get", "grab", "pick", "acquire", "t")
            .setExecutor(new TakeCommand());

        CommandManager.getCommand(
                "drop", "Drop an item",
                    "discard", "dispose", "toss", "dump", "d")
            .setExecutor(new DropCommand());

        CommandManager.getCommand(
                "use", "Use an item")
            .setExecutor(new UseCommand());

        CommandManager.getCommand(
                "inventory", "See your carried items.",
                    "inv", "items", "bag", "i")
            .setExecutor(new InventoryCommand());

        CommandManager.getCommand(
                "exit", "Exit the game.")
            .setExecutor(new ExitCommand());


        examineCommand.getExecutor().onCommand(
                player, List.of(), examineCommand.getName());
    }

    /**
     * Start main loop
     */
    public void init () {
        for (;;) {
            System.out.print("> ");
            CommandManager.parseInput(this.player);
        }
    }
}
