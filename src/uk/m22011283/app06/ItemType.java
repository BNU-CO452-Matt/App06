package uk.m22011283.app06;

/**
 * Represents an item type/id.
 */
public enum ItemType {
    //         name           description
    RED_KEY   ("Red Key",     "Maybe this will fit a red keyhole."),
    GREEN_KEY ("Green Key",   "Maybe this will fit a green keyhole."),
    BLUE_KEY  ("Blue Key",    "Maybe this will fit a blue keyhole.");

    public String name;
    public String description;

    ItemType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @return A new instance of Item with this item type
     */
    public Item makeItem() {
        return new Item(this);
    }
}
