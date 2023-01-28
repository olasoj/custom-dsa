package com.dsa.misc.io.nio.path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DemoPath {
    public static final String ABSOLUTE_PATH = System.getProperty("user.dir");

    public static void main(String[] args) throws IOException {

        Path workRelative = Paths.get("employee.dat");
        Path workPath = Paths.get(ABSOLUTE_PATH).resolve(workRelative);//Appends the base path to the relative path

        Path toAbsolutePath = workRelative.toAbsolutePath();

        for (Path path : toAbsolutePath)
            System.out.println(path);

        System.out.println(toAbsolutePath.equals(workRelative));
        boolean check = Files.isSameFile(toAbsolutePath, workRelative);
        System.out.println(check);

        int compareTo = workPath.compareTo(workRelative);
        System.out.println(compareTo);

        System.out.println(toAbsolutePath);
        System.out.println(workRelative.getFileName());
        System.out.println(workRelative.getFileSystem());
        System.out.println(toAbsolutePath.getParent().getNameCount());
        System.out.println(toAbsolutePath.getParent().subpath(0, 3));
        System.out.println(workRelative.getNameCount());
        System.out.println(toAbsolutePath.getRoot());
        System.out.println(ABSOLUTE_PATH);
        System.out.println(workPath);

        Path path = Paths.get("../test.txt");
        System.out.println(path);
        System.out.println(path.normalize());

//        Files.createFile()
    }
}
