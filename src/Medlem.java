import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Medlem {
    static ArrayList<Medlem> medlemmer = new ArrayList<>();
    String navn;
    Date fødselsdag;
    // passiv/motion/konkurrence
    String type;
    ArrayList<String> discipliner;

    // Resultat class eller String[]?
    //ArrayList<> resultater;





    Medlem(String navn, Date fødselsdag, String type){
        this.navn=navn;
        this.fødselsdag=fødselsdag;
        this.type=type;
        medlemmer.add(this);
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

                new Medlem(values[0], sdf.parse(values[1]), values[2]);
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

        new Medlem(nameIn, dateIn, typeIn);
        ToFile.saveList(medlemmer);
    }

    // Rediger medlems oplysninger
    static void rediger(){

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
