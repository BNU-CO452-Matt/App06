package uk.m22011283.app06.commands.executors;

import uk.m22011283.app06.Player;
import uk.m22011283.app06.commands.CommandExecutor;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Allows a player to activate a trigger either on its own or matched
 * with an item.
 *
 * use (trigger)
 * use item on (trigger)
 */
public class UseCommand implements CommandExecutor {
    private static final Pattern pattern = Pattern.compile(
            "(?<target1>[\\w ]+) on (?<target2>[\\w ]+)");

    @Override
    public boolean onCommand(Player player,
                             List<String> commandArgs,
                             String commandName) {

        // Re-join split-args as typed
        String typedString = String.join(" ", commandArgs);

        // Extract target strings from the typed string
        var matcher = UseCommand.pattern.matcher(typedString);
        if (matcher.find()) {
            var target1 = matcher.group("target1");
            var target2 = matcher.group("target2");

            /**
             * If the first target isn't present, the command is invalid in
             * either case, so quit.
             */
            if (target1 == null) {
                player.sendMessage("No target specified!");
                return true;
            }

            /**
             * Find an item to use as the first target.
             */
            var itemTargetOptional = player.searchItem(target1.toLowerCase());
            if (itemTargetOptional.isPresent()) {
                var itemTarget = itemTargetOptional.get();

                if (target2 == null) {
                    player.sendMessage("No second target specified!");
                    return true;
                }

                /**
                 * Find a trigger to use as the second target.
                 */
                var triggerTarget2Optional = player.searchTrigger(
                        target2.toLowerCase());
                if (triggerTarget2Optional.isPresent()) {
                    var triggerTarget2 = triggerTarget2Optional.get();
                    player.sendMessage(String.format(
                            "You use the %s on the %s.",
                            itemTarget.getName(), triggerTarget2.getName()));
                    triggerTarget2Optional.get().onUse(player, itemTarget);
                } else {
                    player.sendMessage("Could not find second target!");
                }

                return true;
            }
        } else {
            /**
             * If command does not match the pattern, just search for a trigger
             * and activate it.
             */
            var triggerTarget1Optional = player.searchTrigger(
                    typedString.toLowerCase());
            if (triggerTarget1Optional.isPresent()) {
                var triggerTarget1 = triggerTarget1Optional.get();
                player.sendMessage(String.format(
                        "You use the %s.", triggerTarget1.getName()));
                triggerTarget1Optional.get().onUse(player);

                return true;
            }
        }

        player.sendMessage("Could not find target(s)!");
        return true;
    }
}
