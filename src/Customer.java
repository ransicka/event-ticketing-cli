public class Customer implements Runnable{
    private TicketPool ticketPool; //This is shared between Vendors and Customers
    private int customerRetrievalRate; //Frequency which the tickets will be removed from pool
    private int quantity; //Quantity customer willing to purchase

    public Customer(TicketPool ticketPool, int customerRetrievalRate, int quantity) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        for(int i=0; i<quantity;i++){
            Ticket ticket =ticketPool.buyTicket(); //Remove ticket from the pool
            //Printing details of bought tickets
            System.out.println("Ticket bought by "+Thread.currentThread().getName()+". Ticket is "+ticket);

            //Delay which the tickets will be removed
            try {
                Thread.sleep(customerRetrievalRate*1000);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }
}
