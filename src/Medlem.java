import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.text.SimpleDateFormat;

public class Medlem {
    static ArrayList<Medlem> medlemmer = new ArrayList<>();
    static ArrayList<String> navne = new ArrayList<>();
    String navn;
    Date fødselsdag;
    // passiv/motion/konkurrence
    String type;
    String alder;
    String hold;
    int beløb;
    List<String> discipliner = new ArrayList<>();
    boolean betalt;
    static ArrayList<Integer> restanceIndex = new ArrayList<>();


    // Resultat class eller String[]?
    //ArrayList<> resultater;





    Medlem(String navn, Date fødselsdag, String type, List<String> disciplin, String hold, boolean betalt){
        this.navn=navn;
        this.fødselsdag=fødselsdag;
        this.type=type;
        this.discipliner=disciplin;
        this.hold=hold;
        this.betalt=betalt;

        if (Period.between(fødselsdag.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears()<18){
            alder="Junior";
            beløb = 1000;

        } else {alder="Senior";
            beløb = 1600;
        }

        if (Period.between(fødselsdag.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears()<=60) {

            beløb = 1200;
        }


        if (type.equals("Passiv")) {

            beløb = 500;

        }


        medlemmer.add(this);
        navne.add(navn);

        if (!betalt) {

            restanceIndex.add(medlemmer.size() - 1);

        }
    }


    //Dato format
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    static Scanner input = new Scanner(System.in);
    static boolean cont;

    public static void indlæs() {
        try (BufferedReader br = new BufferedReader(new FileReader("medlemmer.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                List<String> disci = Arrays.asList(values[3].split(","));
                new Medlem(values[0], sdf.parse(values[1]), values[2], disci, values[4], Boolean.parseBoolean(values[5]));

                if (!values[4].equals("0")){
                    Hold.holdliste.get(Hold.holdNavne.indexOf(values[4])).svoemmer.add(medlemmer.get(medlemmer.size()-1));
                }


            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

        // Opret et medlem
    static void opret() throws ParseException, IOException {
        List<String> stilart = new ArrayList<>();
        String stilartInput = "";
        cont = true;
        System.out.println("Indtast navn");
        String nameIn = input.nextLine();

        Date dateIn = null;
        while (cont) {
            System.out.println("Indtast fødselsdag (åååå/MM/dd)");
            String dateStringIn = input.nextLine();

            try {
                dateIn = sdf.parse(dateStringIn);
                cont = false;
            }
            catch (Exception e){
                System.out.println("Ugyldig dato");
            }

        }


        System.out.println("Medlemstype");
        Menu.menu(new String[] {"Konkurrence", "Motion", "Passiv"});
        String typeIn = null;
        boolean nyStilart = true;
        switch (Menu.op){
            case 1 -> typeIn = "Konkurrence";
            case 2 -> typeIn = "Motion";
            case 3 -> typeIn = "Passiv";
        }


        if (typeIn.equals("Konkurrence")) {

            System.out.println("Vælg en stilart!");

            while(nyStilart) {

                Menu.menu(new String[]{"Fri svømning", "Rygcrawl", "Butterfly", "Brystsvømning", "Færdiggør, gem og luk"});


                switch (Menu.op) {


                    case 1 -> stilart.add("Fri svømning");
                    case 2 -> stilart.add("Rygcrawl");
                    case 3 -> stilart.add("Butterfly");
                    case 4 -> stilart.add("Brystsvømning");
                    case 5 -> nyStilart = false;

                }


            }
        } else
            stilart.add("Ingen tilknyttet disciplin");



        new Medlem(nameIn, dateIn, typeIn, stilart, "0", false);


        Hold.tilmeldSvømmehold(medlemmer.size()-1);
        ToFile.saveList(medlemmer);
    }

    // Rediger medlems oplysninger
    static void rediger(){
        int navneIndex=udvælgSvømmer();
        System.out.println(medlemmer.get(navneIndex));
        Menu.menu(new String[]{"Adminstrer medlemskab","Tilmeld svømmehold","Frameld Svømmehold","Tilføj ny bedste tid"});
        switch (Menu.op){
            case 2 -> Hold.tilmeldSvømmehold(navneIndex);

        }
    }

    static int udvælgSvømmer() {
        System.out.println("Vælg en svømmer");
        String svømmer = input.nextLine();
        return navne.indexOf(svømmer);
    }

    // Se medlems resultater
    static void resultater(){


    }

    // Se medlemmer på hold
    static void hold(){

    }

    static void registrerBetaling() throws IOException {

        int navneindex = udvælgSvømmer();

        if (medlemmer.get(navneindex).betalt) {

            System.out.println("Kontingentet er allerede betalt");


        } else {
            System.out.println("Der mangler at blive betalt " + medlemmer.get(navneindex).beløb);

            System.out.println("Ønsker du at registrere betaling?");

            Menu.menu(new String[] {"Ja", "Nej"});

            switch (Menu.op) {

                case 1 -> {
                    medlemmer.get(navneindex).betalt = true;
                    ToFile.saveList(medlemmer);
                    restanceIndex.remove(navneindex);

                }


            }

        }


    }


    static void seRestance() {

        System.out.println("Følgende medlemmer mangler at betale kontingent:");

        for (Integer m : restanceIndex) {
            System.out.println(medlemmer.get(m).navn+" "+sdf.format(medlemmer.get(m).fødselsdag.getTime())+" "+medlemmer.get(m).type);

        }

    }



    static void tilføjSvømmeTid() {
        System.out.println("Indtast navn: ");
        String navn = input.nextLine();
        System.out.println("Indtast svømmetid: ");
        double svømmeTid = input.nextDouble();
        System.out.println("Indtast diciplin: ");
        String diciplin = input.next();

        Date fødselsdag = null;
        for (Medlem medlem: medlemmer) {
            if (medlem.navn.equals(navn)){
                fødselsdag = medlem.fødselsdag;
            }
        }

        if (Period.between(fødselsdag.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears()<18){

            Hold.holdliste.get(1).tilføjSvømmetid(new SvømmeTid(navn, svømmeTid, diciplin));
        } else Hold.holdliste.get(0).tilføjSvømmetid(new SvømmeTid(navn, svømmeTid, diciplin));




    }

    @Override
    public String toString() {
        String disci = String.join(";",discipliner);
        return navn+","+sdf.format(fødselsdag.getTime())+","+type+","+disci+","+hold+","+betalt;
    }
}
