package com.md_5.nbt;

public class TagShort extends Tag<Short> {

    public TagShort(String name, short value) {
        super(TagType.TAG_SHORT, name, value);
    }
}
