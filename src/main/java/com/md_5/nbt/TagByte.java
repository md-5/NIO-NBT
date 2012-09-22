package com.md_5.nbt;

public class TagByte extends Tag<Byte> {

    public TagByte(String name, byte value) {
        super(TagType.TAG_BYTE, name, value);
    }
}
