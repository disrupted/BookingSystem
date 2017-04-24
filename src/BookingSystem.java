import java.util.Scanner;
/*
 * Interface between the user and the programm, starts the programm with the main method and prints questions and status of the programm - like in the zuul game
 * a bit messy so far
 */
public class BookingSystem {
    private Customer customer;
    private Scanner reader;
    private Schedule schedule;
    boolean finished = false;

    public BookingSystem() {
        schedule = new Schedule();
        reader = new Scanner(System.in);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BookingSystem bs = new BookingSystem();
        bs.start();
    }

    private void printWelcome() {
        System.out.println("Welcome to Ultimate Booking System !");
        printMenu();
    }

    private void printMenu() {
        System.out.println("\nMain Menu:");
        //System.out.println("type \"show schedule\" to see today's screenings");
        System.out.println("type \"add show\" to add a show to the schedule\ntype \"quit\" when you are finished\n");
        System.out.println("----------------------------------------------");
        schedule.showCurrentDay();
        System.out.println("Please pick a Screening: (1-" + schedule.getTotalScreenings() + ")");
    }

    private void printGoodbye() {
        System.out.println("Goodbye !");
    }

    /*
     * im zuul-style implementiert
     */
    public void start() {
        printWelcome();

        while (!finished) {
            String input = getInput();
            int number = 0;
            try {
                number = Integer.parseInt(input);
            } catch (Exception e) { }
            if ((number > 0) && (schedule.screeningExists(number))) {
                Screening screening = schedule.getScreening(number);
                inspect(screening);
            } else { 
                switch (input) {
                    case "quit":
                    finished = true;
                    break;
                    case "show schedule":
                    schedule.showCurrentDay();
                    break;
                    case "add show":
                    addShow();
                    break;
                    default:
                    System.out.println("Invalid input.");
                    printMenu();
                    break;
                }
            }
        }
        printGoodbye();
    }

    /*
     * Show hinzufügen, das interface fragt nach Namen, Zeit und Theater
     */
    private void addShow() {
        System.out.println("Please enter the name of the screening: ");
        String name = reader.next();
        System.out.println("Please enter the time of the screening: (0-23)");
        String maybeTime = reader.next();
        System.out.println("Please enter the number of the theatre: (1-" + schedule.getTotalTheatres() + ")");
        String maybeNumber = reader.next();    
        if (maybeTime.matches("[0-9]+") && maybeTime.length() <= 2 && maybeNumber.matches("[0-9]+") && maybeNumber.length() == 1) {
            int time = Integer.parseInt(maybeTime);
            int theatreNumber = Integer.parseInt(maybeNumber);
            Theatre theatre = schedule.getTheatre(theatreNumber);
            if (theatre != null) {
                schedule.addScreening(name, theatre, time);
                System.out.println("\nSucessfully added a show to the schedule.\n");
            } else {
                System.out.println("ERROR: theatre" + theatreNumber + " doesn't exist.");
            }
        } else { System.out.println("\nERROR adding show. invalid input.\n"); }
    }

    private void inspect(Screening screening) {
        // in der Show drin
        boolean inspectingShow = true;
        System.out.println("\n" + screening.getName() +" @ " + screening.getTimeString() + " – Theatre " + screening.getTheatreNumber());
        System.out.println("type \"back\" to go back to the main menu\ntype \"show\" to show aviable seats\ntype \"book\" to go to the booking menu \n");
        while (!finished && inspectingShow) {
            String input = getInput();
            switch (input) {
                case "back":
                inspectingShow = false;
                printMenu();
                break;
                case "show":
                screening.getSeatStatus();
                break;
                case "book":
                book(screening);
                break;
            }
        }
    }

