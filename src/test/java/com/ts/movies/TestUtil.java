package com.ts.movies;

import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class TestUtil {

    private final ClassLoader loader;

    public TestUtil() {
        loader = getClass().getClassLoader();
    }

    public String getResourceContents(String path) {
        try {
            return StreamUtils.copyToString(loader.getResourceAsStream(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error while reading resource:[" + path + "]");
        }
    }

}
