package com.md_5.nbt;

public class TagByteArray extends Tag<Byte[]> {

    public TagByteArray(String name, Byte[] value) {
        super(TagType.TAG_BYTE_ARRAY, name, value);
    }
}
