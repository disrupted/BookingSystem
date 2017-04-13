import java.util.HashSet;

public class Theatre {
    private HashSet<Seat> seats;
    protected int rowNumber = 0;
    protected String name;

    public Theatre() {
        seats = new HashSet<Seat>();
    }

    public void addSeatRow(int rowLength) {
        rowNumber++;
        for (int seatNumber=1; seatNumber <= rowLength; seatNumber++) {
            Seat seat = new Seat(rowNumber, seatNumber);
            seats.add(seat);
        }
    }

    public String getName() {
        return name;
    }

    public int getNumberOfSeats() {
        return seats.size();
    }

    public String getSeatStatus() {
        String result = "";
        for (Seat seat : seats) {
            String status = "";
            if (seat.isBooked()) status = "booked"; else status = "free";    
            result += "row: " + seat.getRow() + " - number: " + seat.getNumber() + " - " + status + "\n";
        }
        return result;
    }
}
