import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int maximumCapacity=getInput(input, "Enter maximum ticket capacity");
        int vendorCount=getInput(input, "Enter number of Vendors");
        int ticketReleaseRate=getInput(input, "Enter ticket release rate");
        TicketPool ticketPool = new TicketPool(maximumCapacity);

        Vendor[] vendors=new Vendor[10]; //Array of Vendors, for convenience, an array of objects is used
        for(int i=0; i<vendors.length; i++){
            vendors[i]=new Vendor(ticketPool, 20, 5);
            Thread vendorThread = new Thread(vendors[i], "Vendor-" +i); //Used 3rd Constructor of Thread class
            vendorThread.start(); //Start the Vendor thread
        }

        Customer[] customers = new Customer[10];
        for(int i=0; i< customers.length;i++){
            customers[i]=new Customer(ticketPool, 5, 5);
            Thread customerThread=new Thread(customers[i], "Customer-"+i);
            customerThread.start();
        }
    }
    private static int getInput(Scanner input, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(input.nextLine());
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Value must be positive. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
