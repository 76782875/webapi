package com.tubugs.springboot.utils;

import java.util.UUID;

/**
 * Created by xuzhang on 2017/6/22.
 */
public class UUIDUtil {

    public static String generate() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
