
public class Screening {
    Theatre myTheatre;
    private String name;
    private int theatreNumber;
    private int time;
    private int price = 1; // default

    public Screening(String name, int theatreNumber, int time) {
        this.name = name;
        this.theatreNumber = theatreNumber;
        this.time = time;
        createTheatre();
    }

    public void createTheatre() {
        if (theatreNumber == 1) {
            myTheatre = new Theatre1();
        } else {
            System.out.println("No such theatre: " + theatreNumber);
        }
        System.out.println(myTheatre.getNumberOfSeats());
    }
}
