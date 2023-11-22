import java.util.ArrayList;

public class Medlem {
    ArrayList<Medlem> medlemmer = new ArrayList<>();
    String navn;
    Date fødselsdag;
    //passiv/motion/konkurrence
    String type;
    ArrayList<String> discipliner;

    // Resultat class eller String[]?
    ArrayList<> resultater;


    Medlem(String navn, Date fødselsdag, String type){
        this.navn=navn;
        this.fødselsdag=fødselsdag;
        this.type=type;
        medlemmer.add(this);
    }


    // Opret et medlem
    static void opret(){


        new Medlem();

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
}
