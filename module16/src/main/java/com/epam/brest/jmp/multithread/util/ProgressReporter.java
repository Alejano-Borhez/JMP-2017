package com.epam.brest.jmp.multithread.util;

/**
 * Created by alexander_borohov on 6.6.17.
 */
public class ProgressReporter implements Runnable {
    private FileWalker fileWalker;

    public ProgressReporter(FileWalker fileWalker) {
        this.fileWalker = fileWalker;
    }

    @Override
    public void run() {
        String progressBar = "|/-\\";
        int progressCount = 0;
        while (fileWalker.isAlive()) {
            try {
                System.out.printf("%c Scanning %s in progress: FilesCount: %d; DirsCount: %d; TotalSize: %.1f K"
                        , progressBar.charAt(progressCount)
                        , fileWalker.getDirectoryToScan()
                        , fileWalker.getFilesCount().intValue()
                        , fileWalker.getDirsCount().intValue()
                        , fileWalker.getTotalSize());
                Thread.sleep(300);
                progressCount++;
                System.out.print("\r");
                System.out.flush();
                if (progressCount == progressBar.length()) progressCount = 0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
