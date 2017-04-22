import java.util.HashSet;
public class Booking {
    HashSet<Seat> bookedSeats = new HashSet<>();
    private Customer customer;

    public Booking(Customer customer) {
        this.customer = customer;
    }

    /*
     * fügt einen gewünschten Sitzlatz zur Buchung hinzu
     */
    public void bookSeat(Theatre myTheatre, int row, int seatNumber) {
        Seat seat = myTheatre.getSeat(row, seatNumber);
        try {
            if (seat.book()) {
                bookedSeats.add(seat);
                System.out.println("Seat reservation sucessfully added to reservation");
            } else {
                System.out.println("ERROR: Seat " + row + "-" + seatNumber + " is not available for booking");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: Seat " + row + "-" + seatNumber + " doesn't exist");
        }
    }

    public void bookAdjoinedSeat() {

    }

    /*
     * löscht einen Sitzplatz von der Buchung
     */
    public void unbookSeat(Theatre myTheatre, int row, int seatNumber) {
        Seat seat = myTheatre.getSeat(row, seatNumber);
        try {
            if (seat.unbook()) {
                bookedSeats.remove(seat);
                System.out.println("Seat reservation cancelled");
            } else {
                System.out.println("ERROR: Seat " + row + "-" + seatNumber + " is not part of your booking.");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: Seat " + row + "-" + seatNumber + " doesn't exist");
        }
    }

    /*
     * zeigt alle gewünschten Plätze dieser Buchung - wird vom Screening weiterbenutzt
     */
    public String showBookedSeats() {
        String result = "";
        if (bookedSeats.size() > 0) {
            result += bookedSeats.size() + " booked seat(s)";
            for (Seat seat: bookedSeats) {
                result += "\n → row " + seat.getRow() + " – seat " + seat.getNumber();
            }
        } else {
            result += "\n none";
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
