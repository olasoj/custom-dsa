package com.dsa.misc.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CountLongWords {

    public static void main(String[] args) throws IOException {
        String absolutePath = System.getProperty("user.dir");
        Path path = Paths.get(absolutePath + "/rand.json");

        var contents = Files.readString(path);
        List<String> words = new java.util.ArrayList<>(List.of(contents.split("\\PL+")));

        long count = 0;

        for (String w : words) {
            if (w.length() > 6) count++;
        }

        System.out.println(count);
        count = words.stream().filter(w -> w.length() > 12).count();
        System.out.println(count);
        count = words.parallelStream().filter(w -> w.length() > 12).count();
        System.out.println(count);
    }
}
