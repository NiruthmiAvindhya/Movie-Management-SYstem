package com.cinema.threads;



public class CinemaThreading {
    public static void main(String[] args) {
        // Create threads for vendor and customer
        Thread vendorThread1 = new Thread(new VendorThread("Vendor A"));
        Thread vendorThread2 = new Thread(new VendorThread("Vendor B"));
        Thread customerThread1 = new Thread(new ConsumerThread("Customer X"));
        Thread customerThread2 = new Thread(new ConsumerThread("Customer Y"));

        // Start threads
        vendorThread1.start();
        vendorThread2.start();
        customerThread1.start();
        customerThread2.start();

        // Wait for threads to finish
        try {
            vendorThread1.join();
            vendorThread2.join();
            customerThread1.join();
            customerThread2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted!");
        }

        System.out.println("Cinema operations have completed!");
    }
}
