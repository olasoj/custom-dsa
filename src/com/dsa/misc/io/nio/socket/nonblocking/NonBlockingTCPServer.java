package com.dsa.misc.io.nio.socket.nonblocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import static com.dsa.misc.io.nio.socket.BlockServerSocket.DEFAULT_PORT;

public class NonBlockingTCPServer {

    private static final Instant INSTANT = Instant.now();
    private final Map<SocketChannel, List<byte[]>> keepDataTrack = new HashMap<>();
    private final ByteBuffer buffer = ByteBuffer.allocate(2 * 1024);

    public static void main(String[] args) {
        NonBlockingTCPServer main = new NonBlockingTCPServer();
        main.startEchoServer();
    }

    private void startEchoServer() {
        //open Selector and ServerSocketChannel by calling the open() method
        try (Selector selector = Selector.open();
             ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {

            //check that both of them were successfully opened
            if ((serverSocketChannel.isOpen()) && (selector.isOpen())) {
                //configure non-blocking mode
                serverSocketChannel.configureBlocking(false);

                //set some options
                serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 256 * 1024);
                serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);

                //bind the server socket channel to port
                serverSocketChannel.bind(new InetSocketAddress(DEFAULT_PORT));
                //register the current channel with the given selector
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

                //display a waiting message while ... waiting!
                System.out.println("Waiting for connections ...");
                while (true) {
                    //wait for incoming events
                    selector.select();
                    //there is something to process on selected keys
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> keys = selectionKeys.iterator();

                    while (keys.hasNext()) {
                        SelectionKey selectionKey = keys.next();
                        //prevent the same key from coming up again
                        keys.remove();
                        if (!selectionKey.isValid()) continue;

                        if (selectionKey.isAcceptable()) acceptOP(selectionKey, selector);
                        else if (selectionKey.isReadable()) this.readOP(selectionKey);
                        else if (selectionKey.isWritable()) this.writeOP(selectionKey);
                    }
                    boolean before = INSTANT.plus(Duration.ofMinutes(2)).isBefore(Instant.now());
                    if (before) break;
                }

            } else {

                System.out.println("The server socket channel or selector cannot be opened!");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //isAcceptable returned true
    private void acceptOP(SelectionKey key, Selector selector) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverChannel.accept();
        socketChannel.configureBlocking(false);
        System.out.println("Incoming connection from: " + socketChannel.getRemoteAddress());
        //write a welcome message
        socketChannel.write(ByteBuffer.wrap("Hello!\n".getBytes(StandardCharsets.UTF_8)));
        //register channel with selector for further I/O
        keepDataTrack.put(socketChannel, new ArrayList<>());
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    //isReadable returned true
    private void readOP(SelectionKey key) {
        try {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            buffer.clear();
            int numRead = -1;

            try {
                numRead = socketChannel.read(buffer);
            } catch (IOException e) {
                System.err.println("Cannot read error!");
            }

            if (numRead == -1) {
                this.keepDataTrack.remove(socketChannel);
                System.out.println("Connection closed by: " + socketChannel.getRemoteAddress());
                socketChannel.close();
                key.cancel();
                return;
            }

            byte[] data = new byte[numRead];
            System.arraycopy(buffer.array(), 0, data, 0, numRead);
            System.out.println(new String(data, StandardCharsets.UTF_8) + " from " + socketChannel.getRemoteAddress());
            // write back to client
            doEchoJob(key, data);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //isWritable returned true
    private void writeOP(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        List<byte[]> channelData = keepDataTrack.get(socketChannel);
        Iterator<byte[]> its = channelData.iterator();
        while (its.hasNext()) {
            byte[] it = its.next();
            its.remove();
            socketChannel.write(ByteBuffer.wrap(it));
        }
        key.interestOps(SelectionKey.OP_READ);
    }

    private void doEchoJob(SelectionKey key, byte[] data) {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        List<byte[]> channelData = keepDataTrack.get(socketChannel);
        channelData.add(data);
        key.interestOps(SelectionKey.OP_WRITE);
    }
}

