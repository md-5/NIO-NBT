package com.md_5.nbt;

import java.util.LinkedHashMap;
import java.util.Map;

public class TagCompound extends Tag<Map<String, Tag<?>>> {

    public TagCompound(String name) {
        super(TagType.TAG_COMPOUND, name, new LinkedHashMap<String, Tag<?>>());
    }

    public void add(String label, Tag child) {
        getValue().put(label, child);
    }
}
