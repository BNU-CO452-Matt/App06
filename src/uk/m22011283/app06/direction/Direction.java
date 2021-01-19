package uk.m22011283.app06.direction;

/**
 * Pair of generic value and cardinal direction
 * @param <T> Generic type value
 */
public class Direction<T> {
    private final T value;
    private final Cardinal cardinal;

    Direction(T value, Cardinal cardinal) {
        this.value = value;
        this.cardinal = cardinal;
    }

    /**
     * @return Generic value associated with a given direction
     */
    public T getValue() {
        return value;
    }

    /**
     * @return Cardinal direction
     */
    public Cardinal getDirection() {
        return cardinal;
    }
}
