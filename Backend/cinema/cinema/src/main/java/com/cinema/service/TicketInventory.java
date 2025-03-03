package com.cinema.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.springframework.stereotype.Service;

@Service
public class TicketInventory {
    // A thread-safe queue to hold tickets
    private final BlockingQueue<String> ticketsQueue = new LinkedBlockingQueue<>();

    // Add ticket (Producer)
    public void addTicket(String ticket) throws InterruptedException {
        ticketsQueue.put(ticket); // thread-safe
    }

    // Purchase ticket (Consumer)
    public String purchaseTicket() throws InterruptedException {
        return ticketsQueue.take(); // thread-safe
    }

    // Check if tickets are available
    public boolean hasTickets() {
        return !ticketsQueue.isEmpty();
    }
}
