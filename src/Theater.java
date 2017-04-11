
public class Theater {
	private Seat[] collection;
	
	public Theater() {
		newSeatRow(4);
	}

	public void newSeatRow(int rowLength) {
		for (int seatNumber=1; seatNumber <= rowLength; seatNumber++) {
			Seat seat = new Seat(1, seatNumber);
		}
	}
	
	public int getNumberOfSeats() {
		
		return numberOfSeats;
	}

}
