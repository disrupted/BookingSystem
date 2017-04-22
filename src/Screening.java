import java.util.HashSet;
import java.util.Iterator;
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
     * checkt ob das Booking vorhanden ist - eig redundadnt?
     */
    public boolean bookingEditable(String name) {
        Booking editableBooking;
        boolean bookingFound = false;
        for (Booking booking: myBookings) {
            if (booking.getCustomer().getName().equals(name)) {
                editableBooking = booking;
                bookingFound = true;
                return true;
            }
        }
        //*CHECK* falls ein falscher Name des Booking-Customers eingegeben wurde
        System.out.println("cant edit, because there is no such booking");
        return false;

    }

    /*
     * Übersicht über Buchungen - zeigt alle Buchungen nach Namen und dazugehörigen gebuchten Plätzen
     */
    public void showBookings() {
        // jedesmal wenn diese methode aufgerufen wird, wird der Status der seats upgedatet:
        updateSeats();
        String result = "";
        if (myBookings.size() > 0) {
            result += "bookings for " + name + ":" + "\n"+ "\n";
            for (Booking booking: myBookings) {
                result += booking.getCustomer().getName() + ":\n";
                result += booking.showBookedSeats() + "\n" + "\n";
            }   
        } else {
            result += "there are no bookings for " + name;
        }
        System.out.println(result);
    }

    /*
     * dies updated die Anfragen von Sitzplätzen von den Bookings und den Theaterplätzen, sodass die Methode getSeatStatus immer aktuell ist
     */
    private void updateSeats() {
        HashSet<Seat> theatreSeats = myTheatre.getSeats();
        //löscht alle Plätze von dem Theater - dies sorgt dafür, dass vom Booking entfernte Plätze nicht besetzt bleiben
        for (Seat theatreSeat : theatreSeats) {
            theatreSeat.bookedFalse();    
        }         
        for (Booking booking: myBookings) {
            HashSet<Seat> usedSeats = booking.getBookedSeats();

            //geht das Theatre durch und reserviert die in den Bookings eingetragenen Plätze
            for (Seat seat : usedSeats) {
                for (Seat theatreSeat : theatreSeats) {
                    if ((seat.getRow() == theatreSeat.getRow()) && (seat.getNumber() == theatreSeat.getNumber())) {
                        theatreSeat.changeStatus();
                    }
                }
            }
        }   
    }
}

