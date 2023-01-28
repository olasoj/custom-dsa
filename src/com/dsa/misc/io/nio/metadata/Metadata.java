package com.dsa.misc.io.nio.metadata;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;
import java.util.Set;

public class Metadata {

    public static void main(String[] args) {
        FileSystem fs = FileSystems.getDefault();
        Set<String> views = fs.supportedFileAttributeViews();
        for (String view : views) {
            System.out.println(view);
        }

        for (FileStore store : fs.getFileStores()) {
            boolean supported = store.supportsFileAttributeView(BasicFileAttributeView.class);
            System.out.println(store.name() + " ---" + supported);
        }


//        Get Bulk Attributes with readAttributes()
        BasicFileAttributes attr = null;
        Path path = Paths.get("employee.dat");

        try {
            attr = Files.readAttributes(path, BasicFileAttributes.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Objects.isNull(attr))
            return;

        System.out.println("File size: " + attr.size());
        System.out.println("File creation time: " + attr.creationTime());
        System.out.println("File was last accessed at: " + attr.lastAccessTime());
        System.out.println("File was last modified at: " + attr.lastModifiedTime());
        System.out.println("Is directory? " + attr.isDirectory());
        System.out.println("Is regular file? " + attr.isRegularFile());
        System.out.println("Is symbolic link? " + attr.isSymbolicLink());
        System.out.println("Is other? " + attr.isOther());


//        Get a Single Attribute with getAttribute()

    }
}
