package com.dsa.misc.io.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketOption;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class Socket {

    public static void main(String[] args) throws IOException {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.configureBlocking(true);

            System.out.println(serverSocketChannel.isOpen());

            Set<SocketOption<?>> options = serverSocketChannel.supportedOptions();
            for (SocketOption<?> option : options) System.out.println(option);

            serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);
            serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);

            final int DEFAULT_PORT = 5555;
            final String IP = "127.0.0.1";
            serverSocketChannel.bind(new InetSocketAddress(IP, DEFAULT_PORT));

            SocketChannel socketChannel = serverSocketChannel.accept();

            System.out.println(socketChannel.getRemoteAddress());

            //transmission code snippet
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

            while (socketChannel.read(buffer) != -1) {
                buffer.flip();
                socketChannel.write(buffer);
                if (buffer.hasRemaining()) {
                    buffer.compact();
                } else {
                    buffer.clear();
                }
            }
        }

    }
}
