package uk.m22011283.app06.commands;

/**
 * Represents a user command
 */
public class Command {
    private String name;                // CLI Command name
    private String description;         // Command description used in help
    private String[] aliases = {};      // Additional CLI aliases
    private CommandExecutor executor;   // Provides command implementation


    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public CommandExecutor getExecutor() {
        return executor;
    }
    public void setExecutor(CommandExecutor executor) {
        this.executor = executor;
    }

    public String[] getAliases() {
        return aliases;
    }
    public void setAliases(String[] aliases) {
        this.aliases = aliases;
    }
}
