package com.epam.brest.jmp.multithread;

import com.epam.brest.jmp.multithread.util.FileWalker;
import com.epam.brest.jmp.multithread.util.ProgressReporter;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * Created by alexander_borohov on 6.6.17.
 */
public class FileScanner {

    public static void main(String[] args) throws InterruptedException {
        if (args.length > 0) {
            String dir = args[0];
            System.out.println("Trying to scan files in folder: " + dir);

            FileWalker walker = new FileWalker();

            walker.setDirectoryToScan(dir);

        /*    Thread reading = new Thread(walker);
            Thread reporting = new Thread(new ProgressReporter(walker));

            reading.start();
            reporting.start();

            reading.join();
            reporting.join();

            while (reading.isAlive()) {
                if (scanner.next("c").equals("c")) {
                    reading.interrupt();
                }
            }*/

            ForkJoinPool service = new ForkJoinPool();

            ForkJoinTask readingService = service.submit(walker);
            ForkJoinTask reportingService = service.submit(new ProgressReporter(walker));

            readingService.join();
            reportingService.join();

            if (readingService.isDone()) {
                System.out.println("Finished scanning: ");
                System.out.printf("%s: FilesCount: %d; DirsCount: %d; TotalSize: %.1f K"
                        , walker.getDirectoryToScan()
                        , walker.getFilesCount().intValue()
                        , walker.getDirsCount().intValue()
                        , walker.getTotalSize());
            }

            System.exit(0);


        } else {
            System.out.println("You've forgotten to specify a mandatory parameter");
            System.exit(1);
        }
    }
}


