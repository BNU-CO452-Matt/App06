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
        GameState.score += 20;
        return GameState.redKeyDone;
    }
    public static boolean isGreenKeyDone() {
        GameState.score += 20;
        return GameState.greenKeyDone;
    }
    public static boolean isBlueKeyDone() {
        GameState.score += 20;
        return GameState.blueKeyDone;
    }

    public static void setRedKeyDone(boolean redKeyDone) {
        GameState.redKeyDone = redKeyDone;
    }
    public static void setGreenKeyDone(boolean greenKeyDone) {
        GameState.greenKeyDone = greenKeyDone;
    }
    public static void setBlueKeyDone(boolean blueKeyDone) {
        GameState.blueKeyDone = blueKeyDone;
    }

    public static int getScore() {
        return GameState.score;
    }
    public static void setScore(int score) {
        GameState.score = score;
    }
}
