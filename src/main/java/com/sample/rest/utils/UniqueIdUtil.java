package com.sample.rest.utils;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UniqueIdUtil {

    public static String shortUUID() {
        UUID uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        return Long.toString(l, Character.MAX_RADIX);
    }
}
