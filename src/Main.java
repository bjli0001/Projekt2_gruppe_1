import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException, IOException {


        Hold.opretHold();
        Medlem.indlæs();
        Hold.indlæsTider();



        while (true) {
            Menu.menu(new String[]{"Opret medlem", "Rediger medlem", "Se resultater", "Svømmehold", "Tilføj svømmetid", "Betalinger"});
            switch (Menu.op) {
                case 1 -> Medlem.opret();
                case 2 -> Medlem.rediger();
                case 3 -> Medlem.resultater();
                case 4 -> Medlem.hold();
                case 5 -> Hold.tilføjSvømmeTid();
                case 6 -> {
                    Menu.menu(new String[]{"Registrer betaling", "Se medlemmer med restance"});

                    switch (Menu.op) {

                        case 1 -> Medlem.registrerBetaling();
                        case 2 -> Medlem.seRestance();

                    }
                }
            }
        }



    }
}