package com.dsa.misc.concurrency;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExecutorDemo {
    /**
     * Counts occurrences of a given word in a file.
     *
     * @return the number of times the word occurs in the given word
     */
    public static long occurrences(String word, Path path) {
        try (var in = new Scanner(path)) {
            int count = 0;
            while (in.hasNext())
                if (in.next().equals(word)) count++;
            return count;
        } catch (IOException ex) {
            return 0;
        }
    }

    /**
     * Returns all descendants of a given directory--see Chapters 1 and 2 of Volume II. * @param rootDir the root directory
     *
     * @return a set of all descendants of the root directory
     */
    public static Set<Path> descendants(Path rootDir) throws IOException {
        try (Stream<Path> entries = Files.walk(rootDir)) {
            return entries
                    .parallel()
                    .peek(p -> System.out.println(Thread.currentThread().getName()))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toSet());
        }
    }

    /**
     * Yields a task that searches for a word in a file.
     *
     * @param word the word to search
     * @param path the file in which to search
     * @return the search task that yields the path upon success
     */

    public static Callable<Path> searchForTask(String word, Path path) {
        return () -> {

            try (var in = new Scanner(path)) {
                while (in.hasNext()) {
                    if (in.next().equals(word)) return path;

                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Search in " + path + " canceled.");
                        return null;
                    }
                }
            }
            throw new NoSuchElementException();
        };
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        try (var in = new Scanner(System.in)) {

            System.out.print("Enter base directory (e.g. /opt/jdk-9-src): ");
            String start = in.nextLine();

            System.out.print("Enter keyword (e.g. volatile): ");
            String word = in.nextLine();

            Set<Path> files = descendants(Path.of(start));

            System.out.println("Number of files found " + files.size());

            var tasks = new ArrayList<Callable<Long>>();

            for (Path file : files) {
                Callable<Long> task = () -> occurrences(word, file);
                tasks.add(task);
            }

//            ExecutorService executor = Executors.newCachedThreadPool();
            ExecutorService executor = Executors.newFixedThreadPool(30);

            // use a single thread executor instead to see if multiple threads
            // speed up the search
//             ExecutorService executor = Executors.newSingleThreadExecutor();

            Instant startTime = Instant.now();

            List<Future<Long>> results = executor.invokeAll(tasks);
            long total = 0;
            for (Future<Long> result : results) total += result.get();

            Instant endTime = Instant.now();

            System.out.println("Occurrences of " + word + ": " + total);
            System.out.println("Time elapsed: " + Duration.between(startTime, endTime).toMillis() + " ms");

            var searchTasks = new ArrayList<Callable<Path>>();
            for (Path file : files) searchTasks.add(searchForTask(word, file));

            Path found = executor.invokeAny(searchTasks);

            System.out.println(word + " occurs in: " + found);
            if (executor instanceof ThreadPoolExecutor) // the single thread executor isn't
                System.out.println("Largest pool size: " + ((ThreadPoolExecutor) executor).getLargestPoolSize());

            executor.shutdown();
        }
    }
}
