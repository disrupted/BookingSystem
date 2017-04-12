import java.util.Scanner;

public class BookingSystem {
    private Customer customer;
    private Scanner reader;

    public BookingSystem() {
        Schedule schedule = new Schedule();
        reader = new Scanner(System.in);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BookingSystem bs = new BookingSystem();
        
        bs.start();
    }
    
    private void printWelcome() {
        System.out.println("Welcome to Ultimate Booking System !");
    }
    
    private void printGoodbye() {
        System.out.println("Goodbye !");
    }

    public void start() {
        boolean finished = false;

        printWelcome();

        while(!finished) {
            String input = getInput();

            if(input.contains("bye")) {
                finished = true;
            }
            else {
                if (input.equals("book")) {
                    System.out.println("booked");
                    //Booking booking = new Booking();
                }
            }
        }
        printGoodbye();
    }

    public String getInput() {
        System.out.print("> ");                // print prompt
        return reader.nextLine().trim().toLowerCase();
    }

    public void addCustomer(String name, String telephoneNumber) {
        customer = new Customer(name, telephoneNumber);
    }

}
