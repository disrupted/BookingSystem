import java.util.ArrayList;
/**
 * This class is a blueprint for all the differnt theaters of a cinema. With the following Methods
 * every subclass, which are representing the specific theaters can be converted to an invidual theater within a few lines.
 */
public class Theatre {
    private ArrayList<Seat> seats;
    protected int rowNumber = 0;
    protected String name;

    public Theatre() {
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

    public int getNumberOfSeats() {
        return seats.size();
    }

    public ArrayList getSeats() {
        return seats;
    }

    public String getSeatStatus() {
        String result = "\n rows  |   seats\n–––––––––––––––––––––––––––––";
        for (int currentRow = 1; currentRow <= seats.get(seats.size() - 1).getRow(); currentRow++) { // for each row of seats
            result += "\n  #" + currentRow + "   |  ";
            for (int i = 0; i < seats.size(); i++) {
                if (seats.get(i).getRow() == currentRow) {
                    if (seats.get(i).isBooked()) result += " X "; else result += " " + seats.get(i).getNumber() + " ";
                }
            }
        }
        return result + "\n";
    }
}
