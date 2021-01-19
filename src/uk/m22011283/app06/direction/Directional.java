package uk.m22011283.app06.direction;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class Directional<T> implements Iterable<Direction<T>> {
    private final Direction<T> north;
    private final Direction<T> south;
    private final Direction<T> east;
    private final Direction<T> west;

    public Directional(T north, T south, T east, T west) {
        this.north = new Direction<>(north, Cardinal.NORTH);
        this.south = new Direction<>(south, Cardinal.SOUTH);
        this.east = new Direction<>(east, Cardinal.EAST);
        this.west = new Direction<>(west, Cardinal.WEST);
    }

    public T getNorth() {
        return this.north.getValue();
    }
    public T getSouth() {
        return this.south.getValue();
    }
    public T getEast() {
        return this.east.getValue();
    }
    public T getWest() {
        return this.west.getValue();
    }

    public Direction<T> getDirection(Cardinal cardinal) {
        return switch (cardinal) {
            case NORTH -> this.north;
            case SOUTH -> this.south;
            case EAST -> this.east;
            case WEST -> this.west;
        };
    }

    @Override
    public Iterator<Direction<T>> iterator() {
        List<Direction<T>> directions = Arrays.asList(
                this.north, this.east, this.south, this.west);

        return new Iterator<>() {
            private int index;

            public boolean hasNext() {
                return this.index < directions.size();
            }

            @Override
            public Direction<T> next() {
                return directions.get(this.index++);
            }
        };
    }
}

