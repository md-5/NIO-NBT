package com.md_5.nbt;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class NBTInputStream {

    private final CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
    private final ByteBuffer buf;

    public NBTInputStream(ByteBuffer buf) {
        this.buf = buf;
    }

    public TagCompound read() throws CharacterCodingException {
        return (TagCompound) readTag(true);
    }

    private Tag readTag(boolean hasName) throws CharacterCodingException {
        // read type id
        byte typeId = buf.get();
        // map to tag type
        TagType tag = TagType.values()[typeId];
        // read the tag body
        return readTag(tag, hasName);
    }

    private Tag readTag(TagType tag, boolean hasName) throws CharacterCodingException {
        // assign name
        String name = "";
        // skip name for some tags
        if (hasName && tag != TagType.TAG_END) {
            // read tag name
            name = readString();
        }
        // get ready to return a tag
        Tag ret = null;
        // switch
        switch (tag) {
            case TAG_BYTE:
                ret = new TagByte(name, buf.get());
                break;
            case TAG_BYTE_ARRAY:
                Byte[] byteArray = new Byte[buf.getInt()];
                for (int i = 0; i < byteArray.length; i++) {
                    byteArray[i] = buf.get();
                }
                ret = new TagByteArray(name, byteArray);
                break;
            case TAG_COMPOUND:
                TagCompound compound = new TagCompound(name);
                while (true) {
                    Tag child = readTag(true);
                    if (child == null) {
                        break;
                    } else {
                        compound.add(child.getName(), child);
                    }
                }
                ret = compound;
                break;
            case TAG_DOUBLE:
                ret = new TagDouble(name, buf.getDouble());
                break;
            case TAG_FLOAT:
                ret = new TagFloat(name, buf.getFloat());
                break;
            case TAG_END:
                return null;
            case TAG_INT:
                ret = new TagInt(name, buf.getInt());
                break;
            case TAG_INT_ARRAY:
                Integer[] intArray = new Integer[buf.getInt()];
                for (int i = 0; i < intArray.length; i++) {
                    intArray[i] = buf.getInt();
                }
                ret = new TagIntArray(name, intArray);
                break;
            case TAG_LIST:
                TagType listType = TagType.values()[buf.get()];
                int listLength = buf.getInt();
                TagList list = new TagList(name);
                for (int i = 0; i < listLength; i++) {
                    Tag child = readTag(listType, false);
                    list.add(child);
                }
                ret = list;
                break;
            case TAG_LONG:
                ret = new TagLong(name, buf.getLong());
                break;
            case TAG_SHORT:
                ret = new TagShort(name, buf.getShort());
                break;
            case TAG_STRING:
                ret = new TagString(name, readString());
                break;
            default:
                throw new RuntimeException("Don't know how to handle tag type: " + tag);
        }
        return ret;
    }

    private String readString() throws CharacterCodingException {
        // read length
        int length = buf.getShort();
        // only read up to the strings length
        buf.limit(buf.position() + length);
        // return the string
        String ret = decoder.decode(buf).toString();
        // go back to max length
        buf.limit(buf.capacity());
        // return the string
        return ret;
    }

    public static void main(String[] args) throws Exception {
        File file = new File("bigtest.nbt");
        long start, end;
        //
        start = System.currentTimeMillis();
        FileChannel chan = new FileInputStream(file).getChannel();
        ByteBuffer buf = chan.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
        new NBTInputStream(buf).read();
        end = System.currentTimeMillis();
        //
        System.out.println("NIO-NBT took: " + (end - start) + "ms");
    }
}