    private void book(Screening screening) {
        boolean menuBooking = true;
        System.out.println("Booking menu: \n");
        System.out.println("type \"new\" to create a new booking, \"edit\" to edit a booking");
        System.out.println("type \"show bookings\" see all bookings");
        System.out.println("type \"delete\" to delete a booking");
        System.out.println("type \"back\" when you're finished\n");
        while (!finished && menuBooking) {
            String input = getInput();
            switch (input) {
                case "back":
                menuBooking = false;
                break;
                case "show bookings":
                screening.showBookings();
                break;
                case "new":
                newBooking(screening);
                break;
                case "edit":
                Booking booking = customerLogin(screening);
                editBooking(screening, booking);
                break;
                case "delete":
                deleteBooking(screening);
                break;
            }
        }
    }

    private void newBooking(Screening screening) {
        System.out.println("Please enter your name:");
        String name = reader.next();
        System.out.println("Please enter your telephone number:");
        String telephoneNumber = reader.next();
        screening.createBooking(name, telephoneNumber);
        System.out.println("Successfully created booking draft for Customer!");
        // beim Erstellen eines Bookings wird man automatisch zu der Bearbeitung des Bookings weitergeleitet
        editBooking(screening, screening.getBooking(name));
    }

    private Booking customerLogin(Screening screening) {
        System.out.println("Please enter the name of customer assigned to the booking:");
        String name = reader.next();
        return screening.getBooking(name);
    }

    private void editBooking(Screening screening, Booking booking) {
        if (booking != null) {
            // edit booking
            System.out.println("What do you want to edit?\ntype \"add\" to add seat\ntype \"delete\" to delete seat\ntype \"back\" to go back\n");
            menu: while (true) {
                System.out.print("> ");
                String input = reader.nextLine();
                switch (input) {
                    case "back":
                    System.out.println("Booking menu: \n");
                    System.out.println("type \"new\" to create a new booking, \"edit\" to edit a booking");
                    System.out.println("type \"show bookings\" see all bookings");
                    System.out.println("or \"delete\" to delete a booking\ntype \"back\" to go back to inspection menu\n");
                    break menu;
                    case "add":
                    /* add seats to booking as long as it is successful */
                    nextBooking: while (addSeat(screening, booking)) {
                        System.out.println("Do you want to book another seat? (Y/N)");
                        switch (reader.next()) {
                            case "y":
                            break;
                            default:
                            break nextBooking;
                        }
                    }
                    break;
                    case "delete":
                    System.out.println("Please enter the row of the seat:");
                    int row = reader.nextInt();
                    System.out.println("Please enter the number of the seat:");
                    int number = reader.nextInt();
                    booking.unbookSeat(screening.getTheatre(), row, number);
                    System.out.println("Seat reservation sucessfully deleted from reservation");
                    break;
                }
            }
        } else {
            System.out.println("ERROR: requested Booking doesn't exist.");
        }
    }

    private boolean addSeat(Screening screening, Booking inspectedBooking) {
        try {
            screening.getSeatStatus();
            System.out.println("Please enter the row of the seat: (1-" + screening.getTheatre().getTotalRows() + ")");
            int row = reader.nextInt();
            System.out.println("Please enter the number of the seat: (1-" + screening.getTheatre().getTotalSeats(row) + ")");
            int number = reader.nextInt();
            System.out.println("Do you want to book this seat for " + inspectedBooking.convertEuro(screening.getTheatre().getSeat(row, number).getPrice()) + " ? (Y/N)");
            switch (reader.next()) {
                case "y": 
                inspectedBooking.bookSeat(screening.getTheatre(), row, number);
                return true;
                default:
                System.out.println("Booking cancelled.\n");
                break;
            }
        } catch (Exception e) { System.out.println("ERROR: " + e);}
        return false;
    }

    private void deleteBooking(Screening screening) {
        screening.deleteBooking(customerLogin(screening));
    }

    public String getInput() {
        System.out.print("> ");                     // print prompt
        String input = reader.nextLine().toLowerCase();
        if (input.equals("quit")) { finished = true; }
        return input;
    }
}
