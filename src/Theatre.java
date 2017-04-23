import java.util.ArrayList;
/**
 * This class is a blueprint for all the differnt theaters of a cinema. With the following Methods
 * every subclass, which are representing the specific theaters can be converted to an invidual theater within a few lines.
 */
public class Theatre {
    private ArrayList<Seat> seats;
    protected int rowNumber = 0;
    protected String name;
    int number;

    public Theatre(int number) {
        this.number = number;
        seats = new ArrayList<Seat>();
    }

    public void addSeatRow(int rowLength, double seatPrice) {
        rowNumber++;
        for (int seatNumber=1; seatNumber <= rowLength; seatNumber++) {
            Seat seat = new Seat(rowNumber, seatNumber, seatPrice);
            seats.add(seat);
        }
    }

    public String getName() {
        return name;
    }
    
    public int getNumber() {
        return number;
    }

    public int getTotalSeats() {
        return seats.size();
    }
    
    public int getTotalRows() {
        return rowNumber;
    }
    
    public int getTotalSeats(int rowNumber) {
        int totalSeats = 0;
        for (Seat seat: seats) {
            if ((seat.getRow() == rowNumber)) {
                totalSeats++;
            }
        }
        return totalSeats;
    }
    
    public Seat getSeat(int row, int seatNumber) {
        for (Seat seat: seats) {
            if ((seat.getRow() == row) && (seat.getNumber() == seatNumber)) {
                return seat;
            }
        }
        return null;
    }

    public ArrayList getSeats() {
        return seats;
    }

    public String getSeatStatus() {
        double rowPrice = 0;
        String result = "\n#####  Theatre " + number + " seat map  #####\n################################\n\n rows  |  price  |   SEATS                \n–––––––––––––––––––––––––––––——––––––––––";
        for (int currentRow = 1; currentRow <= seats.get(seats.size() - 1).getRow(); currentRow++) { // for each row of seats
            result += "\n  #" + currentRow + "   |  " + getSeat(currentRow, 1).getPrice() + "0€  |  ";
            for (int i = 0; i < seats.size(); i++) {
                if (seats.get(i).getRow() == currentRow) {
                    if (seats.get(i).isBooked()) {
                        result += " X ";
                    } else {
                        result += " " + seats.get(i).getNumber() + " ";
                    }
                }
            }
        }
        int remaining = getRemainingSeats();
        if (remaining == seats.size()) result += "\n\n→ ALL SEATS AVAILABLE";
        else if (remaining < 1) result += "\n\n→ COMPLETELY BOOKED OUT";
        else result += "\n\n→ " + remaining + " REMAINING SEATS"; // prints number of remaining seats
        return result + "\n";
    }
    
    public int getRemainingSeats() {
        int totalBooked = 0; // counts the total number of booked seats in this theatre
        for (Seat seat : seats) {
            if (seat.isBooked()) totalBooked++;
        }
        return seats.size() - totalBooked;
    }
}
