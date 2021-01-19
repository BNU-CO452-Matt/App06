package uk.m22011283.app06.rooms;

import uk.m22011283.app06.GameState;
import uk.m22011283.app06.Player;

public class ExitRoom extends Room {
    public ExitRoom() {
        super("Exit Room", "The way out.");
    }

    @Override
    public void onEnter(Player player) {
        if (GameState.isRedKeyDone()
         && GameState.isGreenKeyDone()
         && GameState.isBlueKeyDone()) {
            player.sendMessage(String.format(
                    "You escaped!%nScore: %d", GameState.getScore()));
            System.exit(0);
        } else {
            player.sendMessage("Door locked, you failed.");
        }
    }
}
