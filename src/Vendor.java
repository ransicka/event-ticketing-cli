import java.math.BigDecimal;

public class Vendor implements Runnable{
    private TicketPool ticketPool; //The ticketPool which is shared among customers and vendors
    private int totalTickets; //Total number of tickets the vendor will sell
    private int ticketReleaseRate; //Frequency in which the tickets will be added to the pool

    public Vendor(TicketPool ticketPool, int totalTickets, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    //Implement the thread
    //Runnable interface should write the implementation for runnable interface
    @Override
    public void run() {
        for(int i=1; i<=totalTickets; i++){
            //i is used as id
            Ticket ticket=new Ticket(i, "Event simple", new BigDecimal("1000"));
            ticketPool.addTickets(ticket);//Method in ticketPool to add tickets

            //The ticketReleaseRate means delay
            //Below converts the delay from seconds to milliseconds
            try{
                Thread.sleep(ticketReleaseRate*1000);
            }catch (InterruptedException e){
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
