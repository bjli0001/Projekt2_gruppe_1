import java.util.ArrayList;

public class Hold {

    static ArrayList<Hold> holdliste = new ArrayList<>();
    static ArrayList<String> holdNavne = new ArrayList<>();
    protected String holdnavn;
    protected String type;
    protected String alder;
    protected String træner;
    protected ArrayList<Medlem> svoemmer=new ArrayList<>();
    private ArrayList<SvømmeTid> svømmeTider = new ArrayList<>();

    Hold (String holdnavn, String type, String alder, String træner){
        this.holdnavn=holdnavn;
        this.type=type;
        this.alder=alder;
        this.træner=træner;
        holdliste.add(this);
        holdNavne.add(holdnavn);
    }

    // Se medlemmer på hold


    public static void opretHold() {

        new Hold("Senior K", "Konkurrence", "Senior","Træner Mike Oxlong");
        new Hold("Junior K", "Konkurrence", "Junior", "Træner Mette Polka");
        new Hold("Hyggeholdet S", "Motion", "Senior","Ingen træner");
        new Hold("Hyggeholdet J", "Motion", "Junior","Ingen træner");

    }

    public void tilføjSvømmetid(SvømmeTid svømmeTid){
        this.svømmeTider.add(svømmeTid);
    }

    public static void tilmeldSvømmehold(int navneIndex) {
        String age=Medlem.medlemmer.get(navneIndex).alder;
        String pro=Medlem.medlemmer.get(navneIndex).type;
        for (Hold i: holdliste){
            if (i.alder.equals(age)&&i.type.equals(pro)){
                i.svoemmer.add(Medlem.medlemmer.get(navneIndex));
                Medlem.medlemmer.get(navneIndex).hold=i.holdnavn;
                System.out.println("Svømmeren er tilmeldt "+i);
            }
        }


    }

    @Override
    public String toString() {
        return "Holdet "+holdnavn+" for "+type+" i aldersgruppen "+alder+" med træneren:"+træner;
    }
}