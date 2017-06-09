package com.epam.brest.jmp.multithread.util;

import static java.lang.System.currentTimeMillis;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * Created by alexander_borohov on 6.6.17.
 */
public class FileWalker implements Runnable {
    private String directoryToScan;
    private AtomicInteger filesCount;
    private AtomicInteger dirsCount;
    private volatile double totalSize;
    private volatile boolean isAlive;

    public String getDirectoryToScan() {
        return directoryToScan;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public AtomicInteger getFilesCount() {
        return filesCount;
    }

    public AtomicInteger getDirsCount() {
        return dirsCount;
    }

    public double getTotalSize() {
        return totalSize;
    }

    public void setDirectoryToScan(String directoryToScan) {
        this.isAlive = true;
        this.directoryToScan = directoryToScan;
        this.filesCount = new AtomicInteger(0);
        this.dirsCount = new AtomicInteger(0);
        this.totalSize = 0;
    }

    @Override
    public void run() {
        Path pathToScan = Paths.get(directoryToScan);
        System.out.println("Began with " + pathToScan.toAbsolutePath().toString());
        while (!Thread.currentThread().isInterrupted()) {
            try {
                long begin, end;
                boolean isParallel = false;

                begin = currentTimeMillis();
                long filesInside = Files.walk(pathToScan).filter(Files::isReadable).count();
                if (filesInside > 1000) {
                    isParallel = true;
                    Files.walk(pathToScan).parallel()
                            .forEach(filesDirsCounter());
                    end = currentTimeMillis() - begin;
                } else {
                    Files.walk(pathToScan)
                            .forEach(filesDirsCounter());
                    end = currentTimeMillis() - begin;
                }

                System.out.println("\r");
                System.out.println("Forked mode ended in " + end + "mSeconds" + ((isParallel)? "in parallel mode": "in single stream mode"));

                this.filesCount = new AtomicInteger(0);
                this.dirsCount = new AtomicInteger(0);
                this.totalSize = 0;

                begin = currentTimeMillis();
                Files.walk(pathToScan)
                        .forEach(filesDirsCounter());
                end = currentTimeMillis() - begin;

                System.out.println("Straight mode ended in " + end + "mSeconds" + ((isParallel)? "in parallel mode": "in single stream mode"));

                this.isAlive = false;
                Thread.currentThread().interrupt();
            } catch (IOException e) {
                System.out.println("e = " + e.getMessage());
            }
        }
    }

    private Consumer<Path> filesDirsCounter() {
        try {
            return path -> {
                if (Files.isReadable(path)) {
                    if (Files.isDirectory(path)) dirsCount.getAndIncrement();
                    if (Files.isRegularFile(path)) {
                        filesCount.getAndIncrement();
                        totalSize = totalSize + path.toFile().length() / 1024;
                    }
                }
            };
        } catch (UncheckedIOException e) {
            System.out.println("e = " + e.getMessage());
            return path -> {
            };
        }
    }
}
