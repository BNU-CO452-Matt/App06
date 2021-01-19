package uk.m22011283.app06;

import uk.m22011283.app06.direction.Directional;
import uk.m22011283.app06.rooms.ExitRoom;
import uk.m22011283.app06.rooms.HallwayRoom;
import uk.m22011283.app06.rooms.KeyRoomBlue;
import uk.m22011283.app06.rooms.KeyRoomGreen;
import uk.m22011283.app06.rooms.KeyRoomRed;
import uk.m22011283.app06.rooms.Room;
import uk.m22011283.app06.rooms.SpawnRoom;

import java.util.Optional;

/**
 * Represents the navigable game map
 */
public class Map {
    private final Room[][] layout = {
        { null,              new KeyRoomRed(),  null,               new HallwayRoom() },
        { null,              new HallwayRoom(), null,               new HallwayRoom() },
        { new HallwayRoom(), new HallwayRoom(), new HallwayRoom(),  new KeyRoomGreen() },
        { new HallwayRoom(), new SpawnRoom(),   null,               null },
        { new KeyRoomBlue(), new HallwayRoom(), new HallwayRoom(),  new HallwayRoom() },
        { null,              new HallwayRoom(), null,               new ExitRoom() }
    };

    public Map() {
        // Place keys
        layout[4][2].addItem(ItemType.RED_KEY.makeItem());
        layout[0][3].addItem(ItemType.GREEN_KEY.makeItem());
        layout[5][1].addItem(ItemType.BLUE_KEY.makeItem());
    }

    /**
     * Get the available exits for a given room.
     * @param room Room object
     * @return Directional<Room> object
     */
    public Directional<Room> getExits(Room room) {
        int positionX = 0;
        int positionY = 0;

        // Find current room position
        for (int i = 0; i < this.layout.length; i++) {
            for (int j = 0; j < this.layout[i].length; j++) {
                if (this.layout[i][j] == room) {
                    positionX = j;
                    positionY = i;
                    break;
                }
            }
        }

        Room exitNorth = null;
        Room exitEast = null;
        Room exitSouth = null;
        Room exitWest = null;

        // Find neighbour rooms
        if ((positionX - 1) >= 0) {
            exitWest = this.layout[positionY][positionX - 1];
        }
        if ((positionX + 1) < this.layout[positionY].length) {
            exitEast = this.layout[positionY][positionX + 1];
        }
        if ((positionY - 1) >= 0) {
            exitNorth = this.layout[positionY - 1][positionX];
        }
        if ((positionY + 1) < this.layout.length) {
            exitSouth = this.layout[positionY + 1][positionX];
        }

        return new Directional<>(exitNorth, exitSouth, exitEast, exitWest);
    }

    /**
     * Gets the starting/spawn room.
     * @return Room Optional
     */
    public Optional<Room> getStartingRoom() {
        for (var rooms : this.layout) {
            for (var room : rooms) {
                if (room == null) {
                    continue;
                }

                if (room.getName().equals(SpawnRoom.SPAWN_ROOM_NAME)) {
                    return Optional.of(room);
                }
            }
        }

        return Optional.empty();
    }
}
