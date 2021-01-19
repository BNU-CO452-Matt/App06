package uk.m22011283.app06;

/**
 * Represents an item to be stored in a container and used by the player
 */
public class Item extends Entity {
    private final ItemType itemType;

    public Item(ItemType itemType) {
        super(itemType.name, itemType.description);
        this.itemType = itemType;
    }

    public ItemType getItemType() {
        return itemType;
    }
}
