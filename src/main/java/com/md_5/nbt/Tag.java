package com.md_5.nbt;

public abstract class Tag<T> {

    /**
     * The name of this tag.
     */
    private final String name;
    /**
     * The type of this tag.
     */
    private final TagType type;
    /**
     * This tags value.
     */
    private final T value;

    /**
     * Creates the tag with the specified name.
     *
     * @param name The name.
     */
    protected Tag(TagType type, String name, T value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    /**
     * Gets the name of this tag.
     *
     * @return The name of this tag.
     */
    public final String getName() {
        return name;
    }

    /**
     * Returns the type of this tag
     *
     * @return The type of this tag.
     */
    public TagType getType() {
        return type;
    }

    /**
     * Gets the value of this tag.
     *
     * @return The value of this tag.
     */
    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue().toString();
    }
}
