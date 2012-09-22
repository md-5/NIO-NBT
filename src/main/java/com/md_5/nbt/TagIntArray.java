package com.md_5.nbt;

public class TagIntArray extends Tag<Integer[]> {

    public TagIntArray(String name, Integer[] value) {
        super(TagType.TAG_INT_ARRAY, name, value);
    }
}
