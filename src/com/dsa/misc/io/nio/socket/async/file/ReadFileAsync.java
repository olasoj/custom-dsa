package com.dsa.misc.io.nio.socket.async.file;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

import static com.dsa.misc.io.nio.path.DemoPath.ABSOLUTE_PATH;

public class ReadFileAsync {

    public static void main(String[] args) {
        int count = 0;
        ByteBuffer buffer = ByteBuffer.allocate(100);
        String encoding = System.getProperty("file.encoding");

        Path path = Paths.get(ABSOLUTE_PATH, "employee.dat");

//        FileChannel.open()
        try (AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ)) {

            Future<Integer> result = asynchronousFileChannel.read(buffer, 0);
            while (!result.isDone()) {
                count++;
                System.out.println("Do something else while reading ...");
            }
            System.out.println("Read done: " + result.isDone());
            System.out.println("Bytes read: " + result.get());
        } catch (Exception ex) {
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
        buffer.flip();
        System.out.print(Charset.forName(encoding).decode(buffer));
        System.out.println(count);
        buffer.clear();
    }
}
