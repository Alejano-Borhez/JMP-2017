package com.epam.brest.jmp.deadlock;

/**
 * Class holds two {@link FileResource} to work on in concurrent mode
 *
 * Created by alexander_borohov on 17.2.17.
 */
public class FileWorker implements Runnable {
    private final FileResource resource1;
    private final FileResource resource2;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Creates an instance of FileWorker
     * @param resource1 - first {@link FileResource} to work on
     * @param resource2 - second {@link FileResource} to work on
     */
    public FileWorker(FileResource resource1, FileResource resource2) {
        this.resource1 = resource1;
        this.resource2 = resource2;
        System.out.println("Created new FileWorker");
    }

    /**
     * Working on {@link FileResource} consequentially as we assume that some data in the first is needed
     * to work with second while preventing concurrent I/O operations by other {@link FileWorker}
     *
     */
    public synchronized void run() {
        synchronized (resource1) {
            resource1.underWork(this);
            System.out.println("FileWorker: " + getName() + " tries to work on " + resource2.getName());
            synchronized (resource2) {
                resource2.underWork(this);
            }
            System.out.println("FileWorker: " + getName() + " ended to work on " + resource2.getName());
        }
    }
}
