package uk.m22011283.app06.commands;

import uk.m22011283.app06.Player;

import java.util.List;

public interface CommandExecutor {
    /**
     * Provides a command implementation.
     *
     * @param player Player that executed command
     * @param commandArgs Argument passed with command (or null if no argument)
     * @param commandName Name of command executed
     * @return Success state
     */
    public boolean onCommand(Player player,
                             List<String> commandArgs,
                             String commandName);
}
