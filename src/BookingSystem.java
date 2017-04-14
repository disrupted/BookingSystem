import java.util.Scanner;

public class BookingSystem {
    private Customer customer;
    private Scanner reader;
    private Schedule schedule;

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
        System.out.println("\n Main Menu:");
        System.out.println("type \"show Schedule\" and then \"inspect\" to go to inspect the seat status or book");
        System.out.println("type \"add show\" to add a show to the schedule \n");
    }

    private void printGoodbye() {
        System.out.println("Goodbye !");
    }

    /*
     * im zuul-style implementiert
     */
    public void start() {
        printWelcome();
        boolean finished = false;

        while(!finished) {
            String input = getInput();
            if(input.contains("quit")) {
                finished = true;
            }
            else{
                if (input.equals("book")) {
                    System.out.println("booked");
                    //Booking booking = new Booking();
                }
                if (input.equals("show Schedule")){
                    schedule.showCurrentDay();
                }
                /*
                 * show hinzufügen, das interface fragt nach Namen, Zeit und Theater
                 */
                if (input.equals("add show")){
                    System.out.println("Please enter the name of the screening");
                    String name = reader.next();
                    System.out.println("Please enter the time of the screening");
                    String maybeTime = reader.next();
                    if(maybeTime.matches("[0-9]+") && maybeTime.length() <= 2) {
                        int time = Integer.parseInt(maybeTime);
                        System.out.println("Please enter the number of the theatre");
                        String maybeNumber = reader.next();
                        if(maybeNumber.matches("[0-9]+") && maybeNumber.length() == 1) {
                            int theatreNumber = Integer.parseInt(maybeNumber);
                            schedule.addScreening(name, theatreNumber, time);
                            System.out.println("you seucessfully added a show to the schedule");
                            System.out.println("\n Main menu: \n");
                        }else{
                            System.out.println("we have only theatres 1 - 6");
                            System.out.println("adding unsucessful");
                            System.out.println("\n Main menu: \n");
                        }
                    }else{
                        System.out.println("please use numbers from 0 to 23 for the time");
                        System.out.println("adding unsucessful");
                        System.out.println("\n Main menu: \n");
                    }
                }

                /*
                 * schow aussuchen    
                 */                                                
                if (input.equals("inspect")) {
                    boolean inspecting = true;
                    System.out.println("Inspecting menu: \n");
                    System.out.println("which show do you want to inspect? - type in the number, type stop to stop the inspection");
                    while(inspecting == true){
                        input = getInput();
                        if(input.equals("stop")){
                            inspecting = false;
                            System.out.println("\n Main menu: \n");
                        }

                        try{int numberToSearch = Integer.parseInt(input);

                            // in der Schow drin
                            if (schedule.isThereThisScreeningNumber(numberToSearch) == true){
                                Screening currentInspectedScreening = schedule.getScreeningByNumber(numberToSearch);
                                boolean inspectingShow = true;
                                System.out.println("\n You are currently inspecting " + currentInspectedScreening.getName() +" at " + currentInspectedScreening.getTime());
                                System.out.println("type \"back\" to go back to inspect all shows");
                                System.out.println("type \"show\" to show aviable seats, type \"book\" to go to the booking menu \n");
                                while(inspectingShow == true){
                                    input = getInput();
                                    if(input.equals("back")){
                                        inspectingShow = false;
                                        System.out.println("Inspecting menu: \n");
                                        System.out.println("which show do you want to inspect? - type in the number, type stop to stop the inspection");
                                    }
                                    if(input.equals("show")){
                                        currentInspectedScreening.getSeatStatus();
                                    }

                                    //booking
                                    if(input.equals("book")){
                                        boolean booking = true;
                                        System.out.println("Booking menu: \n");
                                        System.out.println("type \"new\" to create a new booking, \"edit\" to edit a booking");
                                        System.out.println("type \"show bookings\" see all bookings");
                                        System.out.println("or \"delete\" to delete a booking - type \"back\" to go back to Show-inspection menu");
                                        while(booking == true){
                                            input = getInput();
                                            if(input.equals("show bookings")){
                                                currentInspectedScreening.showBookings();
                                            }
                                            if(input.equals("back")){
                                                System.out.println("Show-Inspecting menu: \n");
                                                System.out.println("\n You are currently inspecting " + currentInspectedScreening.getName() +" at " + currentInspectedScreening.getTime());
                                                System.out.println("type \"back\" to go back to inspect all shows");
                                                System.out.println("type \"show\" to show aviable seats, type \"book\" to go to the booking menu \n");
                                                booking = false;
                                            }
                                            if(input.equals("new")){
                                                System.out.println("Please enter the name of customer");
                                                String name = reader.next();
                                                System.out.println("Please enter the telephone numbere of customer");
                                                String telephoneNumber = reader.next();
                                                currentInspectedScreening.createBooking(name, telephoneNumber);
                                                System.out.println("Sucessfuly created new booking");
                                                //beim erstellen eines Bookings wird man automatisch zu der bearbeitung des bookings weitergeleitet
                                            }
                                            if(input.equals("edit")){
                                                System.out.println("Please enter the name of customer assigned to the booking");
                                                String name = reader.next();
                                                if(currentInspectedScreening.bookingEditable(name)==true){
                                                    //edit booking
                                                    System.out.println("What do you want to edit? type \"add\" to add seat, type \"delete\" to delete seat, \"back\" to go back");
                                                    boolean bookingIsEdited =true;
                                                    Booking inspectedBooking =  currentInspectedScreening.getBooking(name);
                                                    while(bookingIsEdited){
                                                        input = getInput();
                                                        if(input.equals("back")){      
                                                            System.out.println("Booking menu: \n");
                                                            System.out.println("type \"new\" to create a new booking, \"edit\" to edit a booking");
                                                            System.out.println("type \"show bookings\" see all bookings");
                                                            System.out.println("or \"delete\" to delete a booking - type \"back\" to go back to inspection menu");
                                                            bookingIsEdited = false;
                                                        }
                                                        if(input.equals("add")){
                                                            System.out.println("Please enter the row of the seat");
                                                            int row = reader.nextInt();
                                                            System.out.println("Please enter the number of the seat");
                                                            int number = reader.nextInt();
                                                            inspectedBooking.bookSeat(row, number);
                                                            System.out.println("Seat reservation sucessfully added to reservation");
                                                        }
                                                        if(input.equals("delete")){
                                                            System.out.println("Please enter the row of the seat");
                                                            int row = reader.nextInt();
                                                            System.out.println("Please enter the number of the seat");
                                                            int number = reader.nextInt();
                                                            inspectedBooking.unbookSeat(row, number);
                                                            System.out.println("Seat reservation sucessfully deleted from reservation");
                                                        }
                                                    }
                                                }
                                            }
                                            if(input.equals("delete")){
                                                //ask for the name of the customer, so we can find the right booking to delete
                                                System.out.println("Please enter the name of customer assigned to the booking");
                                                String name = reader.next();
                                                currentInspectedScreening.deleteBooking(name);

                                            }
                                        }
                                    }

                                }
                            }
                        }catch(Exception e){}
                    }
                }

            }

        }
        printGoodbye();
    }

    private void editBooking(String name, Screening currentInspectedScreening){

    }

    public String getInput() {
        System.out.print("> ");                // print prompt
        return reader.nextLine();              // gibt die komplete line aus

        //return reader.nextLine().trim().toLowerCase();  - old one
    }

    public void addCustomer(String name, String telephoneNumber) {
        customer = new Customer(name, telephoneNumber);
    }

    private void userInterface(){

    }

}
