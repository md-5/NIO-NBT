package com.md_5.nbt;

public class TagString extends Tag<String> {

    public TagString(String name, String value) {
        super(TagType.TAG_STRING, name, value);
    }
}
