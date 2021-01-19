package uk.m22011283.app06;

public enum ItemType {
    //         name           description
    RED_KEY   ("Red Key",     "Maybe this will fit a red keyhole."),
    GREEN_KEY ("Green Key",   "Maybe this will fit a green keyhole."),
    BLUE_KEY  ("Blue Key",    "Maybe this will fit a blue keyhole."),

    EXIT_NOTE_1("Exit Password 1", "Reads 'hello'."),
    EXIT_NOTE_2("Exit Password 2", "Reads 'world'.");

    public String name;
    public String description;

    private ItemType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Item makeItem() {
        return new Item(this);
    }
}
