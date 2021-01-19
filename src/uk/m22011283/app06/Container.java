package uk.m22011283.app06;

import java.util.Optional;
import java.util.Set;

/**
 * Allows objects to contain game items.
 */
public interface Container {
    Set<Item> getInventory();

    default void addItem(Item item) {
        this.getInventory().add(item);
    }
    default void removeItem(Item item) {
        this.getInventory().remove(item);
    }

    /**
     * Moves an item from current container to another.
     * @param item Item to move
     * @param container Where to move item
     */
    default void moveItem(Item item, Container container) {
        if (this.getInventory().contains(item)) {
            this.removeItem(item);
            container.addItem(item);
        }
    }

    /**
     * Finds the first item of a given type.
     * @param itemType Item type
     * @return Optional Item
     */
    default Optional<Item> findItem(ItemType itemType) {
        for (var item : this.getInventory()) {
            if (item.getItemType() == itemType) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }

    /**
     * Searches for an item in the container and returns the first match.
     * @param searchTerm Item name (or partial)
     * @return Optional Item
     */
    default Optional<Item> searchItem(String searchTerm) {
        for (var item : this.getInventory()) {
            if (item.getName().toLowerCase().contains(searchTerm)) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }
}
