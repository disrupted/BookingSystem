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
    
    public int getNumber() {
        return number;
    }

    public int getNumberOfSeats() {
        return seats.size();
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
        int totalBooked = 0; // counts the total number of booked seats in this theatre
        String result = "\n#####  Theatre " + number + " seat map  #####\n################################\n\n rows  |   seats\n–––––––––––––––––––––––––––––——–";
        for (int currentRow = 1; currentRow <= seats.get(seats.size() - 1).getRow(); currentRow++) { // for each row of seats
            result += "\n  #" + currentRow + "   |  ";
            for (int i = 0; i < seats.size(); i++) {
                if (seats.get(i).getRow() == currentRow) {
                    if (seats.get(i).isBooked()) {
                        result += " X ";
                        totalBooked++;
                    } else {
                        result += " " + seats.get(i).getNumber() + " ";
                    }
                }
            }
        }
        if (totalBooked == 0) result += "\n\n→ ALL SEATS AVAILABLE";
        else if (totalBooked == seats.size()) result += "\n\n→ COMPLETELY BOOKED OUT";
        else result += "\n\n→ " + (seats.size() - totalBooked) + " REMAINING SEATS"; // prints number of remaining seats
        return result + "\n";
    }
}
