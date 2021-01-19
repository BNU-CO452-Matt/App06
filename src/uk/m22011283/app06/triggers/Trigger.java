package uk.m22011283.app06.triggers;

import uk.m22011283.app06.Entity;
import uk.m22011283.app06.Item;
import uk.m22011283.app06.Player;

public class Trigger extends Entity {
    /**
     * @see Entity#Entity(String, String)
     */
    public Trigger(String name, String description) {
        super(name, description);
    }

    /**
     * @see Trigger#onUse(Player, Item)
     */
    public void onUse(Player player) {
        player.sendMessage("This does nothing.");
    }

    /**
     * Interaction handler.
     * @param player Player interacting
     * @param item Target item
     */
    public void onUse(Player player, Item item) {
        player.sendMessage(String.format(
                "You cannot use a %s this way.", item.getName()));
    }
}
