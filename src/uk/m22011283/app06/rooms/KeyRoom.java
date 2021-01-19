package uk.m22011283.app06.rooms;

import uk.m22011283.app06.ItemType;
import uk.m22011283.app06.Player;
import uk.m22011283.app06.triggers.KeyRoomTrigger;

/**
 * Room that requires a key to enter.
 */
public class KeyRoom extends Room {
    /**
     * Key ItemType required to unlock/use the key room
     */
    private final ItemType keyItemType;

    /**
     * @param name Room name
     * @param keyItemType KeyRoom key item type
     */
    public KeyRoom(String name, ItemType keyItemType) {
        super(name, "Looks like this requires a key.");
        this.keyItemType = keyItemType;
        this.addTrigger(new KeyRoomTrigger());
    }

    public ItemType getKeyItemType() {
        return this.keyItemType;
    }

    @Override
    public void onEnter(Player player) {
        player.sendMessage("You see a keyhole in the centre of the room.");
    }

    @Override
    public boolean canEnter(Player player) {
        return player.findItem(this.keyItemType).isPresent();
    }
}
