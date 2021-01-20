package uk.m22011283.app06.rooms;

import uk.m22011283.app06.Container;
import uk.m22011283.app06.Examinable;
import uk.m22011283.app06.Item;
import uk.m22011283.app06.Player;
import uk.m22011283.app06.triggers.Trigger;

import java.util.HashSet;
import java.util.Set;

/**
 * Base room class.
 */
public abstract class Room implements Examinable, Container {
    private final String name;
    private final String description;

    private final Set<Item> inventory = new HashSet<>();
    private final Set<Trigger> triggers = new HashSet<>();

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    /**
     * Add trigger to room (exposed to player).
     * @param trigger Trigger object
     */
    public void addTrigger(Trigger trigger) {
        this.triggers.add(trigger);
    }
    /**
     * Remove trigger from room.
     * @param trigger Trigger object
     */
    public void removeTrigger(Trigger trigger) {
        this.triggers.remove(trigger);
    }

    /**
     * @return Get list of attached triggers.
     */
    public Set<Trigger> getTriggers() {
        return triggers;
    }


    /**
     * To be called on player enter.
     * @param player Player object
     */
    public void onEnter(Player player) {}

    /**
     * To be called on player exit.
     * @param player Player object
     */
    public void onExit(Player player) {}


    @Override
    public Set<Item> getInventory() {
        return this.inventory;
    }
}
