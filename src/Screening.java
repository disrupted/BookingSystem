import java.util.HashSet;
public class Screening {
    Theatre myTheatre;
    private int number;
    private String name;
    private int theatreNumber;
    private int time;
    private int price = 1; // default
    private HashSet<Booking> myBookings;

    public Screening(int number, String name, int theatreNumber, int time) {
        this.name = name;
        this.number = number;
        this.theatreNumber = theatreNumber;
        this.time = time;
        createTheatre();
        myBookings = new HashSet<Booking>();
    }
    
    /*
     * Methoden für die Schedule..
     */
    public int getNumber(){
        return number;
    } 
    public int getTime(){
        return time;
    }    
     public void getDate(){
       // return Date  not yet implemented
    }
     public String getName(){
        return name;
    }    
     public int getTheatreNumber(){
        return theatreNumber;
    }
    

    /*
     * erstellt das Theater in dem das Screening stattfindet
     */
    private void createTheatre() {
        if (theatreNumber == 1) {
            myTheatre = new Theatre1();
        } else {
            System.out.println("No such theatre: " + theatreNumber);
        }
    }

    /*
     * Übersicht aller Plätze - siehe Theater
     */
    public void getSeatStatus() {
        System.out.println(myTheatre.getSeatStatus());
    }

    /*
     * erstellt ein neues Booking für dieses Screening, dabei wird gleich das dazugehörige 
     * Customer Objekt erstellt, das Booking wird im Folgendem im Screening Objekt durch diesen Namen
     * findbar sein (vllcht sollte man es später mit einer Nummer ersetzen - falls 2 mal Thomas Schmidt ins Kino kommt ;)
     * 
     * @param Name und Telefonnummer des Kunden - siehe Customer-Klasse
     */
    public void createBooking(String name, String telephoneNumber){
        myBookings.add(new Booking(new Customer(name,telephoneNumber)));
        //beim erstellen eines Bookings wird man automatisch zu der bearbeitung des bookings weitergeleitet
        editBooking(name);
    }

    /*
     * bearbeitet ein bereits erstelltes Booking - bucht Plätze dazu oder entfernt welche
     */
    public void editBooking(String name){
        Booking editableBooking;
        boolean bookingFound = false;
        for(Booking booking: myBookings){
            if(booking.getCustomer().getName().equals(name)){
                editableBooking = booking;
                bookingFound = true;
            }
        }
        //*CHECK* falls ein falscher Name des Booking-Customers eingegeben wurde
        if(bookingFound == false){
            System.out.println("cant edit, because there is no such booking");
            return;
        }

        //TODO  Anfrage vom System, was man mit diesem booking machen möchte - antworten wieder als input

    }

    /*
     * Übersicht über Buchungen - zeigt alle Buchungen nach Namen und dazugehörigen gebuchten Plätzen
     */
    public void showBookings(){
        String result = "";
        if(myBookings.size()>0){
            result += "bookings for " + name + ":" + "\n"+ "\n";
            for(Booking booking: myBookings){
                result += booking.getCustomer().getName() + ":\n";
                result += booking.showBookedSeats() + "\n" + "\n";
            }   
        }else{
            result += "there are no bookings for " + name;
        }
        System.out.println(result);
    }

    //dies updated die Anfragen von Sitzplätzen von den Bookings und den Theaterplätzen, sodass die Methode get Seat status immer aktuell ist
    //private void update()
}
