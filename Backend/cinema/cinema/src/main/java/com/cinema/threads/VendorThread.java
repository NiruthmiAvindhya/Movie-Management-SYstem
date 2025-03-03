package com.cinema.threads;

public class VendorThread implements Runnable {
    private String vendorName;

    public VendorThread(String vendorName) {
        this.vendorName = vendorName;
    }

    @Override
    public void run() {
        // Simulate adding movies by the vendor
        for (int i = 1; i <= 5; i++) {
            System.out.println("Vendor " + vendorName + " is adding movie " + i);
            try {
                Thread.sleep(1000); // Simulate processing time
            } catch (InterruptedException e) {
                System.out.println("Vendor " + vendorName + " was interrupted!");
            }
        }
        System.out.println("Vendor " + vendorName + " has finished adding movies.");
    }
}

