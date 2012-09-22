package com.md_5.nbt;

public class TagLong extends Tag<Long> {

    public TagLong(String name, long value) {
        super(TagType.TAG_LONG, name, value);
    }
}
