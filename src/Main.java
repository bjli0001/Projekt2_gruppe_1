import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException, IOException {


        Hold.opretHold();
        Medlem.indlæs();
        Hold.indlæsTider();


        while (true) {
            Menu.menu(new String[]{"Opret medlem", "Se/rediger medlem", "Se resultater", "Tilføj svømmetid", "Betalinger"});
            switch (Menu.op) {
                case 1 -> Medlem.opret();
                case 2 -> Medlem.rediger();
                case 3 -> Hold.resultater();
                case 4 -> Hold.tilføjSvømmeTid();
                case 5 -> {
                    Menu.menu(new String[]{"Registrer betaling", "Se medlemmer med restance, Tilbage til hovedmenu"});

                    switch (Menu.op) {

                        case 1 -> Medlem.registrerBetaling();
                        case 2 -> Medlem.seRestance();

                    }
                }
            }
        }
    }
}

// Vores Main metode