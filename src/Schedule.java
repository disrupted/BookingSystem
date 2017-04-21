import java.util.HashSet;
/*
 * der Stundeplan fürs Kino, funktioniert erstmal nur für einen Tag und ohne auf die Zeit richtig zu achten
 */
class Schedule {
    HashSet<Screening> showsOnCurrentDay;
    // gibt jedem Screening automatisch beim Erstellen eine Nummer, sodass sie einfach unterscheidbar sind
    private int screeningNumber; 

    public Schedule() {
        showsOnCurrentDay = new HashSet<Screening>();
        screeningNumber = 0;

        addScreening("Good Fellas", 1, 1);
        addScreening("Pulp Fiction", 1, 3);
        addScreening("Fargo", 1, 5);
    }

    public void addScreening(String name, int theatreNumber, int time) {
        screeningNumber++;
        showsOnCurrentDay.add(new Screening(screeningNumber, name, theatreNumber, time));    
    }

    public void showCurrentDay() {
        String result = "Today: \n\n";
        String indent = "                  "; // 20 spaces.
        for (Screening screening: showsOnCurrentDay) {
            String space = indent.substring(0, 20 - screening.getName().length());
            result += "#" + screening.getNumber() + ":  " + screening.getTimeString() + " – " + screening.getName() + space + " → theatre " + screening.getTheatreNumber() + "\n";
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

    public int getTotalNumberOfScreenings() {
        return showsOnCurrentDay.size();
    }
}
