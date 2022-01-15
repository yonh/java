package com.yonh.io;

import java.io.InputStream;

public class Resources {
    // 将配置文件加载成字节输入流
    public static InputStream getResourceAsStream(String path) {
        InputStream input = Resources.class.getClassLoader().getResourceAsStream(path);
        return input;
    }
}
