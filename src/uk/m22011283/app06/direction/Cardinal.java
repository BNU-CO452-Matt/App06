package uk.m22011283.app06.direction;

/**
 * Represents cardinal directions
 */
public enum Cardinal {
    NORTH("North"),
    SOUTH("South"),
    EAST("East"),
    WEST("West");

    private final String value;

    Cardinal(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
