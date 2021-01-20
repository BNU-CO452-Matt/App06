package uk.m22011283.app06;

/**
 * Global game objective state
 */
public class GameState {
    private static boolean redKeyDone = false;
    private static boolean greenKeyDone = false;
    private static boolean blueKeyDone = false;

    private static int score = 0;

    public static boolean isRedKeyDone() {
        return GameState.redKeyDone;
    }
    public static boolean isGreenKeyDone() {
        return GameState.greenKeyDone;
    }
    public static boolean isBlueKeyDone() {
        return GameState.blueKeyDone;
    }

    /**
     * Complete red key objective and add to score.
     */
    public static void setRedKeyDone() {
        if (!GameState.redKeyDone) {
            GameState.score += 20;
            GameState.redKeyDone = true;
        }
    }

    /**
     * Complete green key objective and add to score.
     */
    public static void setGreenKeyDone() {
        if (!GameState.greenKeyDone) {
            GameState.score += 20;
            GameState.greenKeyDone = true;
        }
    }

    /**
     * Complete blue key objective and add to score.
     */
    public static void setBlueKeyDone() {
        if (!GameState.blueKeyDone) {
            GameState.score += 20;
            GameState.blueKeyDone = true;
        }
    }

    public static int getScore() {
        return GameState.score;
    }
}
