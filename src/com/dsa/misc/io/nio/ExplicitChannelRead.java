package com.dsa.misc.io.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ExplicitChannelRead {

    public static void main(String[] args) {
        FileInputStream fIn;
        FileChannel fChan;
        long fSize;
        ByteBuffer mBuf;
        try {
            // First, open a file for input.
            fIn = new FileInputStream("test.txt");
            // Next, obtain a channel to that file.
            fChan = fIn.getChannel();
            // Now, get the file's size.
            fSize = fChan.size();
            // Allocate a buffer of the necessary size.
            mBuf = ByteBuffer.allocate((int) fSize);
            // Read the file into the buffer.
            fChan.read(mBuf);
            // Rewind the buffer so that it can be read.
            mBuf.rewind();
            // Read bytes from the buffer.
            for (int i = 0; i < fSize; i++)
                System.out.print((char) mBuf.get());
            System.out.println();
            fChan.close(); // close channel
            fIn.close();   // close file
        } catch (IOException exc) {
            exc.printStackTrace();
            System.exit(1);
        }
    }
}
