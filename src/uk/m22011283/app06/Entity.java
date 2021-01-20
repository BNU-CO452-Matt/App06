package uk.m22011283.app06;

/**
 * Represents a thing that exists in the game world.
 */
public abstract class Entity implements Examinable {
    private String name;
    private String description;

    /**
     * Construct an entity object.
     * @param name Name of entity
     * @param description Description of entity
     */
    public Entity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
