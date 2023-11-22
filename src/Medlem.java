

public class Medlem {
    String navn;
    Date fødselsdag;
    String type;

    Medlem(String navn, Date fødselsdag, String type){
        this.navn=navn;
        this.fødselsdag=fødselsdag;
        this.type=type;
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
