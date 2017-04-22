/**
 * Object of this class are representing a seat throughout a screening 
 * (the same "real" seat is represented by a different instance on two different screenings in this programm)
 * 
 * Responsibilities: The seat object knows its row and number and know if it is booked or not,
 * also it can be booked or unbooked via the changeStatus() Method
 */
public class Seat {
    private int row, number;
    private boolean booked = false;

    public Seat(int rowNumber, int seatNumber) {
        this.row = rowNumber;
        this.number = seatNumber;
    }

    public int getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    public boolean isBooked() {
        return booked;
    }

    public void changeStatus() {
        booked = !booked;
    }
    
    public boolean book() {
        if (!booked) { 
            changeStatus();
            return booked; // returns true if booking was successful
        } else {
            return false;
        }
    }
    
    public boolean unbook() {
        if (booked) { 
            changeStatus();
            return !booked; // returns true if unbooking was successful
        } else {
            return false;
        }
    }
}
