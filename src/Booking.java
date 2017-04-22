import java.util.HashSet;
public class Booking {
    HashSet<Seat> bookedSeats = new HashSet<>();
    private Customer customer;

    public Booking(Customer customer) {
        this.customer = customer;
    }

    /*
     * fügt einen gewünschten Sitzlatz zur Buchung hinze
     */
    public void bookSeat(Theatre myTheatre, int row, int seatNumber) {
        Seat seat = myTheatre.getSeat(row, seatNumber);
        if (seat != null) {
            if (seat.book()) {
                bookedSeats.add(seat);
                System.out.println("Seat reservation sucessfully added to reservation");
            } else {
                System.out.println("ERROR: Seat " + row + "-" + seatNumber + " is not available for booking");
            }
        } else { System.out.println("ERROR: Seat " + row + "-" + seatNumber + " doesn't exist"); }
    }

    public void bookAdjoinedSeat() {

    }

    /*
     * löscht eine ngewünschten Sitzplatz von der Buchung
     */
    public void unbookSeat(int row, int seatNumber) {
        for (Seat seat: bookedSeats){
            if ((seat.getRow() == row) && (seat.getNumber() == seatNumber)) {
                bookedSeats.remove(seat);
            }
        }
        System.out.println("there is no such seat booked");
    }

    /*
     * zeigt alle gewünschten Plätze dieser Buchung - wird vom Screening weiterbenutzt
     */
    public String showBookedSeats() {
        String result = "";
        if (bookedSeats.size() <= 0) {
            result += "there are no booked seats";
        } else {
            result += bookedSeats.size() + " booked seats: \n";
            for (Seat seat: bookedSeats) {
                result += "row: " + seat.getRow() + " number: " + seat.getNumber() + "\n";
            }
        }
        return result;
    }

    // Methode vom Screening benutzt um Sitzstatus upzudaten
    public HashSet getBookedSeats() {
        return bookedSeats;
    }

    /*
     * gibt uns den Kunden zurück, wird vom Screening weiterbenutzt
     */
    public Customer getCustomer() {
        return customer;
    }
}
