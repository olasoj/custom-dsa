package com.dsa.misc.concurrency;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompletableFutureDemo {
    private static final Pattern IMG_PATTERN = Pattern.compile("[<]\\s*[iI][mM][gG]\\s*[^>]*[sS][rR][cC]\\s*[=]\\s*['\"]([^'\"]*)['\"][^>]*[>]");
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private URL urlToProcess;

    public static void main(String[] args) throws IOException, InterruptedException {
        URL url = new URL("http://horstmann.com/index.html");
        CompletableFutureDemo completableFutureDemo = new CompletableFutureDemo();
        completableFutureDemo.run(url);
    }

    private static void printCurrentThread() {
        Thread thread = Thread.currentThread();
        System.out.println(thread);
    }

    public CompletableFuture<String> readPage(URL url) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                printCurrentThread();
                return new String(url.openStream().readAllBytes(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }, executor);
    }

    public List<URL> getImageURLs(String webpage) // not time-consuming
    {
        try {
            printCurrentThread();
            var result = new ArrayList<URL>();
            Matcher matcher = IMG_PATTERN.matcher(webpage);
            while (matcher.find()) {
                var url = new URL(urlToProcess, matcher.group(1));
                result.add(url);
            }
            System.out.println("Found URLs: " + result);
            return result;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public CompletableFuture<List<BufferedImage>> getImages(List<URL> urls) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                printCurrentThread();
                var result = new ArrayList<BufferedImage>();
                for (URL url : urls) {
                    result.add(ImageIO.read(url));
                    System.out.println("Loaded " + url);
                }
                return result;
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }, executor);
    }

    public void saveImages(List<BufferedImage> images) {
        System.out.println("Saving " + images.size() + " images");
        printCurrentThread();
        try {
            for (int i = 0; i < images.size(); i++) {
                String filename = "/Users/olasoj/Downloads" + (i + 1) + ".png";
                ImageIO.write(images.get(i), "PNG", new File(filename));
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } finally {
            executor.shutdown();
        }
    }

    public void run(URL url) throws IOException, InterruptedException {
        urlToProcess = url;
        CompletableFuture.completedFuture(url)
                .thenComposeAsync(this::readPage, executor)
                .thenApply(this::getImageURLs)
                .thenCompose(this::getImages)
                .thenAccept(this::saveImages);

        /**
         .thenComposeAsync(this::readPage, executor) .thenApply(this::getImageURLs) .thenCompose(this::getImages) .thenAccept(this::saveImages);
         or use the experimental HTTP client:
         HttpClient client = HttpClient.newBuilder().executor(executor).build(); HttpRequest request = HttpRequest.newBuilder(urlToProcess.toURI()).GET()
         .build();
         client.sendAsync(request, BodyProcessor.asString())
         .thenApply(HttpResponse::body).thenApply(this::getImageURLs)
         .thenCompose(this::getImages).thenAccept(this::saveImages);

         */
    }

}
