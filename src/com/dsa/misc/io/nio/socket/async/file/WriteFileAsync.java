package com.dsa.misc.io.nio.socket.async.file;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

import static com.dsa.misc.io.nio.path.DemoPath.ABSOLUTE_PATH;

public class WriteFileAsync {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.wrap("The win keeps Nadal at the top of the heap in men's tennis, at least for a few more weeks. The world No2, Novak Djokovic, dumped out here in the semi-finals by a resurgent Federer, will come hard at them again at Wimbledon but there is much to come from two rivals who, for seven years, have held all pretenders at bay.".getBytes());

        Path path = Paths.get(ABSOLUTE_PATH, "story.txt");
        int count = 0;

        try (AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.DELETE_ON_CLOSE)) {
            Future<Integer> result = asynchronousFileChannel.write(buffer, 100);
            while (!result.isDone()) {
                System.out.println("Do something else while writing ...");
                count++;
            }
            System.out.println("Written done: " + result.isDone());
            System.out.println("Bytes written: " + result.get());
            System.out.println(count);
        } catch (Exception ex) {
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
