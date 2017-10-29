package com.xiaogang.study.jerseytest.resources;

import java.util.UUID;

public class CommonUtils {
    public static String genUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
