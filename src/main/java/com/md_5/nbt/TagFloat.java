package com.md_5.nbt;

public class TagFloat extends Tag<Float> {

    public TagFloat(String name, float value) {
        super(TagType.TAG_FLOAT, name, value);
    }
}
