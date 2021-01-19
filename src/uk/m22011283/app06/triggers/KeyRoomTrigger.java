package uk.m22011283.app06.triggers;

import uk.m22011283.app06.GameState;
import uk.m22011283.app06.Item;
import uk.m22011283.app06.Player;
import uk.m22011283.app06.rooms.KeyRoom;

/**
 * Key room trigger, allows player to interact with room
 */
public class KeyRoomTrigger extends Trigger {
    public KeyRoomTrigger() {
        super("Keyhole", "Perhaps you could use a key here.");
    }

    @Override
    public void onUse(Player player, Item item) {
        var keyRoom = (KeyRoom)player.getRoom();
        var keyRoomKeyItemType = keyRoom.getKeyItemType();

        // Check key item type against room key item type
        if (keyRoomKeyItemType == item.getItemType()) {
            player.sendMessage("Something happens...");

            // Advance game state
            switch (keyRoomKeyItemType) {
                case RED_KEY -> GameState.setRedKeyDone(true);
                case GREEN_KEY -> GameState.setGreenKeyDone(true);
                case BLUE_KEY -> GameState.setBlueKeyDone(true);
            }

            // Leave key in room
            player.dropItem(item);
            return;
        }

        player.sendMessage("It doesn't fit!");
    }
}
