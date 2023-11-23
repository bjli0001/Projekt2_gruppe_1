import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Medlem {
    static ArrayList<Medlem> medlemmer = new ArrayList<>();
    static ArrayList<String> navne = new ArrayList<>();
    String navn;
    Date fødselsdag;
    // passiv/motion/konkurrence
    String type;
    String alder;
    String hold;
    ArrayList<String> discipliner;

    // Resultat class eller String[]?
    //ArrayList<> resultater;





    Medlem(String navn, Date fødselsdag, String type,String hold){
        this.navn=navn;
        this.fødselsdag=fødselsdag;
        this.type=type;
        this.hold=hold;
        if (Period.between(fødselsdag.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears()<18){
            alder="Junior";
        } else alder="Senior";
        medlemmer.add(this);
        navne.add(navn);
    }


    //Dato format
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    static Scanner input = new Scanner(System.in);
    static boolean cont;

    static void indlæs() {
        try (BufferedReader br = new BufferedReader(new FileReader("medlemmer.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                new Medlem(values[0], sdf.parse(values[1]), values[2],values[3]);
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

        // Opret et medlem
    static void opret() throws ParseException, IOException {
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
        switch (Menu.op){
            case 1 -> typeIn = "Konkurrence";
            case 2 -> typeIn = "Motion";
            case 3 -> typeIn = "Passiv";
        }
        String holdIn=null;
        System.out.println("Mulige hold");
        Menu.op=Menu.inInt(Hold.holdliste.size());


        new Medlem(nameIn, dateIn, typeIn,holdIn);
        ToFile.saveList(medlemmer);
    }

    // Rediger medlems oplysninger
    static void rediger(){
        System.out.println("Vælg en svømmer");
        String svømmer = input.nextLine();
        int navneIndex=navne.indexOf(svømmer);
        System.out.println(medlemmer.get(navneIndex));
        Menu.menu(new String[]{"Adminstrer medlemskab","Tilmeld svømmehold","Frameld Svømmehold","Tilføj ny bedste tid"});
        switch (Menu.op){
            case 2 -> Hold.tilmeldSvømmehold(navneIndex);

        }
    }

    // Se medlems resultater
    static void resultater(){

    }

    // Se medlemmer på hold
    static void hold(){

    }

    @Override
    public String toString() {
        return navn+","+sdf.format(fødselsdag.getTime())+","+type;
    }
}
