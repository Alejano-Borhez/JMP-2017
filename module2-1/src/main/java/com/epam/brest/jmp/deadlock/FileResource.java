package com.epam.brest.jmp.deadlock;

/**
 * Class holds some data to work with by {@link FileWorker}
 * Created by alexander_borohov on 17.2.17.
 */
public class FileResource {
    private String name;

    /**
     * Performs useful work for {@link FileWorker}
     * @param worker - a worker that has access to this resource
     */
    public void underWork(FileWorker worker) {
        try {
//            Doing some useful stuff here
            wait(10);
            System.out.printf("%s under work by %s\n",getName(), worker.getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return - name of this Resource
     */
    public String getName() {
        return name;
    }

    /**
     * Creates an instance of {@link FileResource}
     * @param name
     */
    public FileResource(String name) {
        this.name = name;
    }
}
