package com.dsa.misc.io.nio.file;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReadSmallFileSync {

    public static void main(String[] args) throws IOException {
        Path ballPath = Paths.get("/Users/olasoj/Downloads", "address_data.csv");
        byte[] ballArray = Files.readAllBytes(ballPath);

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(ballArray), StandardCharsets.UTF_8))) {

            List<String> noInput = bufferedReader.lines()
                    .skip(1)
                    .map(line -> {

                        if (Objects.isNull(line)) throw new IllegalStateException("No input");
                        String[] tokens = line.split(",");


                        String s = Arrays.toString(tokens);
                        System.out.println(tokens.length);
                        if (tokens.length != 4) throw new IllegalStateException("Incomplete address" + s);

                        return s;
                    })
                    .collect(Collectors.toList());
            System.out.println(noInput);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }
}
