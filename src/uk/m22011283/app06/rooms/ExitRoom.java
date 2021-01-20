package uk.m22011283.app06.rooms;

import uk.m22011283.app06.GameState;
import uk.m22011283.app06.Player;

/**
 * Final room, if the player has unlocked all the keyholes, they will be
 * able to exit and win the game, otherwise they will give up and lose.
 */
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
        } else {
            player.sendMessage("Door locked, you give up.");

        }

        System.exit(0);
    }
}
