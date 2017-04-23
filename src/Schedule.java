import java.util.ArrayList;
import java.util.HashMap;
/*
 * der Spielplan fürs Kino, funktioniert erstmal nur für einen Tag und ohne auf die Zeit richtig zu achten
 */
class Schedule {
    private ArrayList<Theatre> theatres = new ArrayList<Theatre>();
    private ArrayList<Screening> showsOnCurrentDay = new ArrayList<Screening>();
    // gibt jedem Screening automatisch beim Erstellen eine Nummer, sodass sie einfach unterscheidbar sind
    private int screeningNumber;

    public Schedule() {
        setUp();
        screeningNumber = 0;
    }
    
    public void setUp() {
        Theatre theatre1 = new Theatre(1);
        theatre1.addSeatRow(3, 1);
        theatre1.addSeatRow(3, 1.5);
        theatre1.addSeatRow(2, 2);
        theatres.add(theatre1);
        
        Theatre theatre2 = new Theatre(2);
        theatre2.addSeatRow(5, 1);
        theatre2.addSeatRow(4, 1);
        theatre2.addSeatRow(3, 1.5);
        theatre2.addSeatRow(2, 2);
        theatre2.addSeatRow(1, 3);
        theatres.add(theatre2);
        
        addScreening("Good Fellas", theatre1, 1);
        addScreening("Pulp Fiction", theatre1, 3);
        addScreening("Fargo", theatre1, 5);
        addScreening("Léon", theatre2, 5);
    }
    
    public Theatre getTheatre(int number) {
        for (Theatre theatre: theatres) {
            if (theatre.getNumber() == number) return theatre;
        }
        return null;
    }
    
    public int getTotalTheatres() {
        return theatres.size();
    }

    public void addScreening(String name, Theatre theatre, int time) {
        screeningNumber++;
        showsOnCurrentDay.add(new Screening(screeningNumber, name, theatre, time));    
    }

    public void showCurrentDay() {
        String result = "Today's Screenings: \n\n";
        String indent = "                  "; // 20 spaces.
        for (Screening screening: showsOnCurrentDay) {
            String space = indent.substring(0, 20 - screening.getName().length());
            result += "#" + screening.getNumber() + ":  " + screening.getTimeString() + " – " + screening.getName() + space + " → Theatre " + screening.getTheatreNumber() + "\n";
        }
        System.out.println(result);
    }

    /*
     * Methode für das Booking System um das richtige Screening zu finden CHECK-Methode
     */
    public  boolean screeningExists(int number) {
        for (Screening screening : showsOnCurrentDay) {
            if (screening.getNumber() == number) {
                return true;
            }
        }
        return false;
    }

    public Screening getScreening(int number) {
        for (Screening screening : showsOnCurrentDay) {
            if (screening.getNumber() == number) {
                return screening;
            }
        }
        return null;
    }

    public int getTotalScreenings() {
        return showsOnCurrentDay.size();
    }
}
