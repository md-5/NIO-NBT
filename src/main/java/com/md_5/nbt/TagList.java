package com.md_5.nbt;

import java.util.ArrayList;
import java.util.List;

public class TagList extends Tag<List<Tag<?>>> {

    public TagList(String name) {
        super(TagType.TAG_LIST, name, new ArrayList<Tag<?>>());
    }

    public void add(Tag tag) {
        getValue().add(tag);
    }
}
