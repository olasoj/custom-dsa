package com.dsa.misc.io.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MappedChannelWrite {

    public static void main(String[] args) {

        RandomAccessFile fOut;
        FileChannel fChan;
        ByteBuffer mBuf;
        try {
            fOut = new RandomAccessFile("test.txt", "rw");

            // Next, obtain a channel to that file.
            fChan = fOut.getChannel();
            // Then, map the file into a buffer.
            mBuf = fChan.map(FileChannel.MapMode.READ_WRITE,
                    0, 26);
            // Write some bytes to the buffer.
            for (int i = 0; i < 26; i++)
                mBuf.put((byte) ('A' + i));
            // close channel and file.
            fChan.close();
            fOut.close();
        } catch (IOException exc) {
            System.out.println(exc);
            System.exit(1);
        }
    }
}
