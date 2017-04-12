import java.util.ArrayList;

public class Theatre {
    private ArrayList<Seat> seats;
    protected int rowNumber = 0;
    protected String name;

    public Theatre() {
        seats = new ArrayList<>();
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

    public void showSeats() {
        String result = "";
        for (Seat seat : seats) {
            String status = "";
            if (seat.isBooked()) status = "booked"; else status = "free";    
            result += "row: " + seat.getRow() + " - number: " + seat.getNumber() + " - " + status + "\n";
        }
        System.out.println(result);
    }
}
