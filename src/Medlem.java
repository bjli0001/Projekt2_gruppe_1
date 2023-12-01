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
    String type;
    String alder;
    String hold;
    int beløb;
    List<String> discipliner = new ArrayList<>();
    boolean betalt;
    static ArrayList<Integer> restanceIndex = new ArrayList<>();




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
        cont = true;
        System.out.println("Indtast navn");
        String nameIn = input.nextLine();
        if (!navne.contains(nameIn)) {
            Date dateIn = null;
            while (cont) {
                System.out.println("Indtast fødselsdag (åååå/MM/dd)");
                String dateStringIn = input.nextLine();

                try {
                    dateIn = sdf.parse(dateStringIn);
                    cont = false;
                } catch (Exception e) {
                    System.out.println("Ugyldig dato");
                }

        }


            System.out.println("Medlemstype");
            Menu.menu(new String[]{"Konkurrence", "Motion", "Passiv"});
            String typeIn = null;
            boolean nyStilart = true;
            switch (Menu.op) {
                case 1 -> typeIn = "Konkurrence";
                case 2 -> typeIn = "Motion";
                case 3 -> typeIn = "Passiv";
            }


            if (typeIn.equals("Konkurrence")) {

                System.out.println("Vælg en stilart!");

                    while (nyStilart) {

                    Menu.menu(new String[]{"Fri svømning", "Rygcrawl", "Butterfly", "Brystsvømning", "Færdiggør, gem og luk"});


                    switch (Menu.op) {


                        case 1 -> stilart.add("Fri svømning");
                        case 2 -> stilart.add("Rygcrawl");
                        case 3 -> stilart.add("Butterfly");
                        case 4 -> stilart.add("Brystsvømning");
                        case 5 -> nyStilart = false;

                    }


                }
            } else stilart.add("Ingen tilknyttet disciplin");


            new Medlem(nameIn, dateIn, typeIn, stilart, "0", false);

            Hold.tilmeldSvømmehold(medlemmer.size() - 1);
            ToFile.saveList(medlemmer);

        }
        else System.out.println("Navn er allerede oprettet");
    }

    // Rediger medlems oplysninger
    static void rediger() throws IOException {
        int navneIndex=udvælgSvømmer();
        System.out.println(medlemmer.get(navneIndex));
        Menu.menu(new String[]{"Adminstrer medlemskab","Tilmeld svømmehold","Frameld Svømmehold","Tilføj ny bedste tid"});
        switch (Menu.op){
            case 1 -> Medlem.adminstrerMedlemskab(navneIndex);
            case 2 -> Hold.tilmeldSvømmehold(navneIndex);

        }
    }

    static int udvælgSvømmer() {

        String svømmer = null;
        while (true) {
            System.out.println("Vælg en svømmer, tryk q for at gå tilbage");
            svømmer = input.nextLine();
            if (Medlem.navne.contains(svømmer)) {
                return navne.indexOf(svømmer);
            }
            else if (svømmer.equals("q")){
                return -1;
            }
            else System.out.println("Svømmer findes ikke");
        }

    }

    // Se medlems resultater
    static void resultater(){
        System.out.println("Resultater for alle hold:");
        System.out.println("Svømmer, Tid, Placering, Stævne");
        System.out.println();

        for (int i=0; i<2; i++) {
            System.out.println(Hold.holdliste.get(i));
            Collections.sort(Hold.holdliste.get(i).tiderFri);
            Collections.sort(Hold.holdliste.get(i).tiderRyg);
            Collections.sort(Hold.holdliste.get(i).tiderButterfly);
            Collections.sort(Hold.holdliste.get(i).tiderBryst);
            System.out.println("Frisvømning tider: " + Hold.holdliste.get(i).tiderFri);
            System.out.println("Rygcrawl tider: " + Hold.holdliste.get(i).tiderRyg);
            System.out.println("Butterfly tider: " + Hold.holdliste.get(i).tiderButterfly);
            System.out.println("Brystsvømning Times: " + Hold.holdliste.get(i).tiderBryst);

            System.out.println();
        }

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
    static void adminstrerMedlemskab(int navneIndex) throws IOException {   //Ændrer medlemsskabet for svømmeren
        if (medlemmer.get(navneIndex).type.equals("Motion")||medlemmer.get(navneIndex).type.equals("Konkurrence")){
            System.out.println("Dit medlemsskab er lige pt et "+medlemmer.get(navneIndex).type+" vil du ændre det til et Passivt medlemsskab?");
            Menu.menu(new String[]{"Ja","Nej"});

            switch (Menu.op) {
                case 1 -> {
                    medlemmer.get(navneIndex).type = "Passiv";
                    medlemmer.get(navneIndex).hold= "0";
                    medlemmer.get(navneIndex).discipliner=new ArrayList<>();
                    medlemmer.get(navneIndex).discipliner.add("Ingen tilknyttet disciplin");
                    ToFile.saveList(medlemmer);

                }
            }
        }
        else {
            System.out.println("Dit medlemsskab er lige pt et "+medlemmer.get(navneIndex).type+" vil du ændre det til et aktivt medlemsskab?");
            Menu.menu(new String[]{"Ja","Nej"});

            switch (Menu.op){
                case 1 -> {
                    System.out.println("Fedt! Er du konkurrence svømmer eller motions svømmer?");
                    Menu.menu (new String[]{"Konkurrence","Motion"});
                    switch(Menu.op){
                        case 1-> {
                            medlemmer.get(navneIndex).type = "Konkurrence";
                            System.out.println("Vælg en stilart!");
                            List<String> stilart = new ArrayList<>();
                            boolean nyStilart = true;

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
                            medlemmer.get(navneIndex).discipliner=stilart;
                            Hold.tilmeldSvømmehold(navneIndex);
                            ToFile.saveList(medlemmer);

                        }
                        case 2 -> {
                            medlemmer.get(navneIndex).type = "Motion";
                            Hold.tilmeldSvømmehold(navneIndex);
                            ToFile.saveList(medlemmer);
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        String disci = String.join(";",discipliner);
        return navn+","+sdf.format(fødselsdag.getTime())+","+type+","+disci+","+hold+","+betalt;
    }
}
