package uk.m22011283.app06;

import uk.m22011283.app06.rooms.Room;
import uk.m22011283.app06.triggers.Trigger;

import java.util.*;

/**
 * Represents a player
 */
public class Player extends Entity implements Container {
    private final Set<Item> inventory = new HashSet<>();
    private Room currentRoom;

    /**
     * @param name Player name (unused)
     * @param room Player starting room
     */
    public Player(String name, Room room) {
        super(name, "The player.");
        this.currentRoom = room;
    }

    /**
     * Outputs to the CLI
     * @param message Message to send
     */
    public void sendMessage(String message) {
        System.out.println(message);
    }

    /**
     * @return Player's current room
     */
    public Room getRoom() {
        return this.currentRoom;
    }

    /**
     * Moves player to a given room
     * @param room New room
     */
    public void setRoom(Room room) {
        Room currentRoom = this.getRoom();
        if (currentRoom != null) {
            currentRoom.onExit(this);
        }

        this.currentRoom = room;
        room.onEnter(this);
    }

    /**
     * Search current room for a given trigger name
     * @param searchTerm Trigger name (/partial)
     * @return Optional Trigger
     */
    public Optional<Trigger> searchTrigger(String searchTerm) {
        for (var trigger : this.getRoom().getTriggers()) {
            if (trigger.getName().toLowerCase().contains(searchTerm)) {
                return Optional.of(trigger);
            }
        }

        return Optional.empty();
    }

    @Override
    public Set<Item> getInventory() {
        return this.inventory;
    }

    @Override
    public void addItem(Item item) {
        this.inventory.add(item);
    }
    @Override
    public void removeItem(Item item) {
        this.inventory.remove(item);
    }
    @Override
    public void moveItem(Item item, Container container) {
        if (this.inventory.contains(item)) {
            this.removeItem(item);
            container.addItem(item);
        }
    }

    public void dropItem(Item item) {
        if (this.inventory.contains(item)) {
            this.moveItem(item, this.currentRoom);
        }
    }
}
