package com.md_5.nbt;

public class TagInt extends Tag<Integer> {

    public TagInt(String name, int value) {
        super(TagType.TAG_INT, name, value);
    }
}
