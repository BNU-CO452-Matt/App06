package uk.m22011283.app06.commands.executors;

import uk.m22011283.app06.Player;
import uk.m22011283.app06.commands.CommandExecutor;

import java.util.List;
import java.util.regex.Pattern;

public class UseCommand implements CommandExecutor {
    private static final Pattern pattern = Pattern.compile(
            "(?<target1>[\\w ]+) on (?<target2>[\\w ]+)");

    @Override
    public boolean onCommand(Player player,
                             List<String> commandArgs,
                             String commandName) {

        String joinedArgs = String.join(" ", commandArgs);

        var matcher = UseCommand.pattern.matcher(joinedArgs);
        if (matcher.find()) {
            var target1 = matcher.group("target1");
            var target2 = matcher.group("target2");

            if (target1 == null) {
                player.sendMessage("No target specified!");
                return true;
            }

            var target1Lower = target1.toLowerCase();

            var itemTargetOptional = player.searchItem(target1Lower);
            if (itemTargetOptional.isPresent()) {
                var itemTarget = itemTargetOptional.get();

                if (target2 == null) {
                    player.sendMessage("No second target specified!");
                    return true;
                }

                var target2Lower = target2.toLowerCase();
                var triggerTarget2Optional = player.searchTrigger(
                        target2Lower);

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
            var triggerTarget1Optional = player.searchTrigger(
                    joinedArgs.toLowerCase());

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
