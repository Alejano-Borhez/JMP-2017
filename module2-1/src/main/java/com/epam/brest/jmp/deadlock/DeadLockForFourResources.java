package com.epam.brest.jmp.deadlock;

/**
 * Class simulates a deadlock described in Task 3
 *
 * Created by alexander_borohov on 17.2.17.
 */
public class DeadLockForFourResources {

    /**
     * Creating 4 {@link FileResource} and 4 {@link FileWorker}
     * Then runs 4 {@link Thread} in each 1 worker works with its 2 resources
     * After some time of execution we can see that workers are waiting to lock monitors that are held by other workers
     *
     * @param args - provide any args but they won't be processed
     */
    public static void main(String[] args) {
        FileResource resource1 = new FileResource("resource1");
        FileResource resource2 = new FileResource("resource2");
        FileResource resource3 = new FileResource("resource3");
        FileResource resource4 = new FileResource("resource4");

        FileWorker worker1 = new FileWorker(resource1, resource2);
        FileWorker worker2 = new FileWorker(resource2, resource3);
        FileWorker worker3 = new FileWorker(resource3, resource4);
        FileWorker worker4 = new FileWorker(resource4, resource1);
        worker1.setName("worker1");
        worker2.setName("worker2");
        worker3.setName("worker3");
        worker4.setName("worker4");

        System.out.println("Starting work");

        new Thread(worker1).start();
        new Thread(worker2).start();
        new Thread(worker3).start();
        new Thread(worker4).start();

        System.out.println("Work is over");
    }
}
