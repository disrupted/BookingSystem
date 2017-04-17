import java.util.HashSet;
/**
 * This class is a blueprint for all the differnt theaters of a cinema. With the following Methods
 * every subclass, which are representing the specific theaters can be converted to an invidual theater within a few lines.
 */
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

    public HashSet getSeats(){
        return seats;
    }

    public String getSeatStatus() {
        String result = "";
        boolean lastRow = false;
        int currentRow = 1;
        HashSet<Seat> currentRowSeats = new HashSet<Seat>();
        while(!lastRow){
            for (Seat seat : seats) {
                if(seat.getRow()==currentRow){
                    String status = "";
                    if (seat.isBooked()) status = "booked"; else status = "free";    
                    result += "row: " + seat.getRow() + " - number: " + seat.getNumber() + " - " + status + "\n";
                    currentRowSeats.add(seat);
                }
            }
            if(currentRowSeats.size()<1){ lastRow = true;}
            currentRowSeats.clear();
            currentRow++;
        }
        return result;
    }
    
    /*
     * old method, seats are not sorted but easier to understand
     
      public String getSeatStatus() {
        String result = "";
        for (Seat seat : seats) {
            String status = "";
            if (seat.isBooked()) status = "booked"; else status = "free";    
            result += "row: " + seat.getRow() + " - number: " + seat.getNumber() + " - " + status + "\n";
        }
        return result;
    }
    */
}
