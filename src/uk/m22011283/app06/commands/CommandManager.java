package uk.m22011283.app06.commands;

import uk.m22011283.app06.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;


/**
 * Utility class to handle command management.
 */
public class CommandManager {
    /**
     * Command objects mapped to command names. LinkedHashMap used for
     * insertion-order sort.
     */
    private static final LinkedHashMap<
                String, Command> commands = new LinkedHashMap<>();

    public static LinkedHashMap<String, Command> getCommands() {
        return commands;
    }

    /**
     * Gets (or creates) a command with a given name.
     * 
     * @param name Name of command
     * @return Command object
     */
    public static Command getCommand(String name) {
        // Fetch existing if present
        if (commands.containsKey(name)) {
            return commands.get(name);
        }

        Command command = new Command();
        command.setName(name);

        commands.put(name, command);
        return command;
    }

    /**
     * @see CommandManager#getCommand(String)
     * @param description Description of command (used in help info
     */
    public static Command getCommand(String name, String description) {
        Command command = CommandManager.getCommand(name);
        command.setDescription(description);
        return command;
    }

    /**
     * @see CommandManager#getCommand(String, String) 
     * @param aliases Aliases to use in place of command name in CLI
     */
    public static Command getCommand(String name,
                                     String description,
                                     String... aliases) {

        Command command = CommandManager.getCommand(name, description);
        command.setAliases(aliases);
        return command;
    }

    /**
     * Gets user input and parses to call a given command with an optional
     * argument.
     *
     * @param player Player that executed command
     */
    public static void parseInput(Player player) {
        var inputLine = new Scanner(System.in).nextLine();
        var s = new Scanner(inputLine);

        String commandName = null;
        List<String> commandArgs = new ArrayList<>();

        if (s.hasNext()) {
            commandName = s.next();
            while (s.hasNext()) {
                commandArgs.add(s.next());
            }
        }

        for (var command : CommandManager.commands.values()) {
            if (command.getName().equals(commandName)
                    || Arrays.asList(
                            command.getAliases()).contains(commandName)) {

                // Run command and print error if failed
                if (!command.getExecutor().onCommand(
                        player, commandArgs, commandName)) {
                    player.sendMessage("Error: Command failed!");
                    return;
                }

                return;
            }
        }

        System.out.println("Error: Command not recognised!");
    }
}
