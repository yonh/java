package com.yonh.io;

import java.io.InputStream;

public class ResourceTest {
    public static void main(String[] args) {
        String path = "UserMapper.xml";
        InputStream input = Resources.getResourceAsStream(path);
        System.out.println(input);
    }
}
