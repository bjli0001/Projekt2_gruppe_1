import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

public class Hold {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Hold> holdliste = new ArrayList<>();
    static ArrayList<String> holdNavne = new ArrayList<>();
    protected String holdnavn;
    protected String type;
    protected String alder;
    protected String træner;
    protected ArrayList<Medlem> svoemmer=new ArrayList<>();
    ArrayList<SvømmeTid> tiderFri = new ArrayList<>();
    ArrayList<SvømmeTid> tiderRyg = new ArrayList<>();
    ArrayList<SvømmeTid> tiderButterfly = new ArrayList<>();
    ArrayList<SvømmeTid> tiderBryst = new ArrayList<>();


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

        new Hold("Konkurrence Sr.", "Konkurrence", "Senior","Træner Mike Oxlong");
        new Hold("Konkurrence Jr.", "Konkurrence", "Junior", "Træner Mette Polka");
        new Hold("Hyggeholdet Sr.", "Motion", "Senior","Ingen træner");
        new Hold("Hyggeholdet Jr.", "Motion", "Junior","Ingen træner");

    }

    //Oprretter 4 hold

    static void tilføjSvømmeTid() {
        double tid;
        String diciplin = null;
        String dato = "";
        boolean type = false;


        int navnIndex = Medlem.udvælgSvømmer();

        if (navnIndex > -1) {

            if (Medlem.medlemmer.get(navnIndex).type.equals("Konkurrence")) {
                String navn = Medlem.navne.get(navnIndex);
                Date fødselsdag = Medlem.medlemmer.get(navnIndex).fødselsdag;

                Menu.menu(new String[]{"Træning", "Konkurrence"});

                if (Menu.op == 1) {

                    type = false;

                }

                if (Menu.op == 2) {
                    type = true;
                }

                System.out.println("Indtast svømmetid (mm:ss): ");
                String svømmeTid = input.nextLine();
                String[] svømmeTidArr = svømmeTid.split(":");
                try {
                    if (svømmeTidArr.length==2) {
                        tid = Integer.parseInt(svømmeTidArr[0]) * 60 + Integer.parseInt(svømmeTidArr[1]);
                    }
                    else tid = Integer.parseInt(svømmeTidArr[0]);

                    System.out.println("Vælg diciplin: ");
                    Menu.menu(new String[]{"Fri svømning", "Rygcrawl", "Butterfly", "Brystsvømning"});


                    switch (Menu.op) {

                        case 1 -> diciplin = "Fri svømning";
                        case 2 -> diciplin = "Rygcrawl";
                        case 3 -> diciplin = "Butterfly";
                        case 4 -> diciplin = "Brystsvømning";

                    }


                    if (!type) {

                        System.out.println("Indtast dato for tiden");
                        dato = input.nextLine();

                    }

                    if (type) {
                        System.out.println("Indtast stævne navn: ");
                        String stævneNavn = input.nextLine();
                        System.out.println("Indtast placering: ");
                        int placering = Menu.inInt(100);
                        System.out.println("Indtast dato for stævnet");
                        String datoForTid = input.nextLine();


                        if (Period.between(fødselsdag.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears() < 18) {
                            switch (diciplin){
                                case "Fri svømning" -> Hold.holdliste.get(1).tiderFri.add(new KonkurrenceTid(navn, tid, diciplin, placering, stævneNavn, datoForTid));
                                case "Rygcrawl" -> Hold.holdliste.get(1).tiderRyg.add(new KonkurrenceTid(navn, tid, diciplin, placering, stævneNavn, datoForTid));
                                case "Butterfly" -> Hold.holdliste.get(1).tiderButterfly.add(new KonkurrenceTid(navn, tid, diciplin, placering, stævneNavn, datoForTid));
                                case "Brystsvømning" -> Hold.holdliste.get(1).tiderBryst.add(new KonkurrenceTid(navn, tid, diciplin, placering, stævneNavn, datoForTid));
                            }
                            ToFile.saveResults(Hold.holdliste.get(1));

                        } else {
                            switch (diciplin){
                                case "Fri svømning" -> Hold.holdliste.get(0).tiderFri.add(new KonkurrenceTid(navn, tid, diciplin, placering, stævneNavn, datoForTid));
                                case "Rygcrawl" -> Hold.holdliste.get(0).tiderRyg.add(new KonkurrenceTid(navn, tid, diciplin, placering, stævneNavn, datoForTid));
                                case "Butterfly" -> Hold.holdliste.get(0).tiderButterfly.add(new KonkurrenceTid(navn, tid, diciplin, placering, stævneNavn, datoForTid));
                                case "Brystsvømning" -> Hold.holdliste.get(0).tiderBryst.add(new KonkurrenceTid(navn, tid, diciplin, placering, stævneNavn, datoForTid));
                            }
                            ToFile.saveResults(Hold.holdliste.get(0));
                        }
                    }
                    else {

                        if (Period.between(fødselsdag.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears() < 18) {
                            switch (diciplin) {
                                case "Fri svømning" -> Hold.holdliste.get(1).tiderFri.add(new SvømmeTid(navn, tid, diciplin, dato));
                                case "Rygcrawl" -> Hold.holdliste.get(1).tiderRyg.add(new SvømmeTid(navn, tid, diciplin, dato));
                                case "Butterfly" -> Hold.holdliste.get(1).tiderButterfly.add(new SvømmeTid(navn, tid, diciplin, dato));
                                case "Brystsvømning" -> Hold.holdliste.get(1).tiderBryst.add(new SvømmeTid(navn, tid, diciplin, dato));
                            }
                            ToFile.saveResults(Hold.holdliste.get(1));

                        } else {
                            switch (diciplin) {
                                case "Fri svømning" -> Hold.holdliste.get(0).tiderFri.add(new SvømmeTid(navn, tid, diciplin, dato));
                                case "Rygcrawl" -> Hold.holdliste.get(0).tiderRyg.add(new SvømmeTid(navn, tid, diciplin, dato));
                                case "Butterfly" -> Hold.holdliste.get(0).tiderButterfly.add(new SvømmeTid(navn, tid, diciplin, dato));
                                case "Brystsvømning" -> Hold.holdliste.get(0).tiderBryst.add(new SvømmeTid(navn, tid, diciplin, dato));
                            }
                            ToFile.saveResults(Hold.holdliste.get(0));
                        }
                    }

                } catch (Exception e) {
                    System.out.println("Ugyldig tid");
                }

            }
            else System.out.println("Svømmeren er ikke konkurrence svømmer");
        }
    }

    // Tilføjer svømmetider til de relevante hold baseret på brugerinput og konkurrencesvømmerens information(alder)

    public static void tilmeldSvømmehold(int navneIndex) {
        String age=Medlem.medlemmer.get(navneIndex).alder;
        String pro=Medlem.medlemmer.get(navneIndex).type;
        for (Hold i: holdliste){
            if (i.alder.equals(age) && i.type.equals(pro)){
                i.svoemmer.add(Medlem.medlemmer.get(navneIndex));
                Medlem.medlemmer.get(navneIndex).hold=i.holdnavn;
                System.out.println("Svømmeren er tilmeldt "+i);
            }
        }
    }

//Tilmelder en svømmer på hold baseret på alder og type(niveau)

    public static void indlæsTider() throws ParseException{
        try (BufferedReader br = new BufferedReader(new FileReader("Konkurrence Sr._tider.txt"))) {
            String line;
            int i = 0;
            String[] gemtTid;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(":");

                for (String s: values) {
                    gemtTid=s.split(",");
                    if (gemtTid.length == 4) {
                        switch (i) {
                            case 0 -> Hold.holdliste.get(0).tiderFri.add(new SvømmeTid(gemtTid[0], Double.parseDouble(gemtTid[2]), "Fri svømning",gemtTid[3]));
                            case 1 -> Hold.holdliste.get(0).tiderRyg.add(new SvømmeTid(gemtTid[0], Double.parseDouble(gemtTid[2]), "Rygcrawl", gemtTid[3]));
                            case 2 -> Hold.holdliste.get(0).tiderButterfly.add(new SvømmeTid(gemtTid[0], Double.parseDouble(gemtTid[2]), "Butterfly", gemtTid[3]));
                            case 3 -> Hold.holdliste.get(0).tiderBryst.add(new SvømmeTid(gemtTid[0], Double.parseDouble(gemtTid[2]), "Brystsvømning", gemtTid[3]));
                        }

                    }
                    else if (gemtTid.length == 6){
                        switch (i) {
                            case 0 -> Hold.holdliste.get(0).tiderFri.add(new KonkurrenceTid(gemtTid[0], Double.parseDouble(gemtTid[2]), "Fri svømning", Integer.parseInt(gemtTid[4]),gemtTid[5],gemtTid[3]));
                            case 1 -> Hold.holdliste.get(0).tiderRyg.add(new KonkurrenceTid(gemtTid[0], Double.parseDouble(gemtTid[2]), "Rygcrawl", Integer.parseInt(gemtTid[4]),gemtTid[5],gemtTid[3]));
                            case 2 -> Hold.holdliste.get(0).tiderButterfly.add(new KonkurrenceTid(gemtTid[0], Double.parseDouble(gemtTid[2]), "Butterfly", Integer.parseInt(gemtTid[4]),gemtTid[5],gemtTid[3]));
                            case 3 -> Hold.holdliste.get(0).tiderBryst.add(new KonkurrenceTid(gemtTid[0], Double.parseDouble(gemtTid[2]), "Brystsvømning", Integer.parseInt(gemtTid[4]),gemtTid[5],gemtTid[3]));
                        }
                    }
                }
                i++;
            }
            ToFile.saveResults(Hold.holdliste.get(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedReader br = new BufferedReader(new FileReader("Konkurrence Jr._tider.txt"))) {
            String line;
            int i = 0;
            String[] gemtTid;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(":");

                for (String s: values) {
                    gemtTid=s.split(",");
                    if (gemtTid.length == 4) {
                        switch (i) {
                            case 0 -> Hold.holdliste.get(1).tiderFri.add(new SvømmeTid(gemtTid[0], Double.parseDouble(gemtTid[2]), "Fri svømning", gemtTid[3]));
                            case 1 -> Hold.holdliste.get(1).tiderRyg.add(new SvømmeTid(gemtTid[0], Double.parseDouble(gemtTid[2]), "Rygcrawl", gemtTid[3]));
                            case 2 -> Hold.holdliste.get(1).tiderButterfly.add(new SvømmeTid(gemtTid[0], Double.parseDouble(gemtTid[2]), "Butterfly", gemtTid[3]));
                            case 3 -> Hold.holdliste.get(1).tiderBryst.add(new SvømmeTid(gemtTid[0], Double.parseDouble(gemtTid[2]), "Brystsvømning", gemtTid[3]));
                        }
                    }
                    else if (gemtTid.length == 6){
                        switch (i) {
                            case 0 -> Hold.holdliste.get(1).tiderFri.add(new KonkurrenceTid(gemtTid[0], Double.parseDouble(gemtTid[2]), "Fri svømning", Integer.parseInt(gemtTid[4]),gemtTid[5],gemtTid[3]));
                            case 1 -> Hold.holdliste.get(1).tiderRyg.add(new KonkurrenceTid(gemtTid[0], Double.parseDouble(gemtTid[2]), "Rygcrawl", Integer.parseInt(gemtTid[4]),gemtTid[5],gemtTid[3]));
                            case 2 -> Hold.holdliste.get(1).tiderButterfly.add(new KonkurrenceTid(gemtTid[0], Double.parseDouble(gemtTid[2]), "Butterfly", Integer.parseInt(gemtTid[4]),gemtTid[5],gemtTid[3]));
                            case 3 -> Hold.holdliste.get(1).tiderBryst.add(new KonkurrenceTid(gemtTid[0], Double.parseDouble(gemtTid[2]), "Brystsvømning", Integer.parseInt(gemtTid[4]),gemtTid[5],gemtTid[3]));
                        }
                    }
                }
                i++;
            }
            ToFile.saveResults(Hold.holdliste.get(1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Indlæser svømmetider fra to forskellige filer ("Senior K_tider.txt" og "Konkurrence Jr._tider.txt") og organisere dem på de tilsvarende hold i Hold.holdliste


    static void resultater(){
        System.out.println("Resultater for alle hold:");
        System.out.println("Svømmer, Tid, Placering, Stævne");
        System.out.println();

        for (int i=0; i<2; i++) {
            System.out.println(holdliste.get(i));
            Collections.sort(holdliste.get(i).tiderFri);
            Collections.sort(holdliste.get(i).tiderRyg);
            Collections.sort(holdliste.get(i).tiderButterfly);
            Collections.sort(holdliste.get(i).tiderBryst);
            System.out.print("Frisvømning tider: ");
            printTider(holdliste.get(i).tiderFri);
            System.out.print("Rygcrawl tider: ");
            printTider(holdliste.get(i).tiderRyg);
            System.out.print("Butterfly tider: ");
            printTider(holdliste.get(i).tiderButterfly);
            System.out.print("Brystsvømning Times: ");
            printTider(holdliste.get(i).tiderBryst);

            System.out.println();
        }

    }
    //Udskriver resultaterne for alle hold, herunder svømmere, tid, placering og stævneinformation.

    static void printTider(ArrayList<SvømmeTid> tider){

        ArrayList<String> names = new ArrayList<>();
        boolean cont = true;
        int i = 0;
        int j = 0;
        while (cont){
            try {
                if (!names.contains(tider.get(i).name)) {
                    names.add(tider.get(i).name);
                    j++;
                    System.out.print((j) + ".: " + tider.get(i).toPrint());
                    if (j == 5) {
                        cont = false;
                    } else System.out.print("  ");
                }
                i++;
            }
            catch (Exception e) {
                cont=false;
            }
        }
        System.out.println();

    }

    // udskriver top fem svømmertider fra en liste af SvømmeTid-objekter.

    @Override
    public String toString() {
        return "Holdet "+holdnavn+" for "+type+" i aldersgruppen "+alder+" med træneren: "+træner;
    }

    // Genererer en tekstrepræsentation af et holdobjekt.
}