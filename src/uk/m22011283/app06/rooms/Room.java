package uk.m22011283.app06.rooms;

import uk.m22011283.app06.Container;
import uk.m22011283.app06.Examinable;
import uk.m22011283.app06.Item;
import uk.m22011283.app06.Player;
import uk.m22011283.app06.triggers.Trigger;

import java.util.HashSet;
import java.util.Set;

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

    public void addTrigger(Trigger trigger) {
        this.triggers.add(trigger);
    }
    public void removeTrigger(Trigger trigger) {
        this.triggers.remove(trigger);
    }

    public Set<Trigger> getTriggers() {
        return triggers;
    }

    public void onEnter(Player player) {}
    public void onExit(Player player) {}

    public boolean canEnter(Player player) {
        return true;
    }

    @Override
    public Set<Item> getInventory() {
        return this.inventory;
    }
}
