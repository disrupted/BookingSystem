import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;
/**
 * Instances of this class are representing one specific show in the cinema, 
 * (the same movie on a different day or time is also a different show)
 * 
 * Responsibilities:
 */
public class Screening {
    Theatre myTheatre;
    private int number;
    private String name;
    private int theatreNumber;
    private int time;
    private int price = 1; // default
    private HashSet<Booking> myBookings;

    public Screening(int number, String name, int theatreNumber, int time) {
        this.name = name;
        this.number = number;
        this.theatreNumber = theatreNumber;
        this.time = time;
        createTheatre();
        myBookings = new HashSet<Booking>();
    }

    /*
     * Methods for the schedule-class
     */
    public int getNumber() {
        return number;
    }

    public int getTime() {
        return time;
    }

    public String getTimeString() {
        String zero = "";
        if (time < 10) { zero += "0"; }
        return zero + time + ":00";
    }    

    public void getDate() {
        // return Date  not yet implemented
    }

    public String getName() {
        return name;
    }
    
    public Theatre getTheatre() {
        return myTheatre;
    }

    public int getTheatreNumber() {
        return theatreNumber;
    }

    public String getDetails() {
        return "#" + number + ":  " + getTimeString() + " – " + name + " – theatre " + theatreNumber;
    }

    /*
     * erstellt das Theater in dem das Screening stattfindet
     */
    private void createTheatre() {
        switch (theatreNumber) {
            case 1:
            myTheatre = new Theatre1();
            break;
            case 2:
            myTheatre = new Theatre2();
            break;
            default:
            System.out.println("No such theatre: " + theatreNumber);
            break;
        }
    }

    /*
     * Übersicht aller Plätze - siehe Theater
     */
    public void getSeatStatus() {
        System.out.println(myTheatre.getSeatStatus());
    }

    /*
     * erstellt ein neues Booking für dieses Screening, dabei wird gleich das dazugehörige 
     * Customer Objekt erstellt, das Booking wird im Folgendem im Screening Objekt durch diesen Namen
     * findbar sein (vllcht sollte man es später mit einer Nummer ersetzen - falls 2 mal Thomas Schmidt ins Kino kommt ;)
     * 
     * @param Name und Telefonnummer des Kunden - siehe Customer-Klasse
     */
    public void createBooking(String name, String telephoneNumber) {
        myBookings.add(new Booking(new Customer(name,telephoneNumber)));

    }

    public void deleteBooking(String name) {
        Iterator<Booking> bi = myBookings.iterator();
        while (bi.hasNext()) {
            Booking currentBooking = bi.next();
            if (currentBooking.getCustomer().getName().equals(name)) {
                myBookings.remove(currentBooking);
                System.out.println("deleted sucessfully");
                return;
            }
        }
        System.out.println("Error - could not delete");
    }

    /*
     * returns a Booking from the screening-HashSet Collection of Bookings
     */
    public Booking getBooking(String name) {
        for (Booking booking: myBookings) {
            if (booking.getCustomer().getName().equals(name)) {
                return booking;
            }
        }
        return null;
    }

    /*
     * Übersicht über Buchungen – zeigt alle Buchungen nach Namen und dazugehörigen gebuchten Plätzen
     */
    public void showBookings() {
        String result = "";
        if (myBookings.size() > 0) {
            result += "Bookings for \"" + name + "\":";
            for (Booking booking: myBookings) {
                result += "\n\n" + booking.getCustomer().getName() + ":" + booking.showBookedSeats();
            }   
        } else {
            result += "There aren't any bookings for \"" + name + "\" yet.";
        }
        System.out.println(result + "\n");
    }
}

