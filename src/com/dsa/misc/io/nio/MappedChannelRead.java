package com.dsa.misc.io.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedChannelRead {

    public static void main(String[] args) {
        FileInputStream fIn;
        FileChannel fChan;
        long fSize;
        MappedByteBuffer mBuf;
        try {
            // First, open a file for input.
            fIn = new FileInputStream("rand.json");
            // Next, obtain a channel to that file.
            fChan = fIn.getChannel();
            // Get the size of the file.
            fSize = fChan.size();
            // Now, map the file into a buffer.
            mBuf = fChan.map(FileChannel.MapMode.READ_ONLY,
                    0, fSize);
            // Read bytes from the buffer.
            for (int i = 0; i < fSize; i++)
                System.out.print((char) mBuf.get());

            fChan.close(); // close channel
            fIn.close();   // close file
        } catch (IOException exc) {
            exc.printStackTrace();
            System.exit(1);
        }

    }
}
