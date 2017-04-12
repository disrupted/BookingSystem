
class Schedule {

    public Schedule() {
        //Theatre1 theatre1 = new Theatre1();
        //System.out.println(theatre1.getName() + " - " + theatre1.getNumberOfSeats());
        addScreening("Hulk", 1, 8); // Test movie at 8pm in theatre 1
    }

    public void addScreening(String name, int theatreNumber, int time) {
        Screening screening = new Screening(name, theatreNumber, time);
    }
}
