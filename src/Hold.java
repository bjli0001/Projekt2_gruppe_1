import java.util.ArrayList;

public class Hold {

    static ArrayList<Hold> holdliste = new ArrayList<>();
    static ArrayList<String> holdNavne = new ArrayList<>();
    String holdnavn;
    String type;
    String alder;
    ArrayList<Medlem> svoemmer=new ArrayList<>();

    Hold (String holdnavn, String type, String alder){
        this.holdnavn=holdnavn;
        this.type=type;
        this.alder=alder;
        holdliste.add(this);
        holdNavne.add(holdnavn);
    }

    // Se medlemmer på hold


    static void opretHold() {

        new Hold("Senior K", "Konkurrence", "Senior");
        new Hold("Junior K", "Konkurrence", "Junior");
        new Hold("Hyggeholdet S", "Motion", "Senior");
        new Hold("Hyggeholdet J", "Motion", "Junior");

    }

    static void tilmeldSvømmehold(int navneIndex) {
        String age=Medlem.medlemmer.get(navneIndex).alder;
        String pro=Medlem.medlemmer.get(navneIndex).type;
        ArrayList<Integer> holdIndex = new ArrayList<>();
        int j=0;
        for (Hold i: holdliste){
            if (i.alder.equals(age)&&i.type.equals(pro)){
                holdIndex.add(j);
            }
            j++;
        }
        j=1;
        for(int i: holdIndex){

            System.out.println("Tast "+j+": "+holdliste.get(i));
            j++;
        }
        System.out.println("Vælg menupunkt [1,"+holdIndex.size()+"]:");
        Menu.op = Menu.inInt(holdliste.size());
        holdliste.get(holdIndex.get(Menu.op-1)).svoemmer.add(Medlem.medlemmer.get(navneIndex));

        Medlem.medlemmer.get(navneIndex).hold=holdliste.get(holdIndex.get(Menu.op-1)).holdnavn;
    }

    @Override
    public String toString() {
        return "Holdet "+holdnavn+" for "+type+" i aldersgruppen "+alder;
    }
}
