package com.cinema.threads;

public class ConsumerThread implements Runnable {
    private String consumerName;

    public ConsumerThread(String customerName) {
        this.consumerName = customerName;
    }

    @Override
    public void run() {
        // Simulate booking tickets by the customer
        for (int i = 1; i <= 3; i++) {
            System.out.println("Customer " +consumerName + " is booking ticket " + i);
            try {
                Thread.sleep(1500); // Simulate processing time
            } catch (InterruptedException e) {
                System.out.println("Customer " + consumerName + " was interrupted!");
            }
        }
        System.out.println("Customer " + consumerName + " has finished booking tickets.");
    }
}
