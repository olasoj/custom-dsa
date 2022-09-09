package com.dsa.misc.networking.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadedEchoServer {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        try (var s = new ServerSocket(8189)) {
            int i = 1;

            while (true) {
                Socket incoming = s.accept();
                System.out.println("Spawning " + i);
                Runnable r = new ThreadedEchoHandler(incoming);

//                executorService.submit(r);
                executorService.execute(r);
//                var t = new Thread(r);
//                t.start();
                i++;
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}

class ThreadedEchoHandler implements Runnable {
    private final Socket incoming;

    /**
     * Constructs a handler.
     *
     * @param incomingSocket the incoming socket
     */
    public ThreadedEchoHandler(Socket incomingSocket) {
        incoming = incomingSocket;
    }

    @Override
    public void run() {
        try (
                InputStream inStream = incoming.getInputStream();
                OutputStream outStream = incoming.getOutputStream();
                var in = new Scanner(inStream, StandardCharsets.UTF_8);
                var out = new PrintWriter(new OutputStreamWriter(outStream, StandardCharsets.UTF_8), true /* autoFlush */)
        ) {

            out.println("Hello! Enter BYE to exit.");
            // echo client input
            var done = false;
            while (!done && in.hasNextLine()) {
                String line = in.nextLine();
                out.println("Echo: " + line);
                if (line.trim().equals("BYE"))
                    done = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
