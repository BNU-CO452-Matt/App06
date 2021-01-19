package uk.m22011283.app06.rooms;

import uk.m22011283.app06.Player;

/**
 * Represents the starting location
 */
public class SpawnRoom extends Room {
    public static final String SPAWN_ROOM_NAME = "Spawn";

    public SpawnRoom() {
        super(SpawnRoom.SPAWN_ROOM_NAME, "The starting room.");
    }

    @Override
    public void onEnter(Player player) {
        player.sendMessage("Welcome!");
    }

    @Override
    public void onExit(Player player) {

    }
}
