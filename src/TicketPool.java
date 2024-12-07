import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private Queue<Ticket> ticketQueue;
    private int maximumCapacity;

    public TicketPool(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
        this.ticketQueue = new LinkedList<>();

    }
    //Add ticket method which is used by Vendors to addTickets
    public synchronized void addTickets(Ticket ticket){ //Need to synchronize
        while (ticketQueue.size() >= maximumCapacity){
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();  //For CLI
                throw new RuntimeException(e.getMessage());
            }
        }
        this.ticketQueue.add(ticket); //Adding the ticket to the queue
        notifyAll(); //Notifying all the waiting threads
        //Print the message to show the thread name who added and the current size of the pool
        System.out.println(Thread.currentThread().getName() + " has added a ticket to the pool. Current size is "+ticketQueue.size());
    }
    //buyTicket method is used when customers are buying tickets
    public synchronized Ticket buyTicket(){
        while(ticketQueue.isEmpty()){
            try {
                wait();
            }catch (InterruptedException e){
                throw new RuntimeException(e.getMessage());
            }
        }
        Ticket ticket = ticketQueue.poll();  //Remove ticket from queue (front)
        notifyAll(); //Notify all waiting threads
        //Print the message to show the thread name who added and the current size of the pool
        System.out.println(Thread.currentThread().getName()+" has bought a ticket from the pool. Current pool size is "+ticketQueue.size());
        return ticket;
    }
}
