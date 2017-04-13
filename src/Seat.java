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
}
