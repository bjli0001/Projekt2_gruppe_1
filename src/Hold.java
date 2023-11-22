import java.util.ArrayList;

public class Hold {

    ArrayList<Hold> holdliste = new ArrayList<>();


    String holdnavn;
    String type;
    String alder;
    ArrayList<Medlem> svoemmer=new ArrayList<>();

    Hold (String holdnavn, String type, String alder){
        this.holdnavn=holdnavn;
        this.type=type;
        this.alder=alder;
        holdliste.add(this);
    }

    // Se medlemmer på hold


    static void opretHold() {

        new Hold("Senior K", "konkurrence", "senior");
        new Hold("Junior K", "konkurrence", "junior");
        new Hold("Hyggeholdet 2", "motion", "senior");
        new Hold("Hyggeholdet 1", "motion", "junior");



    }
}
