package com.dsa.misc.io.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ExplicitChannelWrite {

    public static void main(String[] args) {
        FileOutputStream fOut;
        FileChannel fChan;
        ByteBuffer mBuf;


        try {
            fOut = new FileOutputStream("test.txt");
            // Get a channel to the output file.
            fChan = fOut.getChannel();
            // Create a buffer.
            mBuf = ByteBuffer.allocateDirect(26);
            // Write some bytes to the buffer.
            for (int i = 0; i < 26; i++)
                mBuf.put((byte) ('A' + i));
            // Rewind the buffer so that it can be written.
            mBuf.rewind();
            // Write the buffer to the output file.
            fChan.write(mBuf);
            // close channel and file.
            fChan.close();
            fOut.close();
        } catch (IOException exc) {
            exc.printStackTrace();
            System.exit(1);
        }
    }
}
