import java.util.HashSet;
/*
 * der Stundeplan fürs Kino, funktioniert erstmal nur für einen Tag und ohne auf die Zeit richtig zu achten
 */
class Schedule {

    HashSet<Screening> showsOnCurrentDay;
    private int screeningNumber; //gibt jedem Screening automatisch beim erstellen 
    //eine nummer, sodass sie einfach unterscheidbar sind

    public Schedule() {
        showsOnCurrentDay = new HashSet<Screening>();
        screeningNumber = 0;
        //Theatre1 theatre1 = new Theatre1();
        //System.out.println(theatre1.getName() + " - " + theatre1.getNumberOfSeats());
        //addScreening("Hulk", 1, 8); // Test movie at 8pm in theatre 1
    }

    public void addScreening(String name, int theatreNumber, int time) {
        screeningNumber++;        
        showsOnCurrentDay.add(new Screening(screeningNumber, name, theatreNumber, time));    
    }

    public void showCurrentDay(){
        String result = "screenings today: \n \n";
        {
            for(Screening screening: showsOnCurrentDay){
                result += screening.getTime()  + ": " + "Screening.no."+ screening.getNumber() + " - " + screening.getName() + " in theatre no." + screening.getTheatreNumber() + "\n";
            }
        }
        System.out.println(result);
    }

    /*
     * Methode für das Booking System um das richtige Screening zu finden
     */
    public  boolean isThereThisScreeningNumber(int number){
        for(Screening screening : showsOnCurrentDay){
            if(screening.getNumber() == number){
                return true;
            }
        }
        return false;
    }

    public Screening getScreeningByNumber(int number){
        for(Screening screening : showsOnCurrentDay){
            if(screening.getNumber() == number){
                return screening;
            }
        }
        return null;
    }
}
